package com.cheetah.setu.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


public class RoRegularTextView extends AppCompatTextView {


    public RoRegularTextView(@NonNull Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/roboto_regular.ttf");
        this.setTypeface(face);
    }

    public RoRegularTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/roboto_regular.ttf");
        this.setTypeface(face);
    }

    public RoRegularTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/roboto_regular.ttf");
        this.setTypeface(face);
    }


}
