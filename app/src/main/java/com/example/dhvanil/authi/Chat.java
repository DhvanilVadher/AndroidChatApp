package com.example.dhvanil.authi;

public class Chat {
    private String sender;
    private String receiever;
    private String message;

    public Chat( String sender, String receiever, String message ) {
        sender = sender;
        receiever = receiever;
        message = message;
    }
    public Chat(){}
    public String getSender() {
        return sender;
    }

    public void setSender( String sender ) {
        this.sender = sender;
    }

    public String getReceiever() {
        return receiever;
    }

    public void setReceiever( String receiever ) {
        this.receiever = receiever;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }
}
