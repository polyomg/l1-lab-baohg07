package poly.edu.lab8.service;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

public interface MailService {

    @Data
    @Builder
    @Accessors(chain = true)
    class Mail {
        @Builder.Default
        private String from = "WebShop <web-shop@gmail.com>";
        private String to;
        private String cc;
        private String bcc;
        private String subject;
        private String body;
        private String filenames; // file paths separated by , or ;
    }

    void send(Mail mail);

    default void send(String to, String subject, String body) {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }

    void push(Mail mail); // for Bài 2 (ký tiếp)
    default void push(String to, String subject, String body) {
        this.push(Mail.builder().to(to).subject(subject).body(body).build());
    }
}

