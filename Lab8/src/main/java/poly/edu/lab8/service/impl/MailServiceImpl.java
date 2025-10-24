package poly.edu.lab8.service.impl;

import poly.edu.lab8.service.MailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    // Queue for scheduled sending
    private final List<MailService.Mail> queue = new ArrayList<>();

    @Override
    public synchronized void push(MailService.Mail mail) {
        queue.add(mail);
    }

    @Override
    public void send(MailService.Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // 2.1 From
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());

            // 2.2 To / CC / BCC
            if (mail.getTo() != null) helper.setTo(mail.getTo());
            if (mail.getCc() != null && !mail.getCc().trim().isEmpty()) helper.setCc(mail.getCc());
            if (mail.getBcc() != null && !mail.getBcc().trim().isEmpty()) helper.setBcc(mail.getBcc());

            // 2.3 Subject + Body (HTML enabled)
            helper.setSubject(mail.getSubject() != null ? mail.getSubject() : "");
            helper.setText(mail.getBody() != null ? mail.getBody() : "", true);

            // 2.4 Attachments
            if (mail.getFilenames() != null && !mail.getFilenames().trim().isEmpty()) {
                String[] parts = mail.getFilenames().split("[,;]+");
                for (String p : parts) {
                    File f = new File(p.trim());
                    if (f.exists() && f.isFile()) {
                        helper.addAttachment(f.getName(), f);
                    }
                }
            }

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Scheduled worker: run every 500ms
    @Scheduled(fixedDelay = 500)
    public void run() {
        while (!queue.isEmpty()) {
            MailService.Mail mail;
            synchronized (this) {
                if (queue.isEmpty()) return;
                mail = queue.remove(0);
            }
            try {
                this.send(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


