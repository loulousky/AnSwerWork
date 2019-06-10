package com.liuhai.answerwork;

import android.app.Application;
import android.text.NoCopySpan;

import com.liuhai.answerwork.dateutils.Httputils;

import org.scilab.forge.jlatexmath.core.AjLatexMath;

import io.github.kbiakov.codeview.classifier.CodeProcessor;

public class AnserWorkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Httputils.getInstance(this);
        AjLatexMath.init(this);

    }
}
