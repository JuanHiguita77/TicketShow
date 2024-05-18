package com.riwi.ticketShowWeb.infraestructure.helpers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailHelper {

    private final JavaMailSender mailSender;

    public void sendMail(String title, String city, String userName, LocalDateTime date, String description, double price, String destiny)
    {
        MimeMessage message = mailSender.createMimeMessage();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateAppointment = date.format(formatter);

        String htmlContent = readHTMLTemplate(userName, title, city, description, dateAppointment, price);

        try{
            message.setFrom(new InternetAddress("juanferhiguita65@gmail.com"));
            message.setSubject("Event reserved successfully");

            message.setRecipients(MimeMessage.RecipientType.TO, destiny);

            message.setContent(htmlContent,MediaType.TEXT_HTML_VALUE);

            mailSender.send(message);
            System.out.println("Email sended");

        }catch(Exception e){
            System.out.println("Error no se pudo enviar el email "+ e.getMessage());
        }

    }

    private String readHTMLTemplate(String userName, String title, String city, String description, String date, double price)
    {
        final Path path = Paths.get("src/main/resources/emails/email_template.html");
        
        try (var lines = Files.lines(path)){
            var html = lines.collect(Collectors.joining());

            return html.replace("{userName}", userName)
                        .replace("{title}", title)
                        .replace("{city}", description)
                        .replace("{description}", description)
                        .replace("{price}", String.valueOf(price));
        } 
        catch (Exception e) 
        {
            System.out.print("Email Html template cant be read...");
            throw new RuntimeException();
        }
    }
}
