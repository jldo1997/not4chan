package com.example.a3chan.firebase;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class FirebaseUser {

    public String email;
    public List<FirebaseChat> chats;

    public FirebaseUser() {
    }

    public FirebaseUser(String email) {
        this.email = email;
        this.chats = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<FirebaseChat> getChats() {
        return chats;
    }

    public void setChats(List<FirebaseChat> chats) {
        this.chats = chats;
    }
}
