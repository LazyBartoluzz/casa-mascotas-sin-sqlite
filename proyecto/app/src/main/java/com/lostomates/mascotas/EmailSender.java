package com.lostomates.mascotas;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/** JavaMail real sobre SMTP STARTTLS. No ejecutarlo en el hilo UI. */
public final class EmailSender {
    private EmailSender() { }

    public static void enviar(String host, int puerto, final String usuario, final String clave,
                              String destino, String nombre, String correo, String texto) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.checkserveridentity", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(puerto));
        props.put("mail.smtp.connectiontimeout", "15000");
        props.put("mail.smtp.timeout", "15000");
        props.put("mail.smtp.writetimeout", "15000");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
            }
        });
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(usuario));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino, true));
        msg.setSubject("Comentario de Casa Mascotas Los Tomates", "UTF-8");
        msg.setText("Nombre: " + nombre + "\nCorreo de contacto: " + correo + "\n\n" + texto, "UTF-8");
        Transport.send(msg);
    }
}
