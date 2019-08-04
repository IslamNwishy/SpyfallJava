package com.example.spyfall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.security.auth.Destroyable;

public class GameRoom extends AppCompatActivity {
    private static final String FILE_NAME ="SaveFile.txt";
    Button Reveal;
    Button NextRound;
    LinearLayout layout;
    ArrayList<String> Names = new ArrayList<String>();
    ArrayList<String> Locations= new ArrayList<String>();
    int RandName;
    String RandLoc;
    String Spy;




    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            view.setEnabled(false);
            Button temp = (Button)view;
            if(temp.getText().toString().compareTo(Spy)!=0)
                OpenActivity(ShowLocation.class,RandLoc);
            else
                OpenActivity(ShowLocation.class,"Spy");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);



        Names= (ArrayList<String>) getIntent().getSerializableExtra("Names");
        layout=findViewById(R.id.LayoutRoom);

        if(savedInstanceState==null){
            Load();
            Random RNG= new Random();
            Locations.trimToSize();
            if(Locations.size()>0){
                RandLoc=Locations.get(RNG.nextInt(Locations.size()));
            }
            Names.trimToSize();
            RandName=RNG.nextInt((Names.size()));
            Spy=Names.get(RandName);

        }

        for (int i=0; i<Names.size(); i++){
            Reveal= new Button(this);
            Reveal.setText(Names.get(i));
            Reveal.setOnClickListener(onClickListener);
            layout.addView(Reveal);
        }


        NextRound=findViewById(R.id.NextRnd);

        NextRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("Names",Names);
                finish();
                startActivity(intent);
            }
        });


    }

    public void OpenActivity(Class<?> cls, String Word){
        Intent intent = new Intent(this, cls);
        intent.putExtra("Location", Word);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void Load(){
        FileInputStream fis=null;

        try {
            fis=openFileInput(FILE_NAME);
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;

            while ((text=br.readLine())!=null){

                Locations.add(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onBackPressed(){

    }



}
