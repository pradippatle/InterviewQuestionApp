package com.pradipatle.androidkt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pradipatle.androidkt.Android.AndroidActivity;
import com.pradipatle.androidkt.Java.JavaActivity;

/**
 * Created by Aeon-02 on 10/9/2017.
 */

public class MainHomeAcitity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout MenuAndroid, MenuJava;
    private AdView mAdView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_acivity);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        MenuAndroid = (LinearLayout)findViewById(R.id.id_android_menu);
        MenuJava = (LinearLayout)findViewById(R.id.id_java_menu);

        MenuAndroid.setOnClickListener(this);
        MenuJava.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.id_android_menu :
                Intent iAndroid = new Intent(MainHomeAcitity.this, AndroidActivity.class);
                startActivity(iAndroid);
                break;

            case R.id.id_java_menu :
                Intent iJava = new Intent(MainHomeAcitity.this, JavaActivity.class);
                startActivity(iJava);
                break;
        }
    }
}
