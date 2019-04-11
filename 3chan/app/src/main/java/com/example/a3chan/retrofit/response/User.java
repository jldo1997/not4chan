package com.example.a3chan.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("picture")
        @Expose
        private String picture;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("favThreads")
        @Expose
        private List<Object> favThreads = null;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;

        /**
         * No args constructor for use in serialization
         *
         */
        public User() {
        }

        /**
         *
         * @param picture
         * @param id
         * @param email
         * @param createdAt
         * @param name
         * @param role
         * @param favThreads
         */
        public User(String id, String name, String picture, String email, List<Object> favThreads, String role, String createdAt) {
            super();
            this.id = id;
            this.name = name;
            this.picture = picture;
            this.email = email;
            this.favThreads = favThreads;
            this.role = role;
            this.createdAt = createdAt;
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

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<Object> getFavThreads() {
            return favThreads;
        }

        public void setFavThreads(List<Object> favThreads) {
            this.favThreads = favThreads;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
}
