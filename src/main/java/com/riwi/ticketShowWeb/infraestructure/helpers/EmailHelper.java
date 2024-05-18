package com.riwi.ticketShowWeb.infraestructure.helpers;



import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.mail.stereotype.Component;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Paths;
import lombok.AllArgsConstructor;

@Component
public class EmailHelper {
    private final JavaMailSender mailsender;

    public void sendMail(String destiny, String userName, LocalDateTime date){
        MimeMessage message = mailsender.createMimeMessage();

        String dateAppointment = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String htmlContent = this.readHTMLTemplate(name, date);

        try{
            message.setFrom(new InternetAddress("Correo del que se le envia va aca"));
            message.setSubject("Informacion acerca de su evento");

            message.setRecipients(MimeMessage.RecipientType.TO,destiny);
            message.setContent(htmlContent.MediaType.TEXT_HTML_VALUE);

            mailsender.send(message);
            System.out.println("Email send");
        }catch(Exception e){
            System.out.println("Error no se pudo enviar el email "+ e.getMessage());
        }

        
    }

    private String readHTMLTemplate(String userName, String title, String city, String category, String description, Double price ){
        final Path path = Paths.get("src/main/resources/emails/email_template.html");
        
        try (var lines = Files.lines(path)){
            var html = lines.collect(Collectors.joining());

            return html.replace("{userName}", userName).replace("{title}", title).replace("{city}", description).replace("{category}", category)
            .replace("{description}", description).replace("{price}", price);
        } catch (Exception e) {
            System.out.println("No se pudo leer el html");
            throw new RuntimeException();
        }

        return 
    }
}
