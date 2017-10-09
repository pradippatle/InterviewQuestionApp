package com.pradipatle.androidkt.Java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.pradipatle.androidkt.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class JavaActivity extends AppCompatActivity {
    private String jsonResponse = null;
    private ArrayList<JavaModel> dataArrayList;
    private JavaAdapter dataAdapter;
    private ListView listview;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
       // AdRequest.Builder.addTestDevice("E47072C8448D4D9F8D8DF8C31C3ECECE");
        mInterstitialAd = new InterstitialAd(this);


        /*AdRequest request = new AdRequest.Builder()
                .addTestDevice("E47072C8448D4D9F8D8DF8C31C3ECECE")
                .build();*/

        getSupportActionBar().setHomeButtonEnabled(true);
        listview = (ListView) findViewById(R.id.listview);
        dataArrayList = new ArrayList<>();
        loadJSONFromAsset();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed");
                onBackPressed();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                    onBackPressed();
                }
                //onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String loadJSONFromAsset() {
        try {
            InputStream is = getAssets().open("java.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonResponse = new String(buffer, "UTF-8");
            Log.i("Log", "JSON RESPONSE " + jsonResponse);
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(jsonResponse);
                JSONArray dataArray = jsonObj.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JavaModel md = new JavaModel();
                    JSONObject jsonObject = dataArray.getJSONObject(i);
                    String SrNum = jsonObject.getString("sr_number");
                    String Question = jsonObject.getString("question");
                    String Answer = jsonObject.getString("answer");

                    md.serial_num = SrNum;
                    md.question = Question;
                    md.answer = Answer;
                    dataArrayList.add(md);
                }
                if (dataArrayList.size() > 0) {
                    dataAdapter = new JavaAdapter(dataArrayList, JavaActivity.this);
                    listview.setAdapter(dataAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonResponse;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mInterstitialAd.setAdUnitId("ca-app-pub-7240646173515438/3832843271");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
