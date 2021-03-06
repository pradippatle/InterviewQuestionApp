package com.pradipatle.androidkt.Java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pradipatle.androidkt.R;

import java.util.ArrayList;

/**
 * Created by Aeon-02 on 03-04-2017.
 */

public class JavaAdapter extends BaseAdapter {

    ArrayList<JavaModel> dataArrayList;
    Context context;

    public JavaAdapter(ArrayList<JavaModel> dataList, Context context) {
        this.dataArrayList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, parent, false);
        }
        TextView question = (TextView) convertView.findViewById(R.id.id_question);
        TextView answer = (TextView) convertView.findViewById(R.id.id_answer);

        question.setText(dataArrayList.get(position).getNumber() +" ) "+dataArrayList.get(position).getQuestion());
        answer.setText(dataArrayList.get(position).getAnswer());

        return convertView;
    }


}
