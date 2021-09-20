package com.example.my_json_review;

public class Admin_infoModel {
    private String serial_id;
    private String user_name;
    private String gmail;
    private String password;

    public Admin_infoModel(String serial_id, String user_name, String gmail, String password) {
        this.serial_id = serial_id;
        this.user_name = user_name;
        this.gmail = gmail;
        this.password = password;
    }

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
