package com.example.not4chanapp.model;

import java.util.List;

public class User {

    private String id;
    private String email;
    private String name;
    private String role;
    private String picture;
    private List<Thread> favThreads;

    public User() {
    }

    public User(String id, String email, String name, String role, String picture, List<Thread> favThreads) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.picture = picture;
        this.favThreads = favThreads;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPict() {
        return picture;
    }

    public void setPict(String picture) {
        this.picture = picture;
    }

    public List<Thread> getFavThreads() {
        return favThreads;
    }

    public void setFavThreads(List<Thread> favThreads) {
        this.favThreads = favThreads;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", pict='" + picture + '\'' +
                ", favThreads=" + favThreads +
                '}';
    }
}
