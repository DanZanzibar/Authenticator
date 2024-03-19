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

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.setPasscodeLiveData(AuthenticatorUtils.getCurrentPasscode());
        viewModel.startCountdown(AuthenticatorUtils.getRemainingSecondsUntilTimeTick());
        registerTimeTickReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterTimeTickReceiver();
    }

    @Override
    public void respondToTimeTick() {
        viewModel.setPasscodeLiveData(AuthenticatorUtils.getCurrentPasscode());
        viewModel.startCountdown(60);
        sharedPreferences.addTimeStamp(AuthenticatorUtils.getCurrentTimeStamp());
    }

    private void navigateToLogActivity() {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }
}