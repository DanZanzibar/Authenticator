//******************************************************************************
//  LogActivity.java
//
//  Zan Owsley T00745703
//  COMP 2161 Assignment 
//  This class represents the activity responsible for displaying the time stamp
//  log.
//******************************************************************************
package com.example.authenticator;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

import androidx.annotation.Nullable;

public class LogActivity extends AuthenticatorActivity {

    private AuthenticatorSharedPreferences sharedPreferences;

    //--------------------------------------------------------------------------
    //  This 'onCreate' method sets up the clear button and the
    //  'AuthenticatorSharedPreferences' variable.
    //--------------------------------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        sharedPreferences = new AuthenticatorSharedPreferences(this);
        generateList(sharedPreferences.getLog());

        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(v -> clearButtonPush());
    }

    //--------------------------------------------------------------------------
    //  This 'onResume' method registers the 'TimeTickReceiver'.
    //--------------------------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        registerTimeTickReceiver();
    }

    //--------------------------------------------------------------------------
    //  This 'onPause' method unregisters the 'TimeTickReceiver'.
    //--------------------------------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        unregisterTimeTickReceiver();
    }

    //--------------------------------------------------------------------------
    //  This method adds a time stamp to data storage and regenerates the time
    //  stamp list.
    //--------------------------------------------------------------------------
    @Override
    public void respondToTimeTick() {
        sharedPreferences.addTimeStamp(AuthenticatorUtils.getCurrentTimeStamp());
        generateList(sharedPreferences.getLog());
    }

    //--------------------------------------------------------------------------
    //  This method generates the ListView that displays the time stamps.
    //--------------------------------------------------------------------------
    private void generateList(String log) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.list_item,
                getLogArray(log)
        );
        ListView listView = findViewById(R.id.log_list);
        listView.setAdapter(adapter);
    }

    //--------------------------------------------------------------------------
    //  This method returns the stored time stamp data as an array for the
    //  ListView to use.
    //--------------------------------------------------------------------------
    private String[] getLogArray(String log) {
        String[] logArray;
        if (log.contains("\n"))
            logArray = log.split("\n");
        else
            logArray = new String[] {log};

        return logArray;
    }

    //--------------------------------------------------------------------------
    //  This method clears the stored time stamps (except the most recent) and
    //  regenerates the displayed ListView.
    //--------------------------------------------------------------------------
    private void clearButtonPush() {
        sharedPreferences.clearAllButLastTimeStamp();
        generateList(sharedPreferences.getLog());
    }
}
