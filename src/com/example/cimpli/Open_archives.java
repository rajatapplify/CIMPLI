package com.example.cimpli;

import image_loader_cimpli.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Open_archives extends Activity implements OnClickListener {
	TextView text_back,text_cimpli,text_voicenote,text_scanimg,text_form,text_form_blank,
	text_scanimg_blank,text_voicenote_blank,text_jobtittle;
	
	ImageView img__voicenote,img_scanimg,img_form,arrow_voicenote,arrow_scanimg,arrow_form;
	String font_path,font_path_light;
	Typeface tf;
	Typeface tf_light;
	SharedPreferences shf_name;
	String url,sound_url;
	ImageLoader imgload;
	ProgressDialog show_dialog;
	int height,width;
	Context ctxxx;
	public static String result;
	LinearLayout main_voice_note,main_voice_note_blank,main_scan_img,main_scan_img_blank,main_form_layout,main_form_layout_blank;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_archives);
		
		ctxxx=Open_archives.this;
		imgload=new ImageLoader(this.ctxxx);
		
		main_voice_note=(LinearLayout)findViewById(R.id.main_voice_note);
		main_voice_note_blank=(LinearLayout)findViewById(R.id.main_voice_note_blank);
		main_scan_img=(LinearLayout)findViewById(R.id.main_scan_img);
		main_scan_img_blank=(LinearLayout)findViewById(R.id.main_scan_img_blank);
		main_form_layout=(LinearLayout)findViewById(R.id.main_form_layout);
		main_form_layout_blank=(LinearLayout)findViewById(R.id.main_form_layout_blank);
	
		main_form_layout_blank.setOnClickListener(this);
		main_form_layout.setOnClickListener(this);
		main_scan_img_blank.setOnClickListener(this);
		main_scan_img.setOnClickListener(this);
		main_voice_note_blank.setOnClickListener(this);
		main_voice_note.setOnClickListener(this);
		
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
		font_path="fonts/Futura-Book.ttf";
		font_path_light="fonts/FuturaEF-Light.ttf";
		Display display = getWindowManager().getDefaultDisplay(); 
		width = display.getWidth();
		height = display.getHeight();
		
		text_voicenote=(TextView)findViewById(R.id.text_voicenote);
		text_scanimg=(TextView)findViewById(R.id.text_scanimg);
		text_form=(TextView)findViewById(R.id.text_form);
		text_cimpli=(TextView)findViewById(R.id.text_cimpli);
		text_jobtittle=(TextView)findViewById(R.id.text_jobtittle);
		
		
		
		text_back=(TextView)findViewById(R.id.text_back);
		text_scanimg_blank=(TextView)findViewById(R.id.text_scanimg_blank);
		text_form_blank=(TextView)findViewById(R.id.text_form_blank);
		text_voicenote_blank=(TextView)findViewById(R.id.text_voicenote_blank);
		
		tf=Typeface.createFromAsset(getAssets(),font_path);
		tf_light=Typeface.createFromAsset(getAssets(),font_path_light);
		
		text_back.setTypeface(tf);
		text_cimpli.setTypeface(tf);
		text_voicenote.setTypeface(tf_light);
		text_scanimg.setTypeface(tf_light);
		text_form.setTypeface(tf_light);
		text_jobtittle.setTypeface(tf_light);
		
		
		text_voicenote_blank.setTypeface(tf_light);
		text_scanimg_blank.setTypeface(tf_light);
		text_form_blank.setTypeface(tf_light);
		
		text_back.setOnClickListener(this);
		text_jobtittle.setText(shf_name.getString("job_tittle_archives", ""));
		url=shf_name.getString("imag_path_archive", "");
		Log.d("value", shf_name.getString("imag_path_archive", ""));
		Log.d("height", height+"");
		Log.d("value", width+"");
		
		sound_url=shf_name.getString("sound_path_archive", "");
		Log.d("value", shf_name.getString("sound_path_archive", ""));
		
		if(shf_name.getString("imag_path_archive", "")==null||shf_name.getString("imag_path_archive", "").equalsIgnoreCase(""))
		{
			
			main_scan_img.setVisibility(View.GONE);
			
			main_scan_img_blank.setVisibility(View.VISIBLE);
			
		}
		else
		{
			main_scan_img.setVisibility(View.VISIBLE);
			
			main_scan_img_blank.setVisibility(View.GONE);
		}
		if(shf_name.getString("sound_path_archive", "")==null||shf_name.getString("sound_path_archive", "").equalsIgnoreCase(""))
		{
			main_voice_note.setVisibility(View.GONE);
			
			main_voice_note_blank.setVisibility(View.VISIBLE);
		
		}
		else
		{
			main_voice_note.setVisibility(View.VISIBLE);
			
			main_voice_note_blank.setVisibility(View.GONE);
		}
		
		if(shf_name.getString("from_path_archive", "")==null||shf_name.getString("from_path_archive", "").equalsIgnoreCase(""))
		{
			main_form_layout.setVisibility(View.GONE);
			
			main_form_layout_blank.setVisibility(View.VISIBLE);
			
			
		}
		else
		{
			main_form_layout.setVisibility(View.VISIBLE);
			
			main_form_layout_blank.setVisibility(View.GONE);
		}
		try{
			
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
            .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
            .penaltyLog().build());
			
			result=sound_url.substring(sound_url.lastIndexOf("/")+1);
//	        Toast.makeText(ctxxx, result, 1000).show();
			 URL url = new URL(sound_url);
			 HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			 urlConnection.setRequestMethod("GET");
		        urlConnection.setDoOutput(true);
		        urlConnection.connect();
		        
		        String path=Environment.getExternalStorageDirectory().toString();
		        File full_path=new File(path+ "/cimpli/sound");
		       full_path.mkdirs();
		       File final_path=new File(full_path,result);
		        
//		        File SDCardRoot = Environment.getExternalStorageDirectory();
		        
//		        File file = new File(SDCardRoot,result);
		       
		       
		        FileOutputStream fileOutput = new FileOutputStream(new File(full_path,result));
		        InputStream inputStream = urlConnection.getInputStream();
		        int totalSize = urlConnection.getContentLength();
		        int downloadedSize = 0;
		        byte[] buffer = new byte[1024];
		        int bufferLength = 0; 
		        while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
		        	  fileOutput.write(buffer, 0, bufferLength);
		        	  downloadedSize += bufferLength;
//		        	  updateProgress(downloadedSize, totalSize);
		        	 
		        }
		        fileOutput.close();			
			

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		imgload.DisplayImage(url, null, null, height, width);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.open_archives, menu);
		return true;
	}

	@SuppressWarnings("unused")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.main_voice_note:
			Intent i=new Intent(ctxxx,Play_sound.class);
			startActivity(i);
			
			
			
			break;
		case R.id.main_voice_note_blank:
			
			break;
		case R.id.main_scan_img:
			final Dialog dialog=new Dialog(Open_archives.this,android.R.style.Theme_Light);
			dialog.setContentView(R.layout.image_show);
//			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			ImageView show_image=(ImageView)dialog.findViewById(R.id.show_image);
			LinearLayout show_main_layout=(LinearLayout)dialog.findViewById(R.id.show_main_layout);
			ProgressBar prog_show_image=(ProgressBar)dialog.findViewById(R.id.prog_show_image);
			
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width, height);
			show_main_layout.setLayoutParams(params);
			imgload.DisplayImage(url, show_image, prog_show_image, height, width);
			
			dialog.show();
			break;
		case R.id.main_scan_img_blank:
			
			break;
		case R.id.main_form_layout:
			Intent open=new Intent(ctxxx,Open_form.class);
			startActivity(open);
			
			break;
		case R.id.main_form_layout_blank:
			break;
		case R.id.text_back:
			finish();
			break;
		default:
			break;
		}
		
	}
	
	public void updateProgress(int currentSize, int totalSize)
	{
//		 Log.d("download zize",currentSize+""+"//"+totalSize+"" );
//		 text_show.setProgress((currentSize/totalSize)*100);
//		text_show.((currentSize/totalSize)*100+"%"); 
	show_dialog=new ProgressDialog(ctxxx);
	show_dialog.setMessage("Downloading files please wait...");
	show_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	show_dialog.setProgress((currentSize/totalSize)*100);
	show_dialog.setIndeterminate(true);
    show_dialog.show();
	
		} 

}
