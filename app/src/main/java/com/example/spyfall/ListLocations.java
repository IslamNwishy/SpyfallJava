package com.example.spyfall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListLocations extends AppCompatActivity {


    ArrayList<String> List = new ArrayList<String>();
    LinearLayout Layout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_locations);

        Layout=findViewById(R.id.ListLayout);
        List= (ArrayList<String>) getIntent().getSerializableExtra("List");

        for(int i=0; i<List.size(); i++){
            textView=new TextView(this);
            int num=i+1;
            textView.setText(num+"- "+List.get(i));
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
            Layout.addView(textView);
        }
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

}
