package com.github.ahmadaghazadeh.sample;

import android.app.Application;

public class App extends Application {

    private Thread.UncaughtExceptionHandler defaultHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this::handleUncaughtException);
    }

    public void handleUncaughtException(Thread thread, Throwable e) {
        StackTraceElement[] arr = e.getStackTrace();
        final StringBuffer report = new StringBuffer(e.toString());
        final String lineSeperator = "-------------------------------\n\n";
        report.append("--------- Stack trace ---------\n\n");
        for (int i = 0; i < arr.length; i++) {
            report.append("    ");
            report.append(arr[i].toString());
            report.append(lineSeperator);
        }
        int x=0;
        defaultHandler.uncaughtException(thread, e);
    }

}
