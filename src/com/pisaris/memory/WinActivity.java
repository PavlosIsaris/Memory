package com.pisaris.memory;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class WinActivity extends Activity {
	RelativeLayout relativeLayout;
	ImageButton newGameBt;
	MediaPlayer mediaPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_win);
		relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
		relativeLayout.setBackgroundResource(R.drawable.win);
		newGameBt = (ImageButton) findViewById(R.id.newGamebt);
		
		newGameBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.crowd);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.win, menu);
		return true;
	}
	
	@Override
	public void onDestroy()
	{
		//mediaPlayer.stop();
		//mediaPlayer.release();
		super.onDestroy();
		
		
	}
	
	@Override
	public void onPause()
	{
		
		super.onPause();
		mediaPlayer.stop();
		mediaPlayer.release();
		
	}
	
	@Override
	public void onRestart()
	{
		super.onRestart();
		mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.born_to_be_wild);
		mediaPlayer.start();
	}
	

}
