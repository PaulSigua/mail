package ec.edu.upd.ppw64.services;

import com.resend.*;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;

public class EmailService {

    private Resend resend;

    public EmailService(String apiKey) {
        this.resend = new Resend(apiKey);
    }

    public SendEmailResponse enviarCorreo() {
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("onboarding@resend.dev")
                .to("jeisonpanora12@gmail.com")
                .subject("Ventadas")
                .html("se guardo el mensaje")
                .build();

        SendEmailResponse response = resend.emails().send(sendEmailRequest);
        return response;
    }
}
