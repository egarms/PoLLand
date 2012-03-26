package com.wisc.app;
import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class createPollSettingsActivity extends Activity{

	String[] responses;
	String question;
	String category;
	String visibility;
	String closePollChoice;
	private int endYear;
	private int endMonth;
	private int endDay;
	static final int DATE_DIALOG_ID = 0;
	Button createButton;
	Button backButton;


	// Called when the activity is first created. 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pollsettings);
		try {
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				question = extras.getString("Q");
				responses = extras.getStringArray("R");
				category = extras.getString("cat");

				System.out.println(question);
				for(int i=0;i<responses.length;i++)
					System.out.println(responses[i]);
				System.out.println(category);
			}
		} catch (Exception e) {
			System.out.println("ERROR HERE!");
		}

		TextView questionText = (TextView) findViewById(R.id.question_text);
		questionText.setText(question);

		RadioGroup answerChoices = (RadioGroup) findViewById(R.id.answerChoices);

		OnClickListener radio_listener = new OnClickListener() {
			public void onClick(View v) {
				onRadioButtonClick(v);
			}

			private void onRadioButtonClick(View v) {
				//
			}
		};

		RadioButton[] buttons = new RadioButton[responses.length];
		for(int i=0;i<responses.length;i++){

			buttons[i] = new RadioButton(this);
			buttons[i].setText(responses[i]);
			buttons[i].setOnClickListener(radio_listener);
			answerChoices.addView(buttons[i]);
		}


		// Visibility choices spinner
		Spinner spinner = (Spinner) findViewById(R.id.visibility);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.visibility_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

		// End date choices spinner
		Spinner endSpinner = (Spinner) findViewById(R.id.end_choice);
		ArrayAdapter<CharSequence> endAdapter = ArrayAdapter.createFromResource(
				this, R.array.end_choice_array, android.R.layout.simple_spinner_item);
		endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		endSpinner.setAdapter(endAdapter);

		endSpinner.setOnItemSelectedListener(new EndingOnItemSelectedListener());

		// Create poll button
		createButton = (Button) findViewById(R.id.create_button);
		createButton.setOnClickListener(new OnClickListener() {

			// Upon clicking the button, start ListViewActivity, 
			// pass it the URL entered in the text box
			public void onClick(View v) {
				System.out.println(closePollChoice);
				Intent i = new Intent(com.wisc.app.createPollSettingsActivity.this,
						com.wisc.app.PollDisplayActivity.class);

				i.putExtra("Q", question);
				i.putExtra("R", responses);
				i.putExtra("cat", category);
				i.putExtra("vis", visibility);
				i.putExtra("closeDate", closePollChoice);

				startActivity(i);
			} // end method onClick
		}); 

		backButton = (Button) findViewById(R.id.back_button);
		backButton.setOnClickListener(new OnClickListener() {

			// Upon clicking the button, start ListViewActivity, 
			// pass it the URL entered in the text box
			public void onClick(View v) {
				finish();
			} // end method onClick
		});



	}

	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent,
				View view, int pos, long id) {
			visibility = parent.getItemAtPosition(pos).toString();
		}

		public void onNothingSelected(AdapterView parent) {
			//
		}
	}

	public class EndingOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent,
				View view, int pos, long id) {
			if(pos==3){
				showDialog(DATE_DIALOG_ID);
			}
			else
				closePollChoice = parent.getItemAtPosition(pos).toString();
		}

		public void onNothingSelected(AdapterView parent) {
			//
		}

	}

	private DatePickerDialog.OnDateSetListener mDateSetListener =
			new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			endYear = year;
			endMonth = monthOfYear;
			endDay = dayOfMonth;
			endMonth++;
			closePollChoice=""+endMonth+'/'+endDay+'/'+endYear+"";
		}
	};

	
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:{
			// get the current date
	        final Calendar c = Calendar.getInstance();
	        int mYear = c.get(Calendar.YEAR);
	        int mMonth = c.get(Calendar.MONTH);
	        int mDay = c.get(Calendar.DAY_OF_MONTH);

			return new DatePickerDialog(createPollSettingsActivity.this,
					mDateSetListener,
					mYear, mMonth, mDay);
		}}
		return null;
	}
}
