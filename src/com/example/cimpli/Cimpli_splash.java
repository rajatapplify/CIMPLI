package com.example.cimpli;



import com.crashlytics.android.Crashlytics;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

public class Cimpli_splash extends Activity {
	private static int SPLASH_TIME_OUT = 3000;
	SharedPreferences shf_name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Crashlytics.start(this);
		setContentView(R.layout.activity_cimpli_splash);
		
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(shf_name.getString("access_token", "").equalsIgnoreCase("")||shf_name.getString("access_token", "")==null)
				{
					Intent i=new Intent(Cimpli_splash.this,Signup.class);
					startActivity(i);
					finish();
				}
				else
				{
					Intent i=new Intent(Cimpli_splash.this,dashboard.class);
					startActivity(i);
					finish();
				}
				
			}
		}, SPLASH_TIME_OUT);
	}
	}




