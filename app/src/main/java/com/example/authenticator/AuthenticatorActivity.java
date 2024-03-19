package com.example.authenticator;

import android.content.Intent;
import android.content.IntentFilter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

public abstract class AuthenticatorActivity extends AppCompatActivity {

    private TimeTickReceiver timeTickReceiver;
    public abstract void respondToTimeTick();

    public Observer<String> createObserver(TextView view) {
        return view::setText;
    }

    public void registerTimeTickReceiver() {
        timeTickReceiver = new TimeTickReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        int receiverFlags = ContextCompat.RECEIVER_NOT_EXPORTED;
        ContextCompat.registerReceiver(this, timeTickReceiver, filter, receiverFlags);
    }

    public void unregisterTimeTickReceiver() {
        unregisterReceiver(timeTickReceiver);
    }
}
