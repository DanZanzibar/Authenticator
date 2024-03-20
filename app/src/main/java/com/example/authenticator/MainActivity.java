//******************************************************************************
//  MainActivity.java
//
//  Zan Owsley T00745703
//  COMP 2161 
//  This class represents the main screen of the app, where a passcode is
//  displayed.
//******************************************************************************
package com.example.authenticator;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AuthenticatorActivity {

    private MainViewModel viewModel;
    private AuthenticatorSharedPreferences sharedPreferences;

    //--------------------------------------------------------------------------
    //  This 'onCreate' method handles setting up the TextView's to observe the
    //  LiveData in the view model, as well as the button to go to the
    //  LogActivity.
    //--------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = new AuthenticatorSharedPreferences(this);
        sharedPreferences.clearAllButLastTimeStamp();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        TextView secondsRemaining = findViewById(R.id.seconds_remaining);
        Observer<String> secondsObserver = createObserver(secondsRemaining);
        viewModel.getSecondsRemainingLiveData().observe(this, secondsObserver);

        TextView passcode = findViewById(R.id.passcode_value);
        Observer<String> passcodeObserver = createObserver(passcode);
        viewModel.getPasscodeLiveData().observe(this, passcodeObserver);

        Button verifyButton = findViewById(R.id.verify_button);
        verifyButton.setOnClickListener(v -> navigateToLogActivity());
    }

    //--------------------------------------------------------------------------
    //  This 'onResume' method triggers the view model to generate the current
    //  password and start the countdown. It also registers the
    //  TimeTickReceiver.
    //--------------------------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        viewModel.setPasscodeLiveData(AuthenticatorUtils.getCurrentPasscode());
        viewModel.startCountdown(AuthenticatorUtils.getRemainingSecondsUntilTimeTick());
        registerTimeTickReceiver();
    }

    //--------------------------------------------------------------------------
    //  This 'onPause' method unregisters the TimeTickReceiver.
    //--------------------------------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        unregisterTimeTickReceiver();
    }

    //--------------------------------------------------------------------------
    //  This method is invoked by the broadcast receiver. It updates the pass
    //  code, stores the new time stamp, and restarts the countdown.
    //--------------------------------------------------------------------------
    @Override
    public void respondToTimeTick() {
        viewModel.setPasscodeLiveData(AuthenticatorUtils.getCurrentPasscode());
        viewModel.startCountdown(60);
        sharedPreferences.addTimeStamp(AuthenticatorUtils.getCurrentTimeStamp());
    }

    //--------------------------------------------------------------------------
    //  This method starts LogActivity.
    //--------------------------------------------------------------------------
    private void navigateToLogActivity() {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }
}
