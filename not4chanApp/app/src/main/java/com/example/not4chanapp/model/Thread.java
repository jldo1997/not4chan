package com.example.not4chanapp.model;

import java.util.List;

public class Thread {

    private Category category;
    private List<Comment> comments;
    private Comment headerComment;
    private String title;

    public Thread() {
    }

    public Thread(Category category, List<Comment> comments, Comment headerComment, String title) {
        this.category = category;
        this.comments = comments;
        this.headerComment = headerComment;
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Comment getHeaderComment() {
        return headerComment;
    }

    public void setHeaderComment(Comment headerComment) {
        this.headerComment = headerComment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "category=" + category +
                ", comments=" + comments +
                ", headerComment=" + headerComment +
                ", title='" + title + '\'' +
                '}';
    }
}
