package com.example.my_json_review;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class pd_Category_showModel  {
    private String pid;
    private String pdName;
    private String categoryName;
    private int selling_price;
    private int pd_discount;
    private int sell_quanitiy;
    private String pdImage;
    private String pdSize;
   // private int pd_stock;
    private String user_gmail;


    public pd_Category_showModel() {
    }

    public pd_Category_showModel(String pid, String pdName, String categoryName, int selling_price, int pd_discount, int sell_quanitiy, String pdImage, String pdSize, String user_gmail) {
        this.pid = pid;
        this.pdName = pdName;
        this.categoryName = categoryName;
        this.selling_price = selling_price;
        this.pd_discount = pd_discount;
        this.sell_quanitiy = sell_quanitiy;
        this.pdImage = pdImage;
        this.pdSize = pdSize;
      //  this.pd_stock = pd_stock;
        this.user_gmail = user_gmail;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUser_gmail() {
        return user_gmail;
    }

    public void setUser_gmail(String user_gmail) {
        this.user_gmail = user_gmail;
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

    public String getPdSize() {
        return pdSize;
    }

    public void setPdSize(String pdSize) {
        this.pdSize = pdSize;
    }
}

