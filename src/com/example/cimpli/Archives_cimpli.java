package com.example.cimpli;

import java.io.File;
import java.util.ArrayList;

import cimpli.services.DeleteUserJobs;
import cimpli.services.GetUserJobs;

import Models_Adapters.Archives_list_adapter;
import Models_Adapters.Archives_list_model;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Archives_cimpli extends Activity implements OnClickListener {
	
	TextView text_cancel,text_job_tittle;
	LinearLayout lay_text_cancel;
	String font_path,font_path_light;
	static Typeface tf;
	static Typeface tf_light;
	static SharedPreferences shf_name;
	static Context con;
	static int height;
	static int width;
	InputMethodManager imm;
	public static ArrayList<Archives_list_model> Get_archives_list=new ArrayList<Archives_list_model>();
	public static ListView archives_job_list;
	public static LinearLayout delete_lay;
	public static int check=0;
	public static ImageView delte_bar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archives_cimpli);
		archives_job_list=(ListView)findViewById(R.id.archives_job_list);
		
		delete_lay=(LinearLayout)findViewById(R.id.delete_lay);
		delete_lay.setVisibility(View.GONE);
		delete_lay.setOnClickListener(this);
		delte_bar=(ImageView)findViewById(R.id.delte_bar);
		delte_bar.setVisibility(View.GONE);
		text_cancel=(TextView)findViewById(R.id.text_cancel);
		text_job_tittle=(TextView)findViewById(R.id.text_job_tittle);
		
		font_path="fonts/Futura-Book.ttf";
		font_path_light="fonts/FuturaEF-Light.ttf";
		tf=Typeface.createFromAsset(getAssets(),font_path);
		tf_light=Typeface.createFromAsset(getAssets(),font_path_light);
		
		text_cancel.setTypeface(tf);
		text_job_tittle.setTypeface(tf);
		
		text_cancel.setOnClickListener(this);
		con=Archives_cimpli.this;
		DisplayMetrics displaymetrics = new DisplayMetrics();

		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
		imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		new GetUserJobs(con, height, width, imm, shf_name, shf_name.getString("access_token", ""),tf,tf_light).execute("main");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archives_cimpli, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_cancel:
			check=0;
			finish();
			break;
		case R.id.lay_text_cancel:
			check=0;
			finish();
			break;
		case R.id.delete_lay:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Are You Sure You Want To Delete");
			
			 builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	
	                    // User clicked OK button
	                	
	                	new DeleteUserJobs(con, shf_name,tf,tf_light,imm).execute("main");
	                	
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
		}
		
		
	}
	public static void getimnage() {
		if(	Archives_cimpli.check==1)
		{
		Archives_cimpli.delete_lay.setVisibility(View.VISIBLE);
		Archives_cimpli.delte_bar.setVisibility(View.VISIBLE);
		Archives_cimpli.archives_job_list.setAdapter(new Archives_list_adapter(con, Archives_cimpli.Get_archives_list, height, width, tf_light, tf,shf_name));
		}
		else if(	Archives_cimpli.check==0)
		{
			Archives_cimpli.delete_lay.setVisibility(View.GONE);
			Archives_cimpli.delte_bar.setVisibility(View.GONE);
		}
	}

}
