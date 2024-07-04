package ec.edu.upd.ppw64.services;


import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/email")
public class EmailResource {

    private EmailService emailService;

    public EmailResource() {
        // Aquí deberías inicializar EmailService con tu apiKey de Resend
        this.emailService = new EmailService("re_K61tVwsg_B9kxV83Wg5go418nqJdJRu4V");
    }

    @POST
    @Path("/enviar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enviarCorreo() {
    	try {
            SendEmailResponse response = emailService.enviarCorreo();
            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    
    }
}
