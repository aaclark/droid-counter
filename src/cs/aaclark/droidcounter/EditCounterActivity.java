package cs.aaclark.droidcounter;

import android.app.Activity;
import android.content.Intent;

public class EditCounterActivity extends Activity {
	
	protected void onCreate(){
		Intent returnIntent = new Intent();
		returnIntent.putExtra("result",0);
		setResult(RESULT_OK,returnIntent);
		finish();
	}
}
