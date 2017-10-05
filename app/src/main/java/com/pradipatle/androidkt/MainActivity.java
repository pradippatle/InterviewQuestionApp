package com.pradipatle.androidkt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private String jsonResponse = null;
    private ArrayList<ModelData> dataArrayList;
    private AdapterData dataAdapter;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);
        dataArrayList = new ArrayList<>();
        loadJSONFromAsset();
    }

    private String loadJSONFromAsset() {
        try {
            InputStream is = getAssets().open("datafile.json");
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
                    ModelData md = new ModelData();
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
                    dataAdapter = new AdapterData(dataArrayList, MainActivity.this);
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
}
