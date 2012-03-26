package com.wisc.app;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PollDisplayActivity extends Activity{

	String[] responses;
	String question;
	String category;
	String visibility;
	String closePollChoice;
	Button voteButton;
	String vote;


	// Called when the activity is first created. 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displaypoll);
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
				RadioButton button = (RadioButton) v;
				vote = (String) button.getText();
			}
		};

		RadioButton[] buttons = new RadioButton[responses.length];
		for(int i=0;i<responses.length;i++){

			buttons[i] = new RadioButton(this);
			buttons[i].setText(responses[i]);
			buttons[i].setOnClickListener(radio_listener);
			answerChoices.addView(buttons[i]);
		}


		// Vote button
		voteButton = (Button) findViewById(R.id.vote_button);
		voteButton.setOnClickListener(new OnClickListener() {

			// Upon clicking the button, start ListViewActivity, 
			// pass it the URL entered in the text box
			public void onClick(View v) {
				Intent i = new Intent(com.wisc.app.PollDisplayActivity.this,
						com.wisc.app.PoLLandActivity.class);

				i.putExtra("Q", question);
				i.putExtra("R", responses);
				i.putExtra("cat", category);
				i.putExtra("vis", visibility);
				i.putExtra("closeDate", closePollChoice);

				startActivity(i);
			} // end method onClick
		}); 
	}

}
