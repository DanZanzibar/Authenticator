package com.example.authenticator;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> passcodeLiveData;
    private final MutableLiveData<String> secondsRemainingLiveData;
    private CountDownTimer countDownTimer;

    public MainViewModel() {
        passcodeLiveData = new MutableLiveData<>("");
        secondsRemainingLiveData = new MutableLiveData<>("");
    }

    public MutableLiveData<String> getPasscodeLiveData() {
        return passcodeLiveData;
    }

    public void setPasscodeLiveData(String passcode) {
        passcodeLiveData.setValue(passcode);
    }

    public MutableLiveData<String> getSecondsRemainingLiveData() {
        return secondsRemainingLiveData;
    }

    public void startCountdown(int seconds) {
        if (countDownTimer != null)
            countDownTimer.cancel();
        countDownTimer = new CountDownTimer(seconds * 1000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secondsRemainingLiveData.setValue((millisUntilFinished / 1000) + " seconds remaining.");
            }

            @Override
            public void onFinish() {}
        };

        countDownTimer.start();
    }
}
