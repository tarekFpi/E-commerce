package com.example.my_json_review;

public class User_order_showModel {
    private String serial_id;
    private String user_name;
    private String user_email;
    private String user_location;
    private String user_flat_no;
    private String user_city;
    private String user_phon;
    private int prod_TotalPrice;
    private String current_date;
    private int Shipping_rate;

    private String Order_status;
    private String Region_name;

    private String pd_id;
    private String pd_name;
    private int pd_price;
    private int sell_quanitiy;
    private int discount;
    private String pd_size;
    private String image_url;
    private String category_name;

    public User_order_showModel(String serial_id, String user_name, String user_email, String user_location, String user_flat_no, String user_city, String user_phon, int prod_TotalPrice, String current_date, int shipping_rate, String order_status, String region_name, String pd_id, String pd_name, int pd_price, int sell_quanitiy, int discount, String pd_size, String image_url, String category_name) {
        this.serial_id = serial_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_location = user_location;
        this.user_flat_no = user_flat_no;
        this.user_city = user_city;
        this.user_phon = user_phon;
        this.prod_TotalPrice = prod_TotalPrice;
        this.current_date = current_date;
        Shipping_rate = shipping_rate;
        Order_status = order_status;
        Region_name = region_name;
        this.pd_id = pd_id;
        this.pd_name = pd_name;
        this.pd_price = pd_price;
        this.sell_quanitiy = sell_quanitiy;
        this.discount = discount;
        this.pd_size = pd_size;
        this.image_url = image_url;
        this.category_name = category_name;
    }

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getOrder_status() {
        return Order_status;
    }

    public void setOrder_status(String order_status) {
        Order_status = order_status;
    }

    public String getPd_id() {
        return pd_id;
    }

    public void setPd_id(String pd_id) {
        this.pd_id = pd_id;
    }

    public String getPd_name() {
        return pd_name;
    }

    public void setPd_name(String pd_name) {
        this.pd_name = pd_name;
    }

    public int getPd_price() {
        return pd_price;
    }

    public void setPd_price(int pd_price) {
        this.pd_price = pd_price;
    }

    public int getSell_quanitiy() {
        return sell_quanitiy;
    }

    public void setSell_quanitiy(int sell_quanitiy) {
        this.sell_quanitiy = sell_quanitiy;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPd_size() {
        return pd_size;
    }

    public void setPd_size(String pd_size) {
        this.pd_size = pd_size;
    }

    public String getRegion_name() {
        return Region_name;
    }

    public void setRegion_name(String region_name) {
        Region_name = region_name;
    }



    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_location() {
        return user_location;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public String getUser_flat_no() {
        return user_flat_no;
    }

    public void setUser_flat_no(String user_flat_no) {
        this.user_flat_no = user_flat_no;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_phon() {
        return user_phon;
    }

    public void setUser_phon(String user_phon) {
        this.user_phon = user_phon;
    }

    public int getProd_TotalPrice() {
        return prod_TotalPrice;
    }

    public void setProd_TotalPrice(int prod_TotalPrice) {
        this.prod_TotalPrice = prod_TotalPrice;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public int getShipping_rate() {
        return Shipping_rate;
    }

    public void setShipping_rate(int shipping_rate) {
        Shipping_rate = shipping_rate;
    }


}
