package com.example.a3chan.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCreateThread {
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("headerComment")
    @Expose
    private String headerComment;
    @SerializedName("title")
    @Expose
    private String title;

    public RequestCreateThread(String category, String headerComment, String title) {
        this.category = category;
        this.headerComment = headerComment;
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHeaderComment() {
        return headerComment;
    }

    public void setHeaderComment(String headerComment) {
        this.headerComment = headerComment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
