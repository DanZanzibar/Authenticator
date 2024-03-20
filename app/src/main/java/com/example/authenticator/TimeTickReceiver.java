//******************************************************************************
//  TimeTickReceiver.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 
//  This class extends 'BroadcastReceiver' for the specific purposes of this app.
//******************************************************************************
package com.example.authenticator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeTickReceiver extends BroadcastReceiver {

    //--------------------------------------------------------------------------
    //  This 'onReceive' method invokes the containing AuthenticatorActivity's
    //  'respondToTimeTick' method.
    //--------------------------------------------------------------------------
    @Override
    public void onReceive(Context context, Intent intent) {
        if (context instanceof AuthenticatorActivity) {
            AuthenticatorActivity activity = (AuthenticatorActivity) context;
            activity.respondToTimeTick();
        }
    }
}
