package com.wisc.app;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class PoLLandActivity extends Activity {
	Button nextButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		nextButton = (Button) findViewById(R.id.next_button);
		nextButton.setOnClickListener(new OnClickListener() {

			// Upon clicking the button, start ListViewActivity, 
			// pass it the URL entered in the text box
			public void onClick(View v) {

				Intent i = new Intent(com.wisc.app.PoLLandActivity.this,
						com.wisc.app.CreatePollActivity.class);

				startActivity(i);
			} // end method onClick
		}); 
	}
}