package com.jeison.perfomance_test.infrastructure.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailHelper {

    private JavaMailSender mailSender;

     public void sendMail(String destinity, String nameClient, LocalDateTime date){
        MimeMessage message = mailSender.createMimeMessage();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateAppointment = date.format(formatter);
        String htmlContent = this.readHTMLTemplate(nameClient, dateAppointment);

        try {
            message.setFrom(new InternetAddress("jeisonortiz1516@gmail.com"));
            message.setSubject("Survey created succesfully");

            message.setRecipients(MimeMessage.RecipientType.TO,destinity);
            message.setContent(htmlContent,MediaType.TEXT_HTML_VALUE);

            mailSender.send(message);

        } catch (Exception e) {
            System.out.println("ERROR sending the email " + e.getMessage());

        }
    }


    private String readHTMLTemplate(String nameClient, String date){
        //Indicar en donde se encuentra el template
        final Path path = Paths.get("src/main/resources/templates/email.html");

        try (var lines = Files.lines(path)){
            var html = lines.collect(Collectors.joining());

            return html.replace("{name}", nameClient).replace("{date}", date);
        } catch (IOException e) {
            System.out.println("Error reading file");
            throw new RuntimeException();
        }
    }
}
