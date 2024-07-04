package ec.edu.upd.ppw64.services;

public class SendEmailRequest {

    private String destinatario;
    private String asunto;
    private String contenido;

    public SendEmailRequest() {
        // Constructor por defecto necesario para la deserialización
    }

    // Getters y setters
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}