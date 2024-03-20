//******************************************************************************
//  AuthenticatorActivity.java
//
//  Zan Owsley T00745703
//  COMP 2161 
//  This abstract class is extended for both activities in the app. It provides
//  method for registering, unregistering the broadcast receiver and requires
//  the subclasses to implement 'respondToTimeTick'.
//******************************************************************************
package com.example.authenticator;

import android.content.Intent;
import android.content.IntentFilter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

public abstract class AuthenticatorActivity extends AppCompatActivity {

    private TimeTickReceiver timeTickReceiver;

    //--------------------------------------------------------------------------
    //  This abstract method will be used by the broadcast receiver.
    //--------------------------------------------------------------------------
    public abstract void respondToTimeTick();

    //--------------------------------------------------------------------------
    //  This method returns an Observer which sets the text of the given
    //  TextView.
    //--------------------------------------------------------------------------
    public Observer<String> createObserver(TextView view) {
        return view::setText;
    }

    //--------------------------------------------------------------------------
    //  This method registers the TimeTickReceiver, which responds to each
    //  minute TIME_TICK.
    //--------------------------------------------------------------------------
    public void registerTimeTickReceiver() {
        timeTickReceiver = new TimeTickReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        int receiverFlags = ContextCompat.RECEIVER_NOT_EXPORTED;
        ContextCompat.registerReceiver(this, timeTickReceiver, filter, receiverFlags);
    }

    //--------------------------------------------------------------------------
    //  This method unregisters the TimeTickReceiver.
    //--------------------------------------------------------------------------
    public void unregisterTimeTickReceiver() {
        unregisterReceiver(timeTickReceiver);
    }
}
