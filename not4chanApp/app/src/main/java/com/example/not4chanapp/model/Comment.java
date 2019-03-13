package com.example.not4chanapp.model;

public class Comment {

    private User user;
    private Photo photo;
    private Comment responseTo;
    private String content;

    public Comment() {
    }

    public Comment(User user, Photo photo, Comment responseTo, String content) {
        this.user = user;
        this.photo = photo;
        this.responseTo = responseTo;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Comment getResponseTo() {
        return responseTo;
    }

    public void setResponseTo(Comment responseTo) {
        this.responseTo = responseTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", photo=" + photo +
                ", responseTo=" + responseTo +
                ", content='" + content + '\'' +
                '}';
    }
}
