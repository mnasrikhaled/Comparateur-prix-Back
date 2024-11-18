package com.Pfe.Comparateur.Response;

public class MessageResponse {
    private String message;
    private String username; // Ajout du nom d'utilisateur
    private String id;


    public MessageResponse(String message, String username,String id) {
        this.message = message;
        this.username = username;
        this.id=id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
