package com.example.spyfall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AddWords extends AppCompatActivity {
    private static final String FILE_NAME ="SaveFile.txt";
    private BinarySearchTree Tree=new BinarySearchTree();

    EditText editText;
    Button SaveBtn, ClearBtn, ShowBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        editText = findViewById(R.id.editText);
        SaveBtn=findViewById(R.id.SaveButton);
        ClearBtn=findViewById(R.id.ClearButton);
        ShowBtn=findViewById(R.id.ListButton);

        Load();

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });
        ClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clear();
            }
        });
        ShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> List;
                List=Tree.Traverse();

                if(List.size()>0)
                    OpenActivity(ListLocations.class, List);
                else
                    Draw();

            }
        });




    }

    public void Draw(){
        Toast.makeText(this,"Database is Empty",Toast.LENGTH_SHORT).show();

    }

    public void Save(){
     String mText= editText.getText().toString();
     FileOutputStream fos =null;
     Scanner scan = new Scanner(mText);

     while(scan.hasNextLine()) {
         String Text = scan.nextLine();
            if (!Tree.Insert(Text)) {
                Toast.makeText(this, Text + " Already Exists", Toast.LENGTH_LONG).show();
            } else {

                try {
                    fos = openFileOutput(FILE_NAME, MODE_APPEND);
                    Text += "\n";
                    if (Text != "\n") {
                        fos.write(Text.getBytes());
                        Toast.makeText(this, Text + " Was Added to the Database", Toast.LENGTH_LONG).show();
                    }
                    editText.getText().clear();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void Load(){
        FileInputStream fis=null;

        try {
            fis=openFileInput(FILE_NAME);
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;

            while ((text=br.readLine())!=null){

                Tree.Insert(text);
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

    public void Clear(){
        FileOutputStream fos =null;
        try {
            fos=openFileOutput(FILE_NAME,MODE_PRIVATE);
            fos.write("".getBytes());
            Load();
            Toast.makeText(this,"Database Cleared",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos!=null) {
                try {
                    fos.close();
                    Tree= new BinarySearchTree();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void OpenActivity(Class<?> cls, ArrayList<String> List){
        Intent intent = new Intent(this, cls);
        intent.putExtra("List",List);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }


}
