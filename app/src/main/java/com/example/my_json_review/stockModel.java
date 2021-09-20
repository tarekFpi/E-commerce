package com.example.my_json_review;

public class stockModel {
    private String bill_id;
    private String product_name;
    private String product_cateogryName;
    private String product_size;
    private int sell_price;
    private int sell_discount;
    private int purchase_quantity;
    private int sell_quantity;
    private int stock;
    private String image_url;
    private String details;

    public stockModel(String bill_id, String product_name, String product_cateogryName, String product_size, int sell_price, int sell_discount, int purchase_quantity, int sell_quantity, int stock, String image_url, String details) {
        this.bill_id = bill_id;
        this.product_name = product_name;
        this.product_cateogryName = product_cateogryName;
        this.product_size = product_size;
        this.sell_price = sell_price;
        this.sell_discount = sell_discount;
        this.purchase_quantity = purchase_quantity;
        this.sell_quantity = sell_quantity;
        this.stock = stock;
        this.image_url = image_url;
        this.details = details;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_cateogryName() {
        return product_cateogryName;
    }

    public void setProduct_cateogryName(String product_cateogryName) {
        this.product_cateogryName = product_cateogryName;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public int getSell_price() {
        return sell_price;
    }

    public void setSell_price(int sell_price) {
        this.sell_price = sell_price;
    }

    public int getSell_discount() {
        return sell_discount;
    }

    public void setSell_discount(int sell_discount) {
        this.sell_discount = sell_discount;
    }

    public int getPurchase_quantity() {
        return purchase_quantity;
    }

    public void setPurchase_quantity(int purchase_quantity) {
        this.purchase_quantity = purchase_quantity;
    }

    public int getSell_quantity() {
        return sell_quantity;
    }

    public void setSell_quantity(int sell_quantity) {
        this.sell_quantity = sell_quantity;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
