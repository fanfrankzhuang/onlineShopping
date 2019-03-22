package controller;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;


public class SendEmail extends HttpServlet{
    public void doMethod(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String name = request.getParameter("Name");
        //String phonenumber = request.getParameter("PhoneNumber");
        String email = request.getParameter("FromEmailAddress");
        String comments = request.getParameter("Comments");

        String[] recepients = new String[]{"C0735444@mylambton.ca"};
        String [] bccRecepients = new String[]{"lambtoncollegeintoronto@gmail.com"};
        if(sendMail(recepients,bccRecepients,"Comment", comments, email,name)) {
            response.sendRedirect("welcome.html");
        } else {
            response.sendRedirect("error.html");
        };
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        doMethod(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        doMethod(req, res);
    }

    public static boolean sendMail(String[] recepients, String[] bccRecepients, String subject, String message,
                                   final String fromAddress, String name)
    {

        try{
            Properties props =new Properties();
            props.put("mail.smtp.user", fromAddress);
            props.put("mail.smtp.host","smtp.gmail.com" );
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "false");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(props, new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(fromAddress,"woshi_ZF!");
                }
            });

            Message msg = new MimeMessage(session);
            InternetAddress from = new InternetAddress(fromAddress, name);
            msg.setFrom(from);
            //To Recipients
            InternetAddress[] toAddresses = new InternetAddress[recepients.length];
            for (int i =0;i<recepients.length; i++)
            {
                toAddresses[i] = new InternetAddress(recepients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            //BCC Recipients
            if (bccRecepients != null) {
                InternetAddress[] bccAddresses = new InternetAddress[bccRecepients.length];
                for (int i = 0; i < bccRecepients.length; i++) {
                    bccAddresses[i] = new InternetAddress(bccRecepients[i]);
                }
                msg.setRecipients(Message.RecipientType.BCC, bccAddresses);
            }
            msg.setSubject(subject);
            msg.setContent(message,"text/plain");
            Transport.send(msg);
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;

    }

}

