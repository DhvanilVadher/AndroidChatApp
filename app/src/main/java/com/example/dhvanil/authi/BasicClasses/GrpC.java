package com.example.dhvanil.authi.BasicClasses;

public class GrpC {
    public  String id;
    public String name;
    public Boolean isChecked;

    public GrpC( String id, String name, Boolean isChecked ) {
        this.id = id;
        this.name = name;
        this.isChecked = isChecked;
    }

    public GrpC() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setChecked( Boolean checked ) {
        isChecked = checked;
    }
}
