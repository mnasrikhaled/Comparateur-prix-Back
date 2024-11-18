package com.Pfe.Comparateur.Controller;


import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.Pfe.Comparateur.Models.ERole;
import com.Pfe.Comparateur.Models.Role;
import com.Pfe.Comparateur.Models.User;


import com.Pfe.Comparateur.Models.User_alert;
import com.Pfe.Comparateur.Repository.RoleRepository;
import com.Pfe.Comparateur.Repository.UserAlertRepository;
import com.Pfe.Comparateur.Repository.UserRepository;

import com.Pfe.Comparateur.Repository.ProductRepository;


import com.Pfe.Comparateur.Request.LoginRequest;
import com.Pfe.Comparateur.Response.MessageResponse;
import com.Pfe.Comparateur.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody LoginRequest loginRequest) {
        // Vérifier si l'utilisateur existe dans la base de données
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Vérifier si le mot de passe correspond
            if (user.getPassword().equals(loginRequest.getPassword())) {
                // L'authentification a réussi, retourner une réponse réussie avec le nom d'utilisateur
                return ResponseEntity.ok(new MessageResponse("Signin successful!", user.getUsername(),user.getId()));
            }
        }

        // L'authentification a échoué
        return ResponseEntity.badRequest().body(new MessageResponse("Invalid username or password", null,null));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Get the session associated with the request
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Clear any authentication-related data stored in the session
            session.removeAttribute("user"); // Assuming "user" is the attribute storing user details
            session.invalidate(); // Invalidate the session
            return ResponseEntity.ok(new MessageResponse("Logout successful!",null,null));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("No active session found",null,null));
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User signupRequest) {
        try {
            // Vérifier si le nom d'utilisateur est déjà pris
            if (userRepository.existsByUsername(signupRequest.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken", null,null));
            }
            // Vérifier si l'e-mail est déjà utilisé
            if (userRepository.existsByEmail(signupRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use", null,null));
            }



            // Créer un nouvel utilisateur
            User user = new User(signupRequest.getUsername(), signupRequest.getEmail(),
                    signupRequest.getPassword());

            // Vérifier si l'utilisateur qui s'inscrit est l'administrateur
            if (signupRequest.getUsername().equals("admin") && signupRequest.getEmail().equals("Medianet@gmail.com")) {
                Role adminRole = new Role(ERole.ADMIN);
                roleRepository.save(adminRole); // Sauvegarder le rôle ADMIN en premier
                user.getRoles().add(adminRole);
            } else {
                Role userRole = new Role(ERole.USER);
                roleRepository.save(userRole); // Sauvegarder le rôle USER en premier
                user.getRoles().add(userRole);
            }


            // Enregistrer l'utilisateur dans la base de données
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("Signup successful!", null,null));
        } catch (Exception e) {
            // En cas d'erreur lors de l'enregistrement de l'utilisateur
            e.printStackTrace(); // Affichez l'exception dans la console pour le débogage
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error occurred while signing up. Please try again later.", null,null));
        }
    }




    @GetMapping(value = "all")
    public List<User> getAll() {
        return userRepository.findAll();
    }




    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping(value = "/id/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @Valid @RequestBody User updateUser) {
        userService.updateUser(updateUser,id);
        return ResponseEntity.ok(new MessageResponse("User updated successfully!", null,null));
    }


    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!", null,null));

    }


@PostMapping("/send-email")
public String sendEmail() {
    try {
userService.checkPriceAndSendAlerts();
        return "Email sent successfully.";
    } catch (NumberFormatException e) {
        return "Invalid price or budget format.";
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }
}
}


