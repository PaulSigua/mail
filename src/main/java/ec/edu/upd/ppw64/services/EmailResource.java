package ec.edu.upd.ppw64.services;


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
        this.emailService = new EmailService("re_bXC2yREn_J7sfxiJvgqLFs4KwXMT7VmDU");
    }

    @POST
    @Path("/enviar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enviarCorreo() {
    	try {
        SendEmailResponse response = emailService.enviarCorreo();

        return Response.ok().entity(response).build();
    	} catch (Exception e) {
			// TODO: handle exception

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
    }
}
