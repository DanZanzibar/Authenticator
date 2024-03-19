package com.example.authenticator;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthenticatorSharedPreferences {

    private static final String FILE_NAME = "com.example.authenticator.shared_preferences";
    private static final String PREF_NAME = "log_stamps";
    private final SharedPreferences sharedPreferences;

    public AuthenticatorSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void addTimeStamp(String timeStamp) {
        String currentLog = getLog();
        setLog(currentLog + "\n" + timeStamp);
    }

    public void clearAllButLastTimeStamp() {
        String currentLog = getLog();
        String newLog;

        if (currentLog.contains("\n")) {
            String[] currentLogArray = currentLog.split("\n");
            newLog = currentLogArray[currentLogArray.length - 1];
        }
        else
            newLog = currentLog;

        setLog(newLog);
    }

    public String getLog() {
        return sharedPreferences.getString(PREF_NAME, "");
    }

    private void setLog(String log) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_NAME, log);
        editor.apply();
    }
}
