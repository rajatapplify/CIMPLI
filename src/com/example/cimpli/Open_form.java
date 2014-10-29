package com.example.cimpli;

import cimpli.services.Form_cimpli_set;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Open_form extends Activity implements OnClickListener {
Context ctxx;
SharedPreferences shf_name;
String font_path,font_path_light;
Typeface tf;
Typeface tf_light;
int w,h;
TextView account_name_hint,account_name,contact_name_hint,contact_name,phone_hint,form_phone,
source_hint,form_source,rate_hint,form_rate,ndrate_hint,from_ndrate,text_FORM,text_cancel;
LinearLayout addition_layout,main_lay_openform;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_form);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();
		h = display.getHeight();
		
		main_lay_openform=(LinearLayout)findViewById(R.id.main_lay_openform);
		addition_layout=(LinearLayout)findViewById(R.id.addition_layout);
		
		
		text_cancel=(TextView)findViewById(R.id.text_cancel);
		text_FORM=(TextView)findViewById(R.id.text_FORM);
		account_name_hint=(TextView)findViewById(R.id.account_name_hint);
		account_name=(TextView)findViewById(R.id.account_name);
		contact_name_hint=(TextView)findViewById(R.id.contact_name_hint);
		contact_name=(TextView)findViewById(R.id.contact_name);
		phone_hint=(TextView)findViewById(R.id.phone_hint);
		form_phone=(TextView)findViewById(R.id.form_phone);
		form_source=(TextView)findViewById(R.id.form_source);
		rate_hint=(TextView)findViewById(R.id.rate_hint);
		form_rate=(TextView)findViewById(R.id.form_rate);
		ndrate_hint=(TextView)findViewById(R.id.ndrate_hint);
		from_ndrate=(TextView)findViewById(R.id.from_ndrate);
		source_hint=(TextView)findViewById(R.id.source_hint);
		
		text_FORM.setTypeface(tf);
		account_name_hint.setTypeface(tf_light);
		account_name.setTypeface(tf_light);
		contact_name_hint.setTypeface(tf_light);
		contact_name.setTypeface(tf_light);
		phone_hint.setTypeface(tf_light);
		form_phone.setTypeface(tf_light);
		form_source.setTypeface(tf_light);
		rate_hint.setTypeface(tf_light);
		form_rate.setTypeface(tf_light);
		ndrate_hint.setTypeface(tf_light);
		from_ndrate.setTypeface(tf_light);
		source_hint.setTypeface(tf_light);
		text_FORM.setTypeface(tf_light);
		
		
		text_cancel.setOnClickListener(this);
		ctxx=Open_form.this;
		font_path="fonts/Futura-Book.ttf";
		font_path_light="fonts/FuturaEF-Light.ttf";
		tf=Typeface.createFromAsset(getAssets(),font_path);
		tf_light=Typeface.createFromAsset(getAssets(),font_path_light);
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
		
		
		
		new Form_cimpli_set(ctxx, shf_name, tf, tf_light,account_name,contact_name,form_phone,form_source,form_rate,from_ndrate,addition_layout,w,h).execute("main");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.open_form, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.text_cancel:
			finish();
			break;

		default:
			break;
		}
	}

}
