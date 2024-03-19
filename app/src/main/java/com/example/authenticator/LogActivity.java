package com.example.authenticator;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

import androidx.annotation.Nullable;

public class LogActivity extends AuthenticatorActivity {

    private AuthenticatorSharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        sharedPreferences = new AuthenticatorSharedPreferences(this);
        generateList(sharedPreferences.getLog());

        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(v -> clearButtonPush());
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerTimeTickReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterTimeTickReceiver();
    }

    @Override
    public void respondToTimeTick() {
        sharedPreferences.addTimeStamp(AuthenticatorUtils.getCurrentTimeStamp());
        generateList(sharedPreferences.getLog());
    }

    private void generateList(String log) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.list_item,
                getLogArray(log)
        );
        ListView listView = findViewById(R.id.log_list);
        listView.setAdapter(adapter);
    }

    private String[] getLogArray(String log) {
        String[] logArray;
        if (log.contains("\n"))
            logArray = log.split("\n");
        else
            logArray = new String[] {log};

        return logArray;
    }

    private void clearButtonPush() {
        sharedPreferences.clearAllButLastTimeStamp();
        generateList(sharedPreferences.getLog());
    }
}
