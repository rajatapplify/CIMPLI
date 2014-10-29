package com.example.cimpli;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Play_sound extends Activity implements OnClickListener {
	
	ImageView playbutton_playsound,playbutton_pausesound;
	TextView back_playsound,voicenote_playsound;
	String font_path,font_path_light;
	Typeface tf;
	Typeface tf_light;
	 MediaPlayer myPlayer ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_sound);
		
		playbutton_playsound=(ImageView)findViewById(R.id.playbutton_playsound);
		playbutton_pausesound=(ImageView)findViewById(R.id.playbutton_pausesound);
		
		back_playsound=(TextView)findViewById(R.id.back_playsound);
		voicenote_playsound=(TextView)findViewById(R.id.voicenote_playsound);
		
		font_path="fonts/Futura-Book.ttf";
		font_path_light="fonts/FuturaEF-Light.ttf";
		
		tf=Typeface.createFromAsset(getAssets(),font_path);
		tf_light=Typeface.createFromAsset(getAssets(),font_path_light);
		
		voicenote_playsound.setTypeface(tf);
		back_playsound.setTypeface(tf);
		
		back_playsound.setOnClickListener(this);
		playbutton_playsound.setOnClickListener(this);
		playbutton_pausesound.setOnClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_sound, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back_playsound:
			if(myPlayer!=null)
			{
			myPlayer.stop();
			finish();
			}
			else
				finish();
			break;
		case R.id.playbutton_pausesound:
			try {
				
		           if (myPlayer != null) {
		
		               myPlayer.pause();
		               playbutton_pausesound.setVisibility(View.GONE);
		               playbutton_playsound.setVisibility(View.VISIBLE);
		               
		
		           }
			}
		           catch (Exception e) {
					// TODO: handle exception
				}
			
			break;
			case R.id.playbutton_playsound:
				try{
					 myPlayer = new MediaPlayer();
					 Log.d("sound", Open_archives.result);
					 String output=Environment.getExternalStorageDirectory().
						      getAbsolutePath()+"/cimpli/sound/"+Open_archives.result;
					 myPlayer.setDataSource(output);
					 
					 myPlayer.prepare();
						myPlayer.start();
						 playbutton_pausesound.setVisibility(View.VISIBLE);
			               playbutton_playsound.setVisibility(View.GONE);

					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				break;

		default:
			break;
		}
		
	}

}
