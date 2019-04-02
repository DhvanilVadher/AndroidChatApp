package com.example.dhvanil.authi.BasicClasses;

public class User  {
    private String id;
    private String name;
    private String ImageUrl;

    public User( String id, String username, String URL ) {
        this.id = id;
        this.name = username;
        this.ImageUrl = URL;
    }
    public User(){}
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname( String username ) {
        this.name = username;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl( String URL ) {
        this.ImageUrl = ImageUrl;
    }
}
