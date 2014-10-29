package com.example.cimpli;

import java.util.concurrent.TimeUnit;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Listen_audio extends Activity implements OnClickListener {
	
	LinearLayout lay_play_button,lay_pause_button;
	ImageView play_button,pause_button;
	SeekBar seekBar_time;
	TextView sound_time,text_voicenote,text_cancel;
	String font_path,font_path_light;
	Typeface tf;
	Typeface tf_light;
	 MediaPlayer myPlayer ;
	 String outputfile="";
	 SharedPreferences shf_name;
	 double sound_timer,soundtimer1;
	  private Handler myHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listen_audio);
		
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
		
		font_path="fonts/Futura-Book.ttf";
		font_path_light="fonts/FuturaEF-Light.ttf";
		tf=Typeface.createFromAsset(getAssets(),font_path);
		tf_light=Typeface.createFromAsset(getAssets(),font_path_light);
		
		lay_play_button=(LinearLayout)findViewById(R.id.lay_play_button);
		lay_pause_button=(LinearLayout)findViewById(R.id.lay_pause_button);
		
		play_button=(ImageView)findViewById(R.id.play_button);
		pause_button=(ImageView)findViewById(R.id.pause_button);
		
		seekBar_time=(SeekBar)findViewById(R.id.seekBar_time);
		
		sound_time=(TextView)findViewById(R.id.sound_time);
		text_voicenote=(TextView)findViewById(R.id.text_voicenote);
		text_cancel=(TextView)findViewById(R.id.text_cancel);
		
		sound_time.setTypeface(tf);
		text_voicenote.setTypeface(tf);
		text_cancel.setTypeface(tf);
		
		text_cancel.setOnClickListener(this);
		
		play_button.setOnClickListener(this);
		pause_button.setOnClickListener(this);
		outputfile=shf_name.getString("sound_file", "");
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listen_audio, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.play_button:
			try{
				 
					lay_play_button.setVisibility(View.GONE);
					lay_pause_button.setVisibility(View.VISIBLE);
					
			 myPlayer = new MediaPlayer();
//			MediaPlayer.create(con, R.raw.west_coast);
		
//			 myPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			 
			myPlayer.setDataSource(outputfile);
			
			myPlayer.prepare();
			myPlayer.start();
			sound_timer=myPlayer.getDuration();
			soundtimer1=myPlayer.getCurrentPosition();
			sound_time.setText(String.format("%d min, %d sec", 
			         TimeUnit.MILLISECONDS.toMinutes((long) sound_timer),
			         TimeUnit.MILLISECONDS.toSeconds((long) sound_timer) - 
			         TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
			         toMinutes((long) sound_timer)))
			      );
			seekBar_time.setProgress((int)soundtimer1);
			seekBar_time.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
					
					
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub
					seekBar=seekBar_time;
					progress=(int)soundtimer1/1000;
					seekBar.setProgress(progress);
				}
			});
			 myHandler.postDelayed(UpdateSongTime,100);
		   Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
			  
				
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			break;
			
		case R.id.pause_button:
			try {
				
		           if (myPlayer != null) {
		
		               myPlayer.pause();
		
		             

		     	      lay_play_button.setVisibility(View.VISIBLE);
		     	      lay_pause_button.setVisibility(View.GONE);
		     	      Toast.makeText(getApplicationContext(), "Stop playing the recording...",
		     	      Toast.LENGTH_LONG).show();
		
		           }
		
		       } catch (Exception e) {
		
		           // TODO Auto-generated catch block
		
		            e.printStackTrace();
		
		        }

			
			
			
			break;
		case R.id.text_cancel:
			if(myPlayer!=null)
			{
			myPlayer.stop();
			finish();
			}
			else
				finish();
			break;
			
			
			

		default:
			break;
		}
		
	}
	private Runnable UpdateSongTime = new Runnable() {
	      public void run() {
	         soundtimer1 = myPlayer.getCurrentPosition();
	         sound_time.setText(String.format("%d min, %d sec", 
	            TimeUnit.MILLISECONDS.toMinutes((long) soundtimer1),
	            TimeUnit.MILLISECONDS.toSeconds((long) soundtimer1) - 
	            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
	            toMinutes((long) soundtimer1)))
	         );
	         seekBar_time.setProgress((int)soundtimer1);
	         myHandler.postDelayed(this, 100);
	      }
	   };

}
