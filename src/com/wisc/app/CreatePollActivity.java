package com.wisc.app;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class CreatePollActivity extends Activity {
	Button clearButton;
	Button nextButton;
	EditText question;
	EditText response;
	String questionStr;
	String[] responseStrArr = new String [3];
	String category;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createpoll);

		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.categories_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

		nextButton = (Button) findViewById(R.id.next_button);
		nextButton.setOnClickListener(new OnClickListener() {

			// Upon clicking the button, start ListViewActivity, 
			// pass it the URL entered in the text box
			public void onClick(View v) {
				question = (EditText)findViewById(R.id.question_entry);
				questionStr = question.getText().toString();

				response = (EditText)findViewById(R.id.A_entry);
				responseStrArr[0] = response.getText().toString();
				response = (EditText)findViewById(R.id.B_entry);
				responseStrArr[1] = response.getText().toString();
				response = (EditText)findViewById(R.id.C_entry);
				responseStrArr[2] = response.getText().toString();

				Intent i = new Intent(com.wisc.app.CreatePollActivity.this,
						com.wisc.app.createPollSettingsActivity.class);

				i.putExtra("Q", questionStr);
				i.putExtra("R", responseStrArr);
				i.putExtra("cat", category);

				startActivity(i);
			} // end method onClick
		}); 

		clearButton = (Button) findViewById(R.id.clear_button);
		clearButton.setOnClickListener(new OnClickListener() {

			// Upon clicking the button, start ListViewActivity, 
			// pass it the URL entered in the text box
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(CreatePollActivity.this);
				builder.setMessage("Are you sure you want to clear your poll?")
				.setCancelable(false)
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				})
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						finish();
						startActivity(new Intent(CreatePollActivity.this, CreatePollActivity.class));
					}
				});
				AlertDialog alert = builder.create();
				alert.show();

			} // end method onClick
		}); 



	}

	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent,
				View view, int pos, long id) {
			category = parent.getItemAtPosition(pos).toString();
			//Toast.makeText(parent.getContext(), "The planet is " +
			//	parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
		}

		public void onNothingSelected(AdapterView parent) {
			//
		}
	}
}