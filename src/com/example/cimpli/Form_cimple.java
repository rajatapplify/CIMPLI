package com.example.cimpli;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cimpli.services.Getgroups;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Form_cimple extends Activity implements OnClickListener {
	TextView text_cancel,text_FORM,text_done,time_date;
	TextView job_title_form;
	LinearLayout lay_text_cancel,lay_text_done,addition_layout,add_layout;
	EditText account_name,contact_name,from_phone,source_form,form_rate,form_rate2;
	ScrollView scrolll_main;
	String font_path,font_path_light;
	Typeface tf;
	Typeface tf_light;
	ImageView add_layout1;
	int w,h;
	int idsss1=-1;
	int idsss2=9;
	int idsss3=19;
	int idsss4=29;
	int idsss5=39;
	ArrayList<String> rate=new ArrayList<String>();
	ArrayList<String> rate2=new ArrayList<String>();
	ArrayList<String> spineer_id=new ArrayList<String>();
	ArrayList<String> descrption=new ArrayList<String>();
	ArrayList<String> hoursss=new ArrayList<String>();
	TextView spineer_items,rate1_chck;
	TextView rate2_chck;
	EditText Desc;
	SharedPreferences shf_name;
	EditText Hour;
	String[] laber_list={"Labour","Material","Other"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_cimple);
		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();
		h = display.getHeight();
		add_layout1=(ImageView)findViewById(R.id.add_layout1);
		
		scrolll_main=(ScrollView)findViewById(R.id.scrolll_main);
		
		job_title_form=(TextView)findViewById(R.id.job_title_form);
		account_name=(EditText)findViewById(R.id.account_name);
		contact_name=(EditText)findViewById(R.id.contact_name);
		from_phone=(EditText)findViewById(R.id.from_phone);
		source_form=(EditText)findViewById(R.id.source_form);
		form_rate=(EditText)findViewById(R.id.form_rate);
		form_rate2=(EditText)findViewById(R.id.form_rate2);
		
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
		
		addition_layout=(LinearLayout)findViewById(R.id.addition_layout);
		add_layout=(LinearLayout)findViewById(R.id.add_layout);
		add_layout.setOnClickListener(this);
		
		font_path="fonts/Futura-Book.ttf";
		font_path_light="fonts/FuturaEF-Light.ttf";
		
		tf=Typeface.createFromAsset(getAssets(),font_path);
		tf_light=Typeface.createFromAsset(getAssets(),font_path_light);
		
		 text_cancel=(TextView)findViewById(R.id.text_cancel);
		text_FORM=(TextView)findViewById(R.id.text_FORM);
		text_done=(TextView)findViewById(R.id.text_done);
		
		time_date=(TextView)findViewById(R.id.time_date);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        time_date.setText(formattedDate);
		text_cancel.setTypeface(tf);
		text_FORM.setTypeface(tf);
		text_done.setTypeface(tf);
		
		job_title_form.setText(dashboard.text_jobtittle.getText().toString());
		text_cancel.setOnClickListener(this);
		text_FORM.setOnClickListener(this);
		text_done.setOnClickListener(this);
	add_layout1.setOnClickListener(this);
		
		addView();
	}

	private void addView() {
		// TODO Auto-generated method stub
		 idsss1++;
		 idsss2++;
		 idsss3++;
		 idsss4++;
		 idsss5++;
		LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
		LinearLayout.LayoutParams layoutParams13 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,1);
		
		LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//		
		layoutParams11.setMargins(0, w/32, 0, 0);
		layoutParams11.gravity=Gravity.CENTER_VERTICAL;
		
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1.0f);
		LinearLayout.LayoutParams layoutParams_spinner = new LinearLayout.LayoutParams(w/8,w/16);
		layoutParams_spinner.gravity=Gravity.RIGHT;
		layoutParams_spinner.setMargins(w/64, 0, 0, 0);
		layoutParams1.gravity=Gravity.CENTER_VERTICAL;
		LinearLayout.LayoutParams layoutParams_chck = new LinearLayout.LayoutParams(w/4,w/9);
		LinearLayout.LayoutParams layoutParams_chck1 = new LinearLayout.LayoutParams(w/4,w/9);
		layoutParams_chck.setMargins(w/32, 0, 0, 0);
		
		LinearLayout first_lay=new LinearLayout(this);
		LinearLayout inside_first_lay=new LinearLayout(this);
		
		inside_first_lay.setBackgroundResource(R.drawable.header);
		first_lay.setBackgroundColor(Color.parseColor("#383838"));
		first_lay.setLayoutParams(layoutParams11);
		
		LinearLayout rate= new LinearLayout(this);
//		rate.setBackgroundColor(Color.WHITE);
		rate.setLayoutParams(layoutParams1);
		CheckBox rate_chk=new CheckBox(this);
		rate_chk.setText("Rate");
		rate_chk.setLayoutParams(layoutParams_chck);
		rate1_chck=new TextView(this);
		rate1_chck.setId(idsss1);
		
		rate1_chck.setText("0");
		rate_chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
				if(isChecked==true)
				{
					rate1_chck.setText("1");
				}
				
				else{
					rate1_chck.setText("0");
				}
			}
		});
		
//		rate1_chck.setVisibility(View.GONE);
	
//		if(rate_chk.isChecked())
//		{
//			
//		}
//		
		
		rate1_chck.setVisibility(View.GONE);
		
		
		
		rate.addView(rate_chk);
		rate.addView(rate1_chck);
		layoutParams1.gravity=Gravity.CENTER_VERTICAL|Gravity.LEFT;
		
		LinearLayout rate_2=new LinearLayout(this);
		rate_2.setPadding(w/32, 0, w/32, 0);
		
		
		CheckBox rate2_chk=new CheckBox(this);
		rate2_chck=new TextView(this);
		
		rate2_chk.setText("2nd Rate");
		rate2_chk.setLayoutParams(layoutParams_chck1);
		rate2_chck.setId(idsss2);
		rate2_chck.setText("0");
		rate2_chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Log.d("lats", isChecked+"");
				if(isChecked==true)
				{
					Toast.makeText(getBaseContext(), "hii", 1000).show();
					rate2_chck.setText("1");
				}
				else
					{
					rate2_chck.setText("0");
					}
				
			}
		});
	
//				if(rate2_chk.isChecked())
//		{
//			
//		}
//				else
//				{
//					rate2_chck.setText("0");
//				}
//				rate2_chck.setVisibility(View.GONE);
		rate2_chck.setVisibility(View.GONE);
		rate_2.addView(rate2_chk);
		rate_2.addView(rate2_chck);
		rate_2.setLayoutParams(layoutParams1);
		
		
		RelativeLayout.LayoutParams layoutParams_rel = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		LinearLayout rate_3=new LinearLayout(this);
//		rate_3.setOrientation(LinearLayout.VERTICAL);
		rate_3.setLayoutParams(layoutParams1);
		
		RelativeLayout rel_shw=new RelativeLayout(this);
		rel_shw.setLayoutParams(layoutParams_rel);
		
		final TextView show_text=new TextView(this);
		show_text.setText(laber_list[0]);
		show_text.setLayoutParams(layoutParams11);
		Spinner laber_sp=new Spinner(this);
		laber_sp.setLayoutParams(layoutParams_spinner);
		laber_sp.setBackgroundColor(Color.parseColor("#383838"));
		laber_sp.setAlpha(0);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_list_labour, laber_list);
		laber_sp.setAdapter(adapter);
		
		
		
		
		rel_shw.addView(show_text);
		rel_shw.addView(laber_sp);
		rate_3.addView(rel_shw);
		inside_first_lay.setLayoutParams(layoutParams12);
		inside_first_lay.addView(rate);
		inside_first_lay.addView(rate_2);
	
		inside_first_lay.addView(rate_3);
		first_lay.addView(inside_first_lay);
		
//		laber_sp.setId(idsss3);
		spineer_items=new TextView(this);
		
		rel_shw.addView(spineer_items);
		spineer_items.setId(idsss3);
		spineer_items.setText(laber_list[0]);
		laber_sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				int index=arg0.getSelectedItemPosition();
				for(int i=0;i<laber_list.length;i++)
				{
					if(laber_list[index].equalsIgnoreCase(laber_list[i]))
					{
						Log.d("check", laber_list[i]+"");
						spineer_items.setText(laber_list[i]);
						spineer_items.setVisibility(View.GONE);
						show_text.setText(laber_list[i]);
					}
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
//		spineer_items.setVisibility(View.GONE);
		

		LinearLayout.LayoutParams Second_lay_parm = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//		Second_lay_parm.setMargins(0, w/32, 0, 0);
		
		
		LinearLayout Second_lay=new LinearLayout(this);
		LinearLayout insider_Second_lay=new LinearLayout(this);
		insider_Second_lay.setLayoutParams(Second_lay_parm);
		insider_Second_lay.setBackgroundResource(R.drawable.header);
		Second_lay.setBackgroundColor(Color.parseColor("#383838"));
		Second_lay.setLayoutParams(layoutParams11);
		
		
		Desc=new EditText(this);
		Desc.setLayoutParams(layoutParams12);
		Desc.setSingleLine(true);
		Desc.setId(idsss4);
		Desc.setHint("Description");
		Desc.setTextSize(18);
		Desc.setTextColor(Color.WHITE);
		Desc.setBackgroundColor(Color.TRANSPARENT);
		insider_Second_lay.addView(Desc);
		Second_lay.addView(insider_Second_lay);
		
		LinearLayout Third_lay=new LinearLayout(this);
		LinearLayout inside_Third_lay=new LinearLayout(this);
		inside_Third_lay.setBackgroundResource(R.drawable.header);
		inside_Third_lay.setLayoutParams(Second_lay_parm);
		Third_lay.setBackgroundColor(Color.parseColor("#383838"));
		
		Third_lay.setLayoutParams(layoutParams11);
		 Hour=new EditText(this);
		Hour.setLayoutParams(layoutParams12);
		Hour.setId(idsss5);
		Hour.setHint("Hours(If Applicable)");
		Hour.setTextSize(18);
		Hour.setSingleLine(true);
		Hour.setTextColor(Color.WHITE);
		Hour.setBackgroundColor(Color.TRANSPARENT);
		inside_Third_lay.addView(Hour);
		Third_lay.addView(inside_Third_lay);
		
		View v=new View(this);
		v.setBackgroundColor(Color.parseColor("#1e1e1e"));
		v.setLayoutParams(layoutParams13);
		
		addition_layout.addView(v);
		addition_layout.addView(first_lay);
//		addition_layout.addView(v);
		addition_layout.addView(Second_lay);
//		addition_layout.addView(v);
		addition_layout.addView(Third_lay);
//		addition_layout.addView(v);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_cimple, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.text_done:  
//			
//			break;
		case R.id.text_cancel:
			finish();  
			break;
		case R.id.lay_text_cancel:
			finish();
			break;
		case R.id.text_done:
			if(account_name.getText().toString().trim().length()>0||contact_name.getText().toString().trim().length()>0
					||from_phone.getText().toString().trim().length()>0||source_form.getText().toString().trim().length()>0||
					form_rate.getText().toString().trim().length()>0||form_rate2.getText().toString().trim().length()>0)
			{
				
			
			rate=new ArrayList<String>();
			 rate2=new ArrayList<String>();
		spineer_id=new ArrayList<String>();
			 descrption=new ArrayList<String>();
	hoursss=new ArrayList<String>();
	Log.d("id",idsss5+"");
	for(int i=0;i<=idsss1;i++)
	{
		TextView final_rate1=(TextView)findViewById(i);
		rate.add(final_rate1.getText().toString());
	}
	for(int j=10;j<=idsss2;j++)
	{
		TextView final_rate2=(TextView)findViewById(j);
		rate2.add(final_rate2.getText().toString());
	}
	for(int k=20;k<=idsss3;k++)
	{
		TextView final_spinner_value=(TextView)findViewById(k);
		spineer_id.add(final_spinner_value.getText().toString());
//		final_spinner_value.setVisibility(View.GONE);
	}
	for(int l=30;l<=idsss4;l++)
	{
		EditText fianl_desc=(EditText)Form_cimple.this.findViewById(l);
		if(fianl_desc.getText().toString().trim().length()>0)
		{
			descrption.add(fianl_desc.getText().toString());
		}
		else{
			descrption.add("no info filled");
		}
	}
	
	
	
	
	for(int i=40;i<=idsss5;i++)
	{
		Log.d("id",i+"");
//		
		EditText hours1=(EditText)this.findViewById(i);
		if(hours1.getText().toString().trim().length()>0)
		{
			hoursss.add(hours1.getText().toString());
		}
		else
		{
			hoursss.add("no info filled");
		}
	}
	
	Log.d("rate", rate.size()+"");
	Log.d("rate", rate2.size()+"");
	Log.d("rate", spineer_id.size()+"");
	Log.d("rate", descrption.size()+"");
	Log.d("rate", hoursss.size()+"");
	
	SharedPreferences.Editor edit=shf_name.edit();
	
	edit.putString("job_title_form", job_title_form.getText().toString().trim());
	edit.putString("account_name", account_name.getText().toString().trim());
	edit.putString("contact_name", contact_name.getText().toString().trim());
	edit.putString("from_phone", from_phone.getText().toString().trim());
	edit.putString("source_form", source_form.getText().toString().trim());
	edit.putString("form_rate", form_rate.getText().toString().trim());
	edit.putString("form_rate2", form_rate2.getText().toString().trim());
	edit.putInt("rate_length", rate.size());
	StringBuilder str=new StringBuilder();
	StringBuilder str1=new StringBuilder();
	StringBuilder str2=new StringBuilder();
	StringBuilder str3=new StringBuilder();
	StringBuilder str4=new StringBuilder();
	
	for(int rateee=0;rateee<rate.size();rateee++)
	{
		if((rateee+1)==rate.size())
		{
			str.append(rate.get(rateee));
			str1.append(rate2.get(rateee));
			str2.append(spineer_id.get(rateee));
			str3.append(descrption.get(rateee));
			str4.append(hoursss.get(rateee));
		}
		else
		{
		str.append(rate.get(rateee)+",");
		str1.append(rate2.get(rateee)+",");
		str2.append(spineer_id.get(rateee)+",");
		str3.append(descrption.get(rateee)+",");
		str4.append(hoursss.get(rateee)+",");
		}
		
		
		Log.d("Rate", rate.get(rateee)+"");
		Log.d("Rate2", rate2.get(rateee)+"");
		Log.d("spineer_item", spineer_id.get(rateee)+"");
		Log.d("descrption", descrption.get(rateee)+"");
		Log.d("hours_log", hoursss.get(rateee)+"");
		
		
		
	}
	Log.d("str1", str.toString());
	Log.d("str2",str1.toString());
	Log.d("str3",str2.toString());
	Log.d("str4", str3.toString());
	Log.d("str5",str4.toString());
	edit.putString("rate",str.toString());
	edit.putString("rate2",str1.toString());
	edit.putString("spineer_id", str2.toString());
	edit.putString("descrption", str3.toString());
	edit.putString("hoursss", str4.toString());
	edit.commit();
	
	Intent data=new Intent();
	data.putExtra("sound_file", shf_name.getString("job_title_form", ""));
	data.putExtra("sound_file", shf_name.getString("account_name", ""));
	data.putExtra("sound_file", shf_name.getString("contact_name", ""));
	data.putExtra("sound_file", shf_name.getString("from_phone", ""));
	data.putExtra("sound_file", shf_name.getString("source_form", ""));
	data.putExtra("sound_file", shf_name.getString("form_rate", ""));
	data.putExtra("sound_file", shf_name.getString("form_rate2", ""));
	setResult(RESULT_OK, data);
	finish();
			}
			else
			{
				SharedPreferences.Editor edit=shf_name.edit();
				edit.putString("rate","");
				edit.putString("rate2","");
				edit.putString("spineer_id", "");
				edit.putString("descrption", "");
				edit.putString("hoursss", "");
				edit.commit();
			}
	break;
			
			
			
		case R.id.add_layout1:
			scrolll_main.fullScroll(ScrollView.FOCUS_DOWN);
			addView();
		
		break;
		
//		case R.id.text_done:
//	
//			break;

		default:
			break;
		}
		
	}

}
