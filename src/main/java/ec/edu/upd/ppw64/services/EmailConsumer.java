package ec.edu.upd.ppw64.services;

import com.rabbitmq.client.*;
import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class EmailConsumer {

    private final static String QUEUE_NAME = "email_queue";
    private static final String SERVER_URL = "http://35.184.173.35:8080/mail/rs/email/enviar";
    private RestTemplate restTemplate = new RestTemplate();

    public EmailConsumer() {
        this.restTemplate = new RestTemplate();
    }

    public void consumeEmails() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("35.184.173.35"); // Cambia a la dirección de tu servidor RabbitMQ
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            Emaill email = new Gson().fromJson(message, Emaill.class);
            try {
                sendEmail(email);
                System.out.println(" [x] Emaill sent: '" + email.getTo() + "'");
            } catch (Exception e) {
                System.out.println(" [x] Error sending email: " + e.getMessage());
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

    private void sendEmail(Emaill email) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construir el cuerpo de la solicitud
        String body = "{\"to\": \"" + email.getTo() + "\", \"subject\": \"" + email.getSubject() + "\", \"html\": \"" + email.getBody() + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(SERVER_URL, entity, String.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                System.out.println("Correo enviado correctamente");
            } else {
                System.out.println("Error al enviar el correo: " + response.getBody());
            }
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }

    class Email {
        private String to;
        private String subject;
        private String body;

        // Getters y Setters

        public Email(String to, String subject, String body) {
            this.to = to;
            this.subject = subject;
            this.body = body;
        }
    }
}
