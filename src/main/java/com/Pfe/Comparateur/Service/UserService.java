package com.Pfe.Comparateur.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import com.Pfe.Comparateur.Models.Product;
import com.Pfe.Comparateur.Models.User;
import com.Pfe.Comparateur.Models.User_alert;
import com.Pfe.Comparateur.Repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;
@Autowired
 ProductService productService;
@Autowired
UserAlertService userAlertService;

    @Autowired
    private static JavaMailSender javaMailSender;






    public UserService(UserRepository userRepository , JavaMailSender jms) {

        this.userRepository = userRepository;
        this.javaMailSender = jms ;
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }
    public List<User> createUser(User newUser) {

        userRepository.save(newUser);
        return getAll();
    }
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> deleteUser(String id) {
        userRepository.deleteById(id);
        return getAll();
    }

    public List<User> updateUser(User updateUser,String id) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(updateUser.getUsername());
            existingUser.setEmail(updateUser.getEmail());
            existingUser.setPassword(updateUser.getPassword());



            userRepository.save(existingUser);
        }

        return getAll();
    }




public void sendEmail(String to, String subject, String body) throws MessagingException {
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(body, true);

    javaMailSender.send(message);
}
    public static double removeLastThreeCharactersAndConvertToDouble(String str) throws ParseException {
        if (str.length() <= 3) {
            throw new IllegalArgumentException("The string must have more than 3 characters.");
        }
        String modifiedString = str.substring(0, str.length() - 3);
        return parseStringToDouble(modifiedString);
    }
    public static double parseStringToDouble(String str) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number = format.parse(str);
        return number.doubleValue();
    }
    public void checkPriceAndSendAlerts() throws ParseException {
        List<Product> products = productService.getAllProducts();
        List<User_alert> userAlerts = userAlertService.getAllUserAlerts();

        for (Product product : products) {
            for (User_alert userAlert : userAlerts) {
                // Comparaison du prix du produit et du budget de l'utilisateur
                if (product.getId().equals(userAlert.getProducts().getId())){
                    if (removeLastThreeCharactersAndConvertToDouble(product.getPrice()) == userAlert.getBudget()) {
                        try {
                            System.out.println( userAlert.getUser().getEmail());
                            sendEmail(userAlert.getUser().getEmail(), "Notification", product.getProductName());
                        } catch (MessagingException e) {
                            // Gérer l'exception de manière appropriée
                            e.printStackTrace();
                        }
                    }
            }
            }
        }
    }
}
