package com.example.spyfall;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;


public class StartGame2 extends AppCompatActivity {
    int PlayerNum;
    EditText Text;
    LinearLayout layout;
    Button NextBtn;
    ArrayList<String> Names = new ArrayList<String>();
    ArrayList<EditText> Fields = new ArrayList<EditText>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game2);
        PlayerNum=Integer.parseInt(getIntent().getStringExtra("PlayerNum"));


        layout=findViewById(R.id.Layout);
        for(int i=0; i<PlayerNum; i++){
            Text=new EditText(this);
            int num=i+1;
            Text.setHint("Player "+ num);
            Text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            Text.setInputType(InputType.TYPE_CLASS_TEXT);
            Fields.add(Text);
            layout.addView(Text);

        }

        NextBtn= new Button(this);
        NextBtn.setText("Next");
        layout.addView(NextBtn);




        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0; i<Fields.size(); i++){
                    if(IsEmpty(Fields.get(i))) return;
                }
                for (int i=0; i<Fields.size(); i++)
                    Names.add(Fields.get(i).getText().toString());

                OpenActivity(GameRoom.class);
            }
        });



    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void OpenActivity(Class<?> cls){
        Intent intent = new Intent(this, cls);
        intent.putExtra("Names", Names);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }


    public boolean IsEmpty(EditText editText){
        if(editText.getText().length()>0)
            return false;
        else{
            Toast.makeText(this, "Please Name All Players", Toast.LENGTH_LONG).show();
            return true;
        }
    }


}

