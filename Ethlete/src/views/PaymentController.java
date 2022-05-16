/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class PaymentController implements Initializable {

    
    @FXML
    private TextField monthTF;
    @FXML
    private TextField yearTF;
    @FXML
    private TextField carteTF;
    @FXML
    private TextField ccvTF;
    @FXML
    private TextField emailTF;
    @FXML
    private JFXButton closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
    public void SendEmail() throws AddressException, MessagingException {
        
        
        String password = "quantech123";
        String toAddress = emailTF.getText();
        String userName = "quantechp@gmail.com";
        String subject = "Détails de Facturation";
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
// * BEGIN CHANGE
        properties.put("mail.smtp.user", userName);
        
        

        // creates a new session, no Authenticator (will connect() later)
        Session session = Session.getDefaultInstance(properties);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("Vous trouveriez ci_joint votre Billet\n"
                 + "Veuillez le montrer à l'agent de sécurité lors de votre présence");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         
         File f = new File("test.pdf");
         DataSource source = new FileDataSource(f.getAbsolutePath());
         System.out.println(f.getAbsolutePath());
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName("test.pdf");
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         msg.setContent(multipart);


        Transport t = session.getTransport("smtp");
        t.connect("smtp.gmail.com", userName, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
        
        // Send message
        

         System.out.println("Sent message successfully....");
        
    }

    @FXML
    private void sendMailBTN(ActionEvent event) throws MessagingException {
        generatePDF();
        SendEmail();
        
    }
     @FXML
    private void retour(ActionEvent event) {
     Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();  }
    
    private void generatePDF() 
    {
            Document doc= new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("test.pdf"));
            doc.open();
            doc.add(new Paragraph("JACKFELYD JESSAIE"));
            doc.close();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }                    
        
        
    }

    public static boolean emailFormat(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isEmail = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {
            isEmail = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isEmail;

    }

}
