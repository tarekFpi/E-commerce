package com.example.my_json_review;

public class product_categoryModel {
    private String category_id;
    private String category_name;
    private String category_date;

    public product_categoryModel(String category_id, String category_name, String category_date) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_date = category_date;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_date() {
        return category_date;
    }

    public void setCategory_date(String category_date) {
        this.category_date = category_date;
    }
}
