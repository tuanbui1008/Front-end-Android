package com.btl.bookingroom.model;

public class CommentOfCustomer {
    private int id;
    private String fullName;
    private String avatar;
    private String content;


    public CommentOfCustomer(int id, String fullName, String avatar, String content) {
        this.id = id;
        this.fullName = fullName;
        this.avatar = avatar;
        this.content = content;
    }

    public CommentOfCustomer(String fullName, String avatar, String content) {
        this.fullName = fullName;
        this.avatar = avatar;
        this.content = content;
    }

    public CommentOfCustomer(String avatar, String content) {
        this.avatar = avatar;
        this.content = content;
    }

    public CommentOfCustomer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
