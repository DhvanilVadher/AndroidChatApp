package com.example.dhvanil.authi;

public class Chat {
    private String Sender;
    private String Receiever;
    private String Message;

    public Chat( String sender, String receiever, String message ) {
        Sender = sender;
        Receiever = receiever;
        Message = message;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender( String sender ) {
        Sender = sender;
    }

    public String getReceiever() {
        return Receiever;
    }

    public void setReceiever( String receiever ) {
        Receiever = receiever;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage( String message ) {
        Message = message;
    }
}
