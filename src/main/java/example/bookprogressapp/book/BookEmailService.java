package example.bookprogressapp.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BookEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    void sendEmail(String bookTitle, Long bookId, String email){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("New book added to your collection!");
        msg.setText("A book with title: " +bookTitle +" was added to your collection! View it here: localhost:4200/books/" +bookId);
        javaMailSender.send(msg);
    }
}
