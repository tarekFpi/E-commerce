package com.example.my_json_review;

public class purchase_orderShow_Model {
    private String serial_id;
    private String buill_no;
    private String  product_name;
    private String categoryName;
    private int purchase_quantity;
    private int purchase_price;
    private int sell_price;
    private String supplayer_name;
    private int sell_discount;
    private int puchase_discount;
    private String product_deatils;
    private String product_size;
    private String image_url;

    public purchase_orderShow_Model(String serial_id, String buill_no, String product_name, String categoryName, int purchase_quantity, int purchase_price, int sell_price, String supplayer_name, int sell_discount, int puchase_discount, String product_deatils, String product_size, String image_url) {
        this.serial_id = serial_id;
        this.buill_no = buill_no;
        this.product_name = product_name;
        this.categoryName = categoryName;
        this.purchase_quantity = purchase_quantity;
        this.purchase_price = purchase_price;
        this.sell_price = sell_price;
        this.supplayer_name = supplayer_name;
        this.sell_discount = sell_discount;
        this.puchase_discount = puchase_discount;
        this.product_deatils = product_deatils;
        this.product_size = product_size;
        this.image_url = image_url;
    }

    public int getPurchase_quantity() {
        return purchase_quantity;
    }

    public void setPurchase_quantity(int purchase_quantity) {
        this.purchase_quantity = purchase_quantity;
    }

    public int getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(int purchase_price) {
        this.purchase_price = purchase_price;
    }

    public int getSell_price() {
        return sell_price;
    }

    public void setSell_price(int sell_price) {
        this.sell_price = sell_price;
    }

    public String getSupplayer_name() {
        return supplayer_name;
    }

    public void setSupplayer_name(String supplayer_name) {
        this.supplayer_name = supplayer_name;
    }

    public int getSell_discount() {
        return sell_discount;
    }

    public void setSell_discount(int sell_discount) {
        this.sell_discount = sell_discount;
    }

    public int getPuchase_discount() {
        return puchase_discount;
    }

    public void setPuchase_discount(int puchase_discount) {
        this.puchase_discount = puchase_discount;
    }

    public String getBuill_no() {
        return buill_no;
    }

    public void setBuill_no(String buill_no) {
        this.buill_no = buill_no;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProduct_deatils() {
        return product_deatils;
    }

    public void setProduct_deatils(String product_deatils) {
        this.product_deatils = product_deatils;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }
}
