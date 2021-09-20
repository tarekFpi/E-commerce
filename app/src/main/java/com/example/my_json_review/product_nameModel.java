package com.example.my_json_review;

public class product_nameModel {
    private String serial_id;
    private String Name;
    private String Add_date;

    public product_nameModel(String serial_id, String name, String add_date) {
        this.serial_id = serial_id;
        Name = name;
        Add_date = add_date;
    }

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdd_date() {
        return Add_date;
    }

    public void setAdd_date(String add_date) {
        Add_date = add_date;
    }
}
