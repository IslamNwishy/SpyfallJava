package com.example.spyfall;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartGame extends AppCompatActivity {

    EditText Text;
    Button NextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Text=findViewById(R.id.PlayerNum);
        NextBtn=findViewById(R.id.Next);


        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenActivity(StartGame2.class);

            }
        });
        Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Text.getText().length()>0)
                    NextBtn.setEnabled(true);
                else
                    NextBtn.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }



    public void OpenActivity(Class<?> cls){
        Intent intent = new Intent(this, cls);
        intent.putExtra("PlayerNum",Text.getText().toString());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }




}
