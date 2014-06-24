package com.mypack.texttospeech.test;
import com.jayway.android.robotium.solo.Solo

;
import com.mypack.texttospeech.MainActivity;
import com.mypack.texttospeech.DataBaseHandler;
import com.mypack.texttospeech.R;
import android.database.Cursor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ProviderTestCase2;
import android.widget.EditText;

public class Test extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;
	DataBaseHandler db;
	public Test() {
		super(MainActivity.class);
        db=new DataBaseHandler(null);
	}

	
	public void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
//	@Override
//	public void tearDown() throws Exception {
//
//
//		solo.finishOpenedActivities();
//
//		super.tearDown();
//	}
	public void test() {
		
		solo.assertCurrentActivity("my first activity",MainActivity.class);

//		EditText text= (EditText) solo.getView(R.id.txtText);
//		solo.clearEditText(text);
//		solo.enterText(text,"sadam");
		solo.clickOnButton("Add");
		//db.insertName("sadam");
		//assertEquals("sadam",db.getname());
		//db.updatename("sadam");
		
	}

}
