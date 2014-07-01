package com.mypack.texttospeech.test;



import com.mypack.texttospeech.DataBaseHandler;
import com.mypack.texttospeech.MainActivity;
import com.mypack.texttospeech.R;
import com.mypack.texttospeech.SecondActivity;
import com.robotium.solo.Solo;

import android.database.Cursor;
import android.speech.tts.TextToSpeech;
import android.test.ActivityInstrumentationTestCase2;
import android.test.RenamingDelegatingContext;
import android.widget.EditText;
import android.widget.TextView;

public class TestMainActivity extends
	ActivityInstrumentationTestCase2<MainActivity>
{
    private TextToSpeech tts;
    DataBaseHandler db;
	private Solo solo;
	private static final String TEST_FILE_PREFIX = "test_";

	public TestMainActivity() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo=new Solo(getInstrumentation(),getActivity());
		RenamingDelegatingContext context 
        = new RenamingDelegatingContext(getActivity(), TEST_FILE_PREFIX);

    db = new DataBaseHandler(context);
    db.open();
		}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
		 super.tearDown();
	}
	public void testMain()
	{
		solo.assertCurrentActivity("test main", MainActivity.class);
		//solo.clickOnButton("Add");
		EditText text= (EditText) solo.getView(R.id.txtText);
		solo.clearEditText(text);
		solo.enterText(text,"ashok");
		solo.clickOnButton("Add");
		db.update("ashok");
		Cursor obj=db.getname();
		obj.moveToFirst();
		String name=obj.getString(0);
		assertEquals("ashok",name);
		solo.assertCurrentActivity("test main", SecondActivity.class);
		TextView view = (TextView)solo.getText(R.id.text);
		view.setText(name);
}
}
