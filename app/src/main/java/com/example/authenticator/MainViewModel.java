//******************************************************************************
//  MainViewModel.java
//
//  Zan Owsley T00745703
//  COMP 2161 
//  Assignment This class is a view model for the main activity. It holds
//  LiveData objects that are observed by TextViews in 'MainActivity'.
//******************************************************************************
package com.example.authenticator;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> passcodeLiveData;
    private final MutableLiveData<String> secondsRemainingLiveData;
    private CountDownTimer countDownTimer;

    //--------------------------------------------------------------------------
    //  A constructor for the class.
    //--------------------------------------------------------------------------
    public MainViewModel() {
        passcodeLiveData = new MutableLiveData<>("");
        secondsRemainingLiveData = new MutableLiveData<>("");
    }

    //--------------------------------------------------------------------------
    //  A getter for the 'passcodeLiveData' variable.
    //--------------------------------------------------------------------------
    public MutableLiveData<String> getPasscodeLiveData() {
        return passcodeLiveData;
    }

    //--------------------------------------------------------------------------
    //  A setter for the underlying String in 'passcodeLiveData'.
    //--------------------------------------------------------------------------
    public void setPasscodeLiveData(String passcode) {
        passcodeLiveData.setValue(passcode);
    }

    //--------------------------------------------------------------------------
    //  A getter for the 'secondsremainingLiveData' variable.
    //--------------------------------------------------------------------------
    public MutableLiveData<String> getSecondsRemainingLiveData() {
        return secondsRemainingLiveData;
    }

    //--------------------------------------------------------------------------
    //  This method starts a CountdownTimer objects for the given number of
    //  seconds.
    //--------------------------------------------------------------------------
    public void startCountdown(int seconds) {

	// If the CountDownTimer object had not finished, cancel it.
        if (countDownTimer != null)
            countDownTimer.cancel();

	// On each tick of the CountDownTimer, set the 'secondsremainingLiveData'
	// value to match.
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
