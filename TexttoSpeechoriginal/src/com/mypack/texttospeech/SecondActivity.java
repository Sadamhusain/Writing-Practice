package com.mypack.texttospeech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

 
public class SecondActivity extends Activity {
    /** Called when the activity is first created. */
 
TextView view;

 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        
        view = (TextView)findViewById(R.id.text);
        Intent intent = getIntent();
        String name = intent.getStringExtra("message");
        view.setText("Username : "+name);
        
        
    }



	
    }