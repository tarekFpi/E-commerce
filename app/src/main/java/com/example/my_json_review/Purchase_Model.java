package com.example.my_json_review;

public class Purchase_Model {
    private String Bill;
    private String CategoryName;
    private String pid;
    private String pdName;
    private int stock;
    private int pd_Price;
    private int selling_price;
    private int pd_discount;
    private int purchase_quanitiy;
    private int sell_quanitiy;
    private String pdImage;
    private String pdDeatils;
    private String pdSize;

    public Purchase_Model() {
    }

    public Purchase_Model(String bill, String categoryName, String pid, String pdName, int stock, int pd_Price, int selling_price, int pd_discount, int purchase_quanitiy, int sell_quanitiy, String pdImage, String pdDeatils, String pdSize) {
        Bill = bill;
        CategoryName = categoryName;
        this.pid = pid;
        this.pdName = pdName;
        this.stock = stock;
        this.pd_Price = pd_Price;
        this.selling_price = selling_price;
        this.pd_discount = pd_discount;
        this.purchase_quanitiy = purchase_quanitiy;
        this.sell_quanitiy = sell_quanitiy;
        this.pdImage = pdImage;
        this.pdDeatils = pdDeatils;
        this.pdSize = pdSize;
    }

    public String getBill() {
        return Bill;
    }

    public void setBill(String bill) {
        Bill = bill;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPd_Price() {
        return pd_Price;
    }

    public void setPd_Price(int pd_Price) {
        this.pd_Price = pd_Price;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getPd_discount() {
        return pd_discount;
    }

    public void setPd_discount(int pd_discount) {
        this.pd_discount = pd_discount;
    }

    public int getPurchase_quanitiy() {
        return purchase_quanitiy;
    }

    public void setPurchase_quanitiy(int purchase_quanitiy) {
        this.purchase_quanitiy = purchase_quanitiy;
    }

    public int getSell_quanitiy() {
        return sell_quanitiy;
    }

    public void setSell_quanitiy(int sell_quanitiy) {
        this.sell_quanitiy = sell_quanitiy;
    }

    public String getPdImage() {
        return pdImage;
    }

    public void setPdImage(String pdImage) {
        this.pdImage = pdImage;
    }

    public String getPdDeatils() {
        return pdDeatils;
    }

    public void setPdDeatils(String pdDeatils) {
        this.pdDeatils = pdDeatils;
    }

    public String getPdSize() {
        return pdSize;
    }

    public void setPdSize(String pdSize) {
        this.pdSize = pdSize;
    }
}
