package poly.edu.lab8.controller;

import poly.edu.lab8.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailService mailService;

    @GetMapping("/form")
    public String form() {
        return "mail/form"; // sẽ tìm file templates/mail/form.html
    }

    @ResponseBody
    @GetMapping("/send")
    public String send(@RequestParam String to,
                       @RequestParam String subject,
                       @RequestParam String body) {
        mailService.send(to, subject, body);
        return "Mail đã được gửi đi!";
    }

    // 🧩 Test nhanh (kiểm tra service hoạt động)
    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return """
                ✅ MailController đang hoạt động tốt!<br>
                <a href='/mail/form'>Quay lại form gửi mail</a>
                """;
    }

}
