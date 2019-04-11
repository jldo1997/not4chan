package com.example.a3chan.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCreateComment {

    @SerializedName("responseTo")
    @Expose
    private String responseTo;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("threadId")
    @Expose
    private String threadId;

    public RequestCreateComment(String responseTo, String content, String threadId) {
        this.responseTo = responseTo;
        this.content = content;
        this.threadId = threadId;
    }

    public RequestCreateComment(String content, String threadId) {
        this.content = content;
        this.threadId = threadId;
    }

    public String getResponseTo() {
        return responseTo;
    }

    public void setResponseTo(String responseTo) {
        this.responseTo = responseTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
}
