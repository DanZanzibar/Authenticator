package com.example.authenticator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeTickReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context instanceof AuthenticatorActivity) {
            AuthenticatorActivity activity = (AuthenticatorActivity) context;
            activity.respondToTimeTick();
        }
    }
}
