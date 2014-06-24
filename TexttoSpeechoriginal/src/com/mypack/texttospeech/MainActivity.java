package com.mypack.texttospeech;
 
import java.util.Locale;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity implements
        TextToSpeech.OnInitListener {
    /** Called when the activity is first created. */
 
    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;
    Context context;
    String name;
	DataBaseHandler dbhelper;

 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        tts = new TextToSpeech(this, this);
 
        btnSpeak = (Button) findViewById(R.id.btnSpeak);
 
        txtText = (EditText) findViewById(R.id.txtText);
 
        // button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
  			 //String text=txtText.getText().toString();
             speakOut();
         	       
         	}
 
        });
    }
 
    
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
 
    @Override
    public void onInit(int status) {
 
        if (status == TextToSpeech.SUCCESS) {
 
            int result = tts.setLanguage(Locale.US);
            tts.setPitch(0.8f);
    		tts.setSpeechRate(0.8f);
 
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btnSpeak.setEnabled(true);
//                speakOut();
            }
 
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
 
    }
 
    private void speakOut() {
 
    	dbhelper = new DataBaseHandler(this);
    	String text = txtText.getText().toString();
    	if(text.length()>0)
    	{
    		dbhelper.open();
        	dbhelper.update(text);
//    		Cursor chData=dbhelper.getname(text);
//    		if(chData.moveToFirst())
//    		{
//    			do
//    			{
//    				name = chData.getString(0);
//    				
//    				System.out.println("name===="+name);
//    			
//    				
//    				
//    			}while(chData.moveToNext());
//    		}
//    		if(name!=null)
//    		{
//    			dbhelper.update(text);
//    		}else
//    		{
//    			dbhelper.insertName(text);
//    		}
        	Toast.makeText(getApplicationContext(),"Saved into database",Toast.LENGTH_LONG).show(); 
        	tts.speak("Congratulation"+text+"Your going to next level", TextToSpeech.QUEUE_FLUSH, null);
    	}
    	else{
    		AlertDialog alertDialog = new AlertDialog.Builder(
                    MainActivity.this).create();
    		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alertDialog.setTitle("Invalid Data..");
			alertDialog.setMessage("Please, Enter Missing or valid data..");
    		
			tts.speak("Please enter missing data", TextToSpeech.QUEUE_FLUSH, null);
    		    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
    	
				public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
					
				}
			});
    		    
    		    alertDialog.show();
    	}
    	
    
 
        
    }
}