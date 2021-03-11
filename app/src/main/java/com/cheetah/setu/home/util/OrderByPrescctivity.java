package com.cheetah.setu.home.util;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheetah.setu.R;
import com.cheetah.setu.main.Func;

public class OrderByPrescctivity extends AppCompatActivity implements View.OnClickListener {
    ImageView tandc;
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_by_prescctivity);

        tandc = findViewById(R.id.info_t_and_c);
        submit = findViewById(R.id.submit);
        tandc.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.info_t_and_c:
                Func.showToast(OrderByPrescctivity.this, "T&C");
                break;
            case R.id.submit:
                Func.showToast(OrderByPrescctivity.this, "SUBMIT");
                break;
        }
    }
}