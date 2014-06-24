package com.pisaris.memory;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity implements OnClickListener {
	
	RelativeLayout relativeLayout;
	LinearLayout linear, linearLayout;
	LinearLayout linear2;
	ScrollView scrollView;
	ImageButton[][] buttonTable;
	int rows = 7;
	int cols = 6;
	String[] picNames = {"android","android1","apple","aragorn","cat","cat1","dog","eiffel","eiffel1",
			"eye","facebook","gollum","guitar","harry_lisa","pao","penguin","ring","superman",
			"sweden","mouse","goofy"};
	int[] picValues = new int[picNames.length];
	int numOfPics;
	int clicked = 1;
	int locked = 0;
	ImageButton bt_prev,bt_cur;
	Intent intent;
	int win_counter = 0;
	MediaPlayer mediaPlayer;
	
	//Timer MyTimer;
    class TimerJob extends TimerTask
    {
        @Override
        public void run ()
        {
            
        	if(bt_cur.getId() == bt_prev.getId())
        	{
        		/*bt_prev.setVisibility(1);
                bt_cur.setVisibility(1);*/
        		Message msg = new Message ();
     	        Bundle b = new Bundle ();
     	        b.putInt("same", 1);
     	        msg.setData (b);
     	        MyHandler.sendMessage (msg);
     	        win_counter++;
        	}
        	else
        	{
        		Message msg = new Message ();
     	        Bundle b = new Bundle ();
     	        b.putInt("same", 0);
     	        msg.setData (b);
     	        MyHandler.sendMessage (msg);
        		//Log.d("not", "same");
        	}
        	locked = 0;
            this.cancel();
            
        }
    };
    
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		relativeLayout = (RelativeLayout)findViewById(R.id.relative_layout);
		setRandomBackground(relativeLayout);
		//relativeLayout.setBackgroundResource(R.drawable.back1);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout2);
        linear = new LinearLayout(getBaseContext());
		linear.setOrientation(LinearLayout.HORIZONTAL);
		linearLayout.addView(linear);
		bt_prev = new ImageButton(getBaseContext());
		bt_cur = new ImageButton(getBaseContext());
		for(int i = 0; i < picNames.length; i ++)
		{
			picValues[i] = 0;
		}
		intent = getIntent();
        Bundle bu = intent.getExtras();
        rows = bu.getInt("rows");
        cols = bu.getInt("cols");
		numOfPics = (rows*cols)/2;
		mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.la_grange);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    @Override
    protected void onStart () //when the activity comes to foreground
    {
    	super.onStart ();
    	buttonTable = new ImageButton[rows][cols];
    	for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				int resID = 0;
				buttonTable[i][j] = new ImageButton(linear.getContext());
				//buttonTable[i][j].setText("..");
				buttonTable[i][j].getBackground().setColorFilter(new LightingColorFilter(Color.BLUE, Color.BLUE));
		        buttonTable[i][j].getBackground().setAlpha(255);
		        
		        linear.addView(buttonTable[i][j],100,100);
		        buttonTable[i][j].setBackgroundResource(R.drawable.plain);
     			
     			buttonTable[i][j].setOnClickListener(this);
     			
     			
				Random r = new Random();
				int pos = r.nextInt(numOfPics);
				
				int flag = 0;
				while(flag == 0)
				{
					
					if(picValues[pos] < 2)
					{
						flag = 1;
						String chosenPic = picNames[pos];
						resID = getResources().getIdentifier(chosenPic, "drawable","com.pisaris.memory");
						picValues[pos]++;
					}
					else
					{
						
						pos = r.nextInt(numOfPics);
					}
				}
				buttonTable[i][j].setId(resID);
				
				/*Log.d("pic: ", picNames[pos]);
				Log.d("shown: ", picValues[pos]+" times");*/
			}
			
			
			linear2 = new LinearLayout(getBaseContext());
			linear2.setOrientation(LinearLayout.HORIZONTAL);
			linearLayout.addView(linear2);
			linear = linear2;

			
		}
    }

	@Override
	public void onClick(View button) 
	{

			if(locked == 0)
			{
				ImageButton bt = (ImageButton)button;
				bt.setBackgroundResource(bt.getId());
				
				if(clicked == 1)
				{
					bt_prev = bt;
					clicked = 2;
					//Log.d("is", "first");
					
					
				}
				else if (clicked == 2)
				{
					//Log.d("is", "second");
					bt_cur = bt;
					Timer MyTimer = new Timer ();
					TimerJob MyJob = new TimerJob ();
			        MyTimer.schedule(MyJob, 2000, 1000);
					clicked = 1;
					locked = 1;
					//MyTimer.cancel();
				}
			}
	}
	
	private void setRandomBackground(RelativeLayout relativeLayout)
	{
		Random r = new Random();
		int i = r.nextInt(9);
		
		switch(i)
		{
		case 0:
			relativeLayout.setBackgroundResource(R.drawable.back1);
			break;
		case 1:
			relativeLayout.setBackgroundResource(R.drawable.back2);
			break;
		case 2:
			relativeLayout.setBackgroundResource(R.drawable.back3);
			break;
		case 3:
			relativeLayout.setBackgroundResource(R.drawable.back4);
			break;
		case 4:
			relativeLayout.setBackgroundResource(R.drawable.back5);
			break;
		case 5:
			relativeLayout.setBackgroundResource(R.drawable.back6);
			break;
		case 6:
			relativeLayout.setBackgroundResource(R.drawable.back7);
			break;
		case 7:
			relativeLayout.setBackgroundResource(R.drawable.back8);
			break;
		case 8:
			relativeLayout.setBackgroundResource(R.drawable.back9);
			break;
		case 9:
			relativeLayout.setBackgroundResource(R.drawable.back10);
			break;
		}
	}
	
	Handler MyHandler = new Handler ()
    {
        @Override
        public void handleMessage (Message Msg)
        {
            Bundle bu = Msg.getData ();
            int same = bu.getInt("same");
            if(same == 1)
            {
            	//bt_cur.setVisibility(2);
                //bt_prev.setVisibility(2);
            	bt_cur.setVisibility(View.INVISIBLE);
            	bt_prev.setVisibility(View.INVISIBLE);
            	if(win_counter == numOfPics)
            	{
            		winGame();
            	}
                /*Toast MyToast = Toast.makeText (getApplicationContext (), "same!", Toast.LENGTH_LONG);
    	        MyToast.setGravity (Gravity.CENTER, 0, 0);
    	        MyToast.show ();*/
            }
            else
            {
            	bt_cur.setBackgroundResource(R.drawable.plain);
            	bt_prev.setBackgroundResource(R.drawable.plain);
            }
        }

		private void winGame() {
			Intent NextAct;
			NextAct = new Intent (getApplicationContext(), WinActivity.class);
			startActivity(NextAct);
			finish();
		}
    };
    
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.newgame:
	        	finish();
	            return true;
	        case R.id.songs:
	        	showDialog(1);
	        	//requestWindowFeature(Window.FEATURE_NO_TITLE);
	            /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
	            return true;
	        case R.id.soundoff:
	        	mediaPlayer.setVolume(0,0);
	        	return true;
	        	
	        case R.id.soundon:
	        	mediaPlayer.setVolume(1,1);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	@Override
	public Dialog onCreateDialog(int id) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle(R.string.song_option)
	           .setItems(R.array.testArray, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               switch (which) {
				case 0:
					mediaPlayer.stop();
					mediaPlayer.release();
					mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.born_to_be_wild);
					mediaPlayer.setLooping(true);
					mediaPlayer.start();
					break;
				case 1:
					mediaPlayer.stop();
					mediaPlayer.release();
					mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.la_grange);
					mediaPlayer.setLooping(true);
					mediaPlayer.start();
					break;
				case 2:
					mediaPlayer.stop();
					mediaPlayer.release();
					mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.youth_gone_wild);
					mediaPlayer.setLooping(true);
					mediaPlayer.start();
					break;
				case 3:
					mediaPlayer.stop();
					mediaPlayer.release();
					mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.black_velvet_band);
					mediaPlayer.setLooping(true);
					mediaPlayer.start();
					break;

				default:
					break;
				}
	           }
	    });
	    return builder.create();
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
