package com.example.dhvanil.authi;

import android.support.v4.view.PagerAdapter;

public class ChatClass extends Chat {
    private boolean senderOfNot;

    public ChatClass( String sender, String receiever, String message, boolean senderOfNot ) {
        super( sender, receiever, message );
        this.senderOfNot = senderOfNot;
    }

    public ChatClass( String sender, String receiever, String message ) {
        super( sender, receiever, message );
    }

    public boolean isSenderOfNot() {
        return senderOfNot;
    }

    public void setSenderOfNot( boolean senderOfNot ) {
        this.senderOfNot = senderOfNot;
    }
}
