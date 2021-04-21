/*
 * File: EmailService.java
 * author: David Villalobos
 * Date: 2021/04/09
 */

package com.getyourtour.service;

import com.getyourtour.model.DetailReservationTour;
import com.getyourtour.model.ReservationTour;
import com.getyourtour.model.Tour;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class EmailService {
	
	private static String email = "myEmail@gmail.com";
	private static String TOKEN = "TOKEN";

    public static void send(String to, String subject, String content) {
        String host = "smtp.gmail.com";
        String from = email;
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, TOKEN);
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html");
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static String generateBillEmail(ReservationTour r) {
        String head = "<head><meta charset=\"UTF-8\">";
        head += "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\"";
        head += "content=\"width=device-width, initial-scale=1.0\"></head>";
        String body = "<body><p style=\"height: 30px; color: black; text-align: left;\">";
        body += "Gracias " + r.getUser().getCompleteName() + " por comprar con Get Your Tour <br> Adjuntamos el detalle de su compra:</p>";
        body += "<table style=\"background-color: white; color: rgb(70, 70, 97); width: 100%;\"><tr><th colspan=7 style=\"border: 1px dotted rgb(138, 138, 168);\" >Reservación #" + r.getId() + "</th></tr>";
        body += "<tr><th style=\"border: 1px dotted rgb(138, 138, 168);\"> #Tour </th><th style=\"border: 1px dotted rgb(138, 138, 168);\">Lugar</th><th style=\"border: 1px dotted rgb(138, 138, 168);\"> Fecha </th><th style=\"border: 1px dotted rgb(138, 138, 168);\"> Nombre </th>";
        body += "<th style=\"border: 1px dotted rgb(138, 138, 168);\"> Precio </th><th style=\"border: 1px dotted rgb(138, 138, 168);\"> Tiquetes </th><th style=\"border: 1px dotted rgb(138, 138, 168);\"> Total </th></tr>";
        for (DetailReservationTour detail : r.getDetails()) {
            Tour t = detail.getTour();
            body += "<tr style=\"text-align: center;\"><td style=\"border: 1px dotted rgb(138, 138, 168);\">" + t.getId() + "</td><td style=\"border: 1px dotted rgb(138, 138, 168);\">" + t.getCompletePlace() + "</td><td style=\"border: 1px dotted rgb(138, 138, 168);\">" + t.getDate().toString() + "</td><td style=\"border: 1px dotted rgb(138, 138, 168);\">" + t.getName() + "</td>";
            body += "<td style=\"border: 1px dotted rgb(138, 138, 168);\">" + t.getPrice() + "</td><td style=\"border: 1px dotted rgb(138, 138, 168);\">" + detail.getTickets() + "</td><td style=\"border: 1px dotted rgb(138, 138, 168);\"> " + detail.getTotal() + "</td></tr>";
        }
        body += "<tr><td colspan=6 style=\"text-align: right;border: 1px dotted rgb(138, 138, 168);\">Sub-total</td><td style=\"border: 1px dotted rgb(138, 138, 168);\">" + r.getSubTotal() + "</td></tr>";
        body += "<tr><td colspan=6 style=\"text-align: right;border: 1px dotted rgb(138, 138, 168);\">Comisión 5%</td><td style=\"border: 1px dotted rgb(138, 138, 168);\">" + r.getCommission() + "</td></tr>";
        body += "<tr><td colspan=6 style=\"text-align: right;border: 1px dotted rgb(138, 138, 168);\">Total</td><td style=\"border: 1px dotted rgb(138, 138, 168);\">" + r.getTotal() + "</td></tr></table></body>";
        return "<!DOCTYPE html style=\"font-size: large; overflow:auto; text-align: center;\"><html lang=\"es\">" + head + body + "</html>";
    }
}