package com.example.cimpli;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Voicemode extends Activity implements OnClickListener,OnTouchListener {
	
	
	ImageView open_record,play_sound,pause_sound,open_record_fade,delete_audio;
	LinearLayout lay_text_cancel,lay_text_done,lay_seekbar,lay_pause_sound,lay_play_sound,lay_open_record,main_open_record;
	Chronometer timer_voice;
	FrameLayout main_open_record_fade;
	SeekBar seekBar_time;
	double sound_timer,soundtimer1;
	boolean show=true;
	LinearLayout delete_audio_lay;
	
	  private Handler myHandler = new Handler();
	private MediaRecorder myAudioRecorder;
	
	   private String outputFile = null;
	   MediaPlayer myPlayer ;
	   Context con;
	   SharedPreferences shf_name;
	   
	TextView text_cancel,text_record,text_done,sound_time,text_voicenote,text_save;
	String font_path,font_path_light;
	Typeface tf;
	Typeface tf_light;
	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voicemode);
		
//		outputFile = Environment.getExternalStorageDirectory().
//			      getAbsolutePath() + "/myrecording.mp3";
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
		
		delete_audio_lay=(LinearLayout)findViewById(R.id.delete_audio_lay);
		lay_seekbar=(LinearLayout)findViewById(R.id.lay_seekbar);
		lay_pause_sound=(LinearLayout)findViewById(R.id.lay_pause_sound);
		lay_play_sound=(LinearLayout)findViewById(R.id.lay_play_sound);
		lay_open_record=(LinearLayout)findViewById(R.id.lay_open_record);
		main_open_record=(LinearLayout)findViewById(R.id.main_open_record);
		
		main_open_record_fade=(FrameLayout)findViewById(R.id.main_open_record_fade);
		
		lay_open_record.setVisibility(View.VISIBLE);
		seekBar_time=(SeekBar)findViewById(R.id.seekBar_time);
		seekBar_time.setBackgroundColor(Color.parseColor("#ffffff"));
		
		
		font_path="fonts/Futura-Book.ttf";
		font_path_light="fonts/FuturaEF-Light.ttf";
		
		tf=Typeface.createFromAsset(getAssets(),font_path);
		tf_light=Typeface.createFromAsset(getAssets(),font_path_light);
		
		timer_voice=(Chronometer)findViewById(R.id.timer_voice);
		
		open_record=(ImageView)findViewById(R.id.open_record);
		play_sound=(ImageView)findViewById(R.id.play_sound);
		pause_sound=(ImageView)findViewById(R.id.pause_sound);
		open_record_fade=(ImageView)findViewById(R.id.open_record_fade);
		delete_audio=(ImageView)findViewById(R.id.delete_audio);
		
//		play_sound.setVisibility(View.INVISIBLE);
//		pause_sound.setVisibility(View.INVISIBLE);
		
		open_record.setOnTouchListener(this);
		delete_audio_lay.setOnClickListener(this);
		delete_audio.setOnClickListener(this);
		play_sound.setOnClickListener(this);
		play_sound.setOnClickListener(this);
		pause_sound.setOnClickListener(this);
		open_record_fade.setOnClickListener(this);
		
		text_voicenote=(TextView)findViewById(R.id.text_voicenote);
		sound_time=(TextView)findViewById(R.id.sound_time);
		text_done=(TextView)findViewById(R.id.text_done);
		text_record=(TextView)findViewById(R.id.text_record);
		text_cancel=(TextView)findViewById(R.id.text_cancel);
		text_save=(TextView)findViewById(R.id.text_save);
		
		text_save.setTypeface(tf);
		text_done.setTypeface(tf);
		text_record.setTypeface(tf);
		text_cancel.setTypeface(tf);
		
		
		text_cancel.setOnClickListener(this);
		text_done.setOnClickListener(this);
		text_save.setOnClickListener(this);
		
		outputFile = Environment.getExternalStorageDirectory().
			      getAbsolutePath() + "/myrecording.3gpp";
		 
		
	      con=Voicemode.this;
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.play_sound:
			
			try{
				
				 	main_open_record.setVisibility(View.GONE);
				
				
					lay_seekbar.setVisibility(View.VISIBLE);
					lay_play_sound.setVisibility(View.GONE);
					lay_pause_sound.setVisibility(View.VISIBLE);
					text_record.setVisibility(View.GONE);
					timer_voice.setVisibility(View.GONE);
					text_done.setVisibility(View.VISIBLE);
					text_voicenote.setVisibility(View.VISIBLE);
					lay_open_record.setVisibility(View.GONE);
					
					
			 myPlayer = new MediaPlayer();
//			MediaPlayer.create(con, R.raw.west_coast);
			Log.d("output", outputFile+"");
//			 myPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			 
			myPlayer.setDataSource(outputFile);
			
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
		case R.id.pause_sound:
		
//	      stop.setEnabled(false);
			
			try {
				
				           if (myPlayer != null) {
				
				               myPlayer.pause();
				
				             

				     	      lay_play_sound.setVisibility(View.VISIBLE);
				     	      lay_pause_sound.setVisibility(View.GONE);
				     	      Toast.makeText(getApplicationContext(), "Stop playing the recording...",
				     	      Toast.LENGTH_LONG).show();
				
				           }
				
				       } catch (Exception e) {
				
				           // TODO Auto-generated catch block
				
				            e.printStackTrace();
				
				        }

		
		break;
		case R.id.lay_text_cancel:
			if(myPlayer!=null)
			{
			myPlayer.stop();
			finish();
			}
			else
				finish();
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
		case R.id.lay_text_done:
			myPlayer.pause();
			text_save.setVisibility(View.VISIBLE);
			text_done.setVisibility(View.GONE);
			lay_play_sound.setVisibility(View.VISIBLE);
			lay_pause_sound.setVisibility(View.GONE);
			
			
			
			break;
		case R.id.text_done:
			myPlayer.pause();
			text_save.setVisibility(View.VISIBLE);
			text_done.setVisibility(View.GONE);
			lay_play_sound.setVisibility(View.VISIBLE);
			lay_pause_sound.setVisibility(View.GONE);
			break;
		case R.id.text_save:
			Intent data=new Intent();
			data.putExtra("sound_file", shf_name.getString("sound_file", ""));
			setResult(RESULT_OK, data);
			finish();
			break;
		case R.id.open_record_fade:
			
			Toast.makeText(con, "You Have To Delete The Previous Recording \n To Record A New One", 1000).show();
			break;
		case R.id.delete_audio:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Are You Sure You Want To Delete");
//            builder.setMessage("Alert")
//               .setTitle("Warning");

           
           
            
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	
                    // User clicked OK button
                	 File myDir = new File(outputFile); 
                	 myDir.mkdirs();
                	 
                	 File file=new File(outputFile);
                	 if (file.exists ())
     	        	{
     	        	file.delete (); 
     	        	
     	        	main_open_record.setVisibility(View.VISIBLE);
     	        	lay_open_record.setVisibility(View.VISIBLE);
               	 main_open_record_fade.setVisibility(View.GONE);
               	 lay_play_sound.setVisibility(View.GONE);
               	 lay_pause_sound.setVisibility(View.GONE);
               	 lay_seekbar.setVisibility(View.GONE);
               	 text_save.setVisibility(View.GONE);
               	 text_done.setVisibility(View.GONE);
     	        	}
                	 
             }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked cancel button
                	
                }
            });
            AlertDialog alert =builder.create();
            alert.show();
            break;
		default:
			break;
			
			
		case R.id.delete_audio_lay:
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setTitle("Are You Sure You Want To Delete");
//            builder.setMessage("Alert")
//               .setTitle("Warning");

           
           
            
            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	
                    // User clicked OK button
                	 File myDir = new File(outputFile); 
                	 myDir.mkdirs();
                	 
                	 File file=new File(outputFile);
                	 if (file.exists ())
     	        	{
     	        	file.delete (); 
     	        	
     	        	main_open_record.setVisibility(View.VISIBLE);
     	        	lay_open_record.setVisibility(View.VISIBLE);
               	 main_open_record_fade.setVisibility(View.GONE);
               	 lay_play_sound.setVisibility(View.GONE);
               	 lay_pause_sound.setVisibility(View.GONE);
               	 lay_seekbar.setVisibility(View.GONE);
               	 text_save.setVisibility(View.GONE);
               	 text_done.setVisibility(View.GONE);
     	        	}
                	 
             }
            });

            builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked cancel button
                	
                }
            });
            AlertDialog alert1 =builder1.create();
            alert1.show();
			
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
	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.open_record:
			lay_play_sound.setVisibility(View.VISIBLE);
			if(event.getAction() == MotionEvent.ACTION_DOWN) {
				
				
				lay_pause_sound.setVisibility(View.GONE);
				timer_voice.setVisibility(View.VISIBLE);
				try {
					
					 myAudioRecorder = new MediaRecorder();
				      myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				      myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				      myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
				      myAudioRecorder.setOutputFile(outputFile);
				      timer_voice.setBase(SystemClock.elapsedRealtime());
				      timer_voice.start();
			         myAudioRecorder.prepare();
			        
			         
			         
			         myAudioRecorder.start();
			         timer_voice.start();
			         SharedPreferences.Editor edit=shf_name.edit();
			         edit.putString("sound_file", outputFile);
			         edit.commit();
//			         sound_time=myAudioRecorder
			         
			         Toast.makeText(con, "start", 1000).show();
			      } catch (IllegalStateException e) {
			         // TODO Auto-generated catch block
			         e.printStackTrace();
			      } catch (IOException e) {
			         // TODO Auto-generated catch block
			         e.printStackTrace();
			      } 
		          return true;
//		      
//				else{
//					Toast.makeText(con, "again", 1000).show();
//				}
			}
			
			
			if (event.getAction() == MotionEvent.ACTION_UP) {
		        	 Toast.makeText(con, "stop", 1000).show();
		        	  myAudioRecorder.stop();
		        	
		        	
		        	  timer_voice.stop();
		        	  myAudioRecorder.release();
		        	  main_open_record.setVisibility(View.GONE);
		        	  main_open_record_fade.setVisibility(View.VISIBLE);
		        	  myAudioRecorder  = null;
		        	  text_save.setVisibility(View.VISIBLE);
		        	  return true;

		        }
			
			
			break;

		default:
			break;
		}
		
		
		return false;
	}

	

}
