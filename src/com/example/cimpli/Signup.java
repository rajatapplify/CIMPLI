package com.example.cimpli;

import java.util.ArrayList;

import cimpli.services.Getgroups;
import cimpli.services.Registration;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends Activity implements OnClickListener {
	
	TextView text_cimpli,text_done;
	EditText signup_last,signup_first;
	Context con;
	String font_path,font_path_light;
	Typeface tf;
	Typeface tf_light;
	SharedPreferences shf_name;
	public static Spinner spinner_groupname;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
		
		SharedPreferences.Editor edit= shf_name.edit();
		
		
		
		font_path="fonts/Futura-Book.ttf";
		font_path_light="fonts/FuturaEF-Light.ttf";
		tf=Typeface.createFromAsset(getAssets(),font_path);
		tf_light=Typeface.createFromAsset(getAssets(),font_path_light);
		
		spinner_groupname=(Spinner)findViewById(R.id.spinner_groupname);
		
		text_done=(TextView)findViewById(R.id.text_done);
		text_cimpli=(TextView)findViewById(R.id.text_cimpli);
		text_cimpli.setTypeface(tf);
		text_done.setTypeface(tf);
		
		signup_first=(EditText)findViewById(R.id.signup_first);
		signup_last=(EditText)findViewById(R.id.signup_last);
		
		signup_first.setTypeface(tf_light);
		signup_last.setTypeface(tf_light);
		
		text_done.setOnClickListener(this);
		
		con=Signup.this;
		
		new Getgroups(con,shf_name).execute("main");
		spinner_groupname.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				int index=arg0.getSelectedItemPosition();
				if(arg2==0)
				{
					
				}
				else
				{
				for(int i=0;i<Getgroups.grour_name.size();i++)
				{
					if(Getgroups.grour_name.get(index).equalsIgnoreCase(Getgroups.grour_name.get(i)))
					{
						SharedPreferences.Editor edit= shf_name.edit();
						String group_iddd=Getgroups.grour_id.get(i);
						
						edit.putString("group_name", group_iddd);
						edit.commit();
						text_done.setVisibility(View.VISIBLE);
						Toast.makeText(con,Getgroups.grour_name.get(index).toString()+"//"+group_iddd,Toast.LENGTH_LONG).show();
						
					}
				}
				
				}	
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		Getgroups.grour_id.clear();
		Getgroups.grour_name.clear();
		
		super.onBackPressed();
	}

	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		 InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                 INPUT_METHOD_SERVICE);
		 imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		 
		 return true;
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_done:
			if(signup_first.getText().toString().trim().length()>0||signup_last.getText().toString().trim().length()>0)
			{
				SharedPreferences.Editor edit= shf_name.edit();
				edit.putString("first_name",signup_first.getText().toString().trim());
				edit.putString("last_name",signup_last.getText().toString().trim());
				
				edit.commit();
				new Registration(con, shf_name,shf_name.getString("first_name", ""),shf_name.getString("last_name", ""),shf_name.getString("group_name", "")).execute("main");
				
				
//			Intent i=new Intent(con,dashboard.class);
//			startActivity(i);
//			finish();
				
				
			}
			else
			{
				Toast.makeText(con, "Please Fill All Mandatory Fields", 1000).show();
			}
			
			
			
			break;

		default:
			break;
		}
		
	}

	

}
