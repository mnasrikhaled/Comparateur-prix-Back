package com.Pfe.Comparateur.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    @Size(max = 20)
    private String username;


    @Size(max = 50)
    @Email
    @Indexed(unique = true)
    private String email;


    @Size(max = 120)
    @Indexed(unique = true)
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();
    private User_alert user_alert;




    public User(String username, String email, String password) {

        this.username = username;
        this.email = email;
        this.password = password;

    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




        public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}