package com.example.my_json_review;

public class AllUser_singUp_Model {
    private String user_serial;
    private String user_name;
    private String user_gmail;
    private String user_password;

    public AllUser_singUp_Model(String user_serial, String user_name, String user_gmail, String user_password) {
        this.user_serial = user_serial;
        this.user_name = user_name;
        this.user_gmail = user_gmail;
        this.user_password = user_password;
    }

    public String getUser_serial() {
        return user_serial;
    }

    public void setUser_serial(String user_serial) {
        this.user_serial = user_serial;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_gmail() {
        return user_gmail;
    }

    public void setUser_gmail(String user_gmail) {
        this.user_gmail = user_gmail;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
