package com.example.my_json_review;

public class Order_Except_Model {


    private String serial_id;
    private String user_name;
    private String user_email;
    private String user_phon;
    private int prod_TotalPrice;
    private String order_date;
    private String except_date;
    private int Shipping_rate;

    private String Order_status;
    private String Region_name;

    private String pd_id;
    private String pd_name;
    private int pd_price;
    private int sell_quanitiy;
    private int discount;
    private String pd_size;
    private String category_name;
    private String messages;

    public Order_Except_Model(String serial_id, String user_name, String user_email, String user_phon, int prod_TotalPrice, String order_date, String except_date, int shipping_rate, String order_status, String region_name, String pd_id, String pd_name, int pd_price, int sell_quanitiy, int discount, String pd_size, String category_name, String messages) {
        this.serial_id = serial_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_phon = user_phon;
        this.prod_TotalPrice = prod_TotalPrice;
        this.order_date = order_date;
        this.except_date = except_date;
        this.Shipping_rate = shipping_rate;
        this.Order_status = order_status;
        this.Region_name = region_name;
        this.pd_id = pd_id;
        this.pd_name = pd_name;
        this.pd_price = pd_price;
        this.sell_quanitiy = sell_quanitiy;
        this.discount = discount;
        this.pd_size = pd_size;
        this.category_name = category_name;
        this.messages = messages;
    }


    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getExcept_date() {
        return except_date;
    }

    public void setExcept_date(String except_date) {
        this.except_date = except_date;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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



    public int getShipping_rate() {
        return Shipping_rate;
    }

    public void setShipping_rate(int shipping_rate) {
        Shipping_rate = shipping_rate;
    }


}
