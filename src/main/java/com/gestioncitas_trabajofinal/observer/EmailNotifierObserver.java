package com.gestioncitas_trabajofinal.observer;

import com.gestioncitas_trabajofinal.model.Cita;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class EmailNotifierObserver implements Observer {

    private final String remitente = "hospitalnacionalh@gmail.com";
    private final String contrasena = "hkzaiuqmwpetxwwv";

    @Override
    public void update(Cita cita) {

        String emailPaciente = cita.getPaciente().getUsername();
        String nombrePaciente = cita.getPaciente().getNombre();
        String nombreMedico = "Dr(a). " + cita.getMedico().getNombre() + " " + cita.getMedico().getApellido();
        String especialidad = cita.getMedico().getEspecialidad().getNombre();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm 'hrs.'");
        String fechaHora = cita.getFechaHora().format(formatter);

        String motivo = cita.getMotivo() != null ? cita.getMotivo() : "No especificado";

        String asunto = "Confirmación de Cita Médica – CitaSmart";


        //      CORREO EN HTML
      
        String cuerpoHTML = """
            <html>
            <body style="font-family: Arial, sans-serif; background:#f4f4f7; padding:20px;">
                
                <div style="max-width: 520px; margin:auto; background:white; padding:25px; border-radius:10px; box-shadow:0 2px 8px rgba(0,0,0,0.1);">
                    
                    <h2 style="color:#0069c0; text-align:center; margin-top:0;">
                        Confirmación de Cita Médica
                    </h2>

                    <p>Hola <strong>%s</strong>,</p>

                    <p>Su cita ha sido registrada exitosamente con los siguientes detalles:</p>

                    <div style="background:#eef6ff; padding:15px; border-radius:8px;">
                        <p><strong>Médico:</strong> %s</p>
                        <p><strong>Especialidad:</strong> %s</p>
                        <p><strong>Fecha y Hora:</strong> %s</p>
                        <p><strong>Motivo:</strong> %s</p>
                    </div>

                    <p style="margin-top:20px;">
                        Puede cancelar o reprogramar su cita desde su panel de usuario.
                    </p>

                    <p style="text-align:center; color:#888; margin-top:30px; font-size:12px;">
                        Gracias por confiar en <strong>CitaSmart</strong>.
                    </p>
                </div>

            </body>
            </html>
        """.formatted(nombrePaciente, nombreMedico, especialidad, fechaHora, motivo);

        enviarCorreo(emailPaciente, asunto, cuerpoHTML);
    }

    private void enviarCorreo(String destinatario, String asunto, String cuerpoHTML) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(remitente, contrasena);
                    }
                });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);

            // 💡 Aquí está la clave para permitir HTML
            mensaje.setContent(cuerpoHTML, "text/html; charset=UTF-8");

            Transport.send(mensaje);
            System.out.println("Correo enviado correctamente a " + destinatario);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
