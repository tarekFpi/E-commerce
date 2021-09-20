package com.example.my_json_review;

public class recycler_modle {
    private String id;
    private String Name;
    private String image;

    public recycler_modle(String id, String name, String image) {
        this.id = id;
        Name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
