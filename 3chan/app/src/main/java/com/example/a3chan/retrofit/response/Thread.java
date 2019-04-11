package com.example.a3chan.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Thread {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = null;
    @SerializedName("headerComment")
    @Expose
    private Comment headerComment;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Thread() {
    }

    public Thread(String id, Category category, List<Comment> comments, Comment headerComment, String title, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.category = category;
        this.comments = comments;
        this.headerComment = headerComment;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "id='" + id + '\'' +
                ", category=" + category +
                ", comments=" + comments +
                ", headerComment=" + headerComment +
                ", title='" + title + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
