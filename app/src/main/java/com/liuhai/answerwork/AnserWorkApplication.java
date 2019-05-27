package com.liuhai.answerwork;

import android.app.Application;
import android.text.NoCopySpan;

import com.liuhai.answerwork.dateutils.Httputils;

public class AnserWorkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Httputils.getInstance(this);

    }
}
