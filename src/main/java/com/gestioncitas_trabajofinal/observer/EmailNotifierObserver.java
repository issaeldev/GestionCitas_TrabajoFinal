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

        String asunto = "Confirmación de su Cita Médica";
        String cuerpo = """
                Hola %s,

                Le confirmamos que su cita ha sido registrada exitosamente con los siguientes detalles:

                - Médico: %s
                - Especialidad: %s
                - Fecha y Hora: %s
                - Motivo: %s

                Puede cancelar esta cita o volver a programarla desde su panel de usuario.

                Gracias por confiar en CitaSmart.
                """.formatted(nombrePaciente, nombreMedico, especialidad, fechaHora, motivo);

        enviarCorreo(emailPaciente, asunto, cuerpo);
    }

    private void enviarCorreo(String destinatario, String asunto, String cuerpo) {
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
            mensaje.setText(cuerpo);

            Transport.send(mensaje);
            System.out.println("Correo enviado correctamente a " + destinatario);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}