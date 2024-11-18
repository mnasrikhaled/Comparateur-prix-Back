package com.Pfe.Comparateur.Service;

import com.Pfe.Comparateur.Models.Product;
import com.Pfe.Comparateur.Models.User;
import com.Pfe.Comparateur.Models.User_alert;
import com.Pfe.Comparateur.Repository.ProductRepository;
import com.Pfe.Comparateur.Repository.UserAlertRepository;
import com.Pfe.Comparateur.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public  class UserAlertService {
    @Autowired
    private UserAlertRepository userAlertRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;





public ResponseEntity<?> sendBudget(String id, String _id, Double budget) {
    if (id != null && _id != null) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Product> optionalProduct = productRepository.findById(_id);

        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            User user = optionalUser.get();
            Product product = optionalProduct.get();

            User_alert userAlert = new User_alert();
            userAlert.setUser(user);
            userAlert.setBudget(budget);
            userAlert.setProduct(product);
            userAlert.getPrice();


            userAlertRepository.save(userAlert);
            return new ResponseEntity<>(userAlert, HttpStatus.OK);
        } else {
            // Vérifier si l'utilisateur existe
            if (!optionalUser.isPresent()) {
                return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND);
            } else {
                // Vérifier si le produit existe
                if (!optionalProduct.isPresent()) {
                    return new ResponseEntity<>("Product not found with ID: " + _id, HttpStatus.NOT_FOUND);
                }
            }
        }
    }

    return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
}



    public List<User_alert> getAllUserAlerts() {
        return userAlertRepository.findAll();
    }
}