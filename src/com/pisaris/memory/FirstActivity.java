package com.pisaris.memory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class FirstActivity extends Activity {

	RelativeLayout relativeLayout;
	RadioButton radioButton1;
	RadioButton radioButton2;
	Spinner spinnerHeight;
	Spinner spinnerWidth;
	RadioGroup radioFixed;
	RadioButton radioButtonFixed1;
	RadioButton radioButtonFixed2;
	RadioButton radioButtonFixed3;
	
	ImageButton playButton;
	int rows=0,cols=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_activity_main);
		
		relativeLayout = (RelativeLayout)findViewById(R.id.relative_layout);
		//relativeLayout.setBackgroundResource(R.drawable.back_start1);
		radioButton1 = (RadioButton)findViewById(R.id.radioButtonFixed);
		radioButton2 = (RadioButton)findViewById(R.id.radioButtonRandom);
		radioFixed  = (RadioGroup) findViewById(R.id.radioGroupFixed);
		radioButtonFixed1 = (RadioButton)findViewById(R.id.radioButtonLevel1);
		radioButtonFixed2 = (RadioButton)findViewById(R.id.radioButtonLevel2);
		radioButtonFixed3 = (RadioButton)findViewById(R.id.radioButtonLevel3);
		
		radioButtonFixed1.setClickable(true);
        radioButtonFixed2.setClickable(true);
        radioButtonFixed3.setClickable(true);
		
		spinnerHeight = (Spinner) findViewById(R.id.spinner1);
		spinnerWidth = (Spinner) findViewById(R.id.spinner2);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterHeight = ArrayAdapter.createFromResource(this,
		        R.array.heights_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapterHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerHeight.setAdapter(adapterHeight);
		
		ArrayAdapter<CharSequence> adapterWidth = ArrayAdapter.createFromResource(this,
		        R.array.widths_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapterWidth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerWidth.setAdapter(adapterWidth);
		
		playButton = (ImageButton) findViewById(R.id.playButton);
		playButton.setOnClickListener(new OnClickListener() {
			
			private Intent NextAct;

			@Override
			public void onClick(View v) {
				Bundle MyBundle = new Bundle();
				// TODO Auto-generated method stub
				if(radioButton1.isChecked())
				{
					//radioFixed.getCheckedRadioButtonId();
					/*Toast MyToast = Toast.makeText (getApplicationContext (), "checked: "+radioFixed.getCheckedRadioButtonId(), Toast.LENGTH_LONG);
	    	        MyToast.setGravity (Gravity.CENTER, 0, 0);
	    	        MyToast.show ();*/
					if(radioFixed.getCheckedRadioButtonId()==radioButtonFixed1.getId())
					{
						/*Toast MyToast = Toast.makeText (getApplicationContext (), "checked: "+radioFixed.getCheckedRadioButtonId(), Toast.LENGTH_LONG);
		    	        MyToast.setGravity (Gravity.CENTER, 0, 0);
		    	        MyToast.show ();*/
						radioButton1.performClick();
		    	        rows = 4;
		    	        cols = 4;
					}
					else if(radioFixed.getCheckedRadioButtonId()==radioButtonFixed2.getId())
					{
						radioButton1.setChecked(true);
						rows = 5;
		    	        cols = 6;
					}
					else if(radioFixed.getCheckedRadioButtonId()==radioButtonFixed3.getId())
					{
						radioButton1.setChecked(true);
						rows = 7;
						cols = 6;
					}
				}
				else if(radioButton2.isChecked())
				{
					rows = spinnerHeight.getSelectedItemPosition()+2;
					cols = spinnerWidth.getSelectedItemPosition()+2;
					/*Toast MyToast = Toast.makeText (getApplicationContext (), "checked: "+rows, Toast.LENGTH_LONG);
	    	        MyToast.setGravity (Gravity.CENTER, 0, 0);
	    	        MyToast.show ();*/
				}
				
				else
				{
					Toast MyToast = Toast.makeText (getApplicationContext (), "Please choose game Difficulty", Toast.LENGTH_LONG);
	    	        MyToast.setGravity (Gravity.CENTER, 0, 0);
	    	        MyToast.show ();
				}
				if(rows!=0&&cols!=0)
				{
					MyBundle.putInt("rows", rows);
					MyBundle.putInt("cols", cols);
					NextAct = new Intent (getApplicationContext(), MainActivity.class);
					NextAct.putExtras(MyBundle);
					startActivity(NextAct);
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();

	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radioButtonFixed:
	            if (checked)
	            {
	            	/*Toast MyToast = Toast.makeText (getApplicationContext (), "same!", Toast.LENGTH_LONG);
	    	        MyToast.setGravity (Gravity.CENTER, 0, 0);
	    	        MyToast.show ();*/
	                //spinnerHeight.setClickable(false);
	                //spinnerWidth.setClickable(false);
	            	
	            	spinnerHeight.setVisibility(View.INVISIBLE);
	            	spinnerWidth.setVisibility(View.INVISIBLE);
	            	
	                radioButtonFixed1.setClickable(true);
	                radioButtonFixed2.setClickable(true);
	                radioButtonFixed3.setClickable(true);
	                
	                radioButtonFixed1.setVisibility(View.VISIBLE);
	                radioButtonFixed2.setVisibility(View.VISIBLE);
	                radioButtonFixed3.setVisibility(View.VISIBLE);
	                
	            }
	            	
	            break;
	        case R.id.radioButtonRandom:
	            if (checked)	            
	            {
	              
	                
	                radioButtonFixed1.setVisibility(View.INVISIBLE);
	                radioButtonFixed2.setVisibility(View.INVISIBLE);
	                radioButtonFixed3.setVisibility(View.INVISIBLE);
	                
	                spinnerHeight.setVisibility(View.VISIBLE);
	            	spinnerWidth.setVisibility(View.VISIBLE);
	                
	                spinnerHeight.setClickable(true);
	                spinnerWidth.setClickable(true);
	            }
	            break;
	    }
	}
	
	

}
