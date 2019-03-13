package com.example.not4chanapp.model.DTO;

public class UserDTO {

    private String email;
    private String password;
    private String name;
    private String picture;
    private String role;


    public UserDTO(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = "";
        this.role = "user";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
