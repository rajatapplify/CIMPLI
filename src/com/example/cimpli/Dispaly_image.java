package com.example.cimpli;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Dispaly_image extends Activity implements OnClickListener {
	
	RelativeLayout main_display_image;
	ImageView main_image,delete_image;
	SharedPreferences shf_name;
	String imagepath;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dispaly_image);
		
		main_image=(ImageView)findViewById(R.id.main_image);
		delete_image=(ImageView)findViewById(R.id.delete_image);
		delete_image.setOnClickListener(this);
		main_display_image=(RelativeLayout)findViewById(R.id.main_display_image);
		
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
		Log.d("value", shf_name.getString("userpic", ""));
		imagepath=shf_name.getString("userpic", "");
	try{
//		 byte [] encodeByte=Base64.decode(imagepath,Base64.DEFAULT);
//	       Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
	       
	       main_image.setImageBitmap(dashboard.bitmap);
	       
	      
	     }catch(Exception e){
	       e.getMessage();
	       
	     }
		
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.delete_image:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Are You Sure You Want To Delete");
//            builder.setMessage("Alert")
//               .setTitle("Warning");

           
           
            
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	
                    // User clicked OK button
//                	 File myDir = new File(outputFile); 
//                	 myDir.mkdirs();
//                	 
//                	 File file=new File(outputFile);
                	 if (dashboard.file.exists ())
     	        	{
     	        	dashboard.file.delete (); 
     	        	dashboard.main_scan_img_done.setVisibility(View.GONE);
     	        	dashboard.main_scan_img.setVisibility(View.VISIBLE);
     	        	dashboard.selectedImageUri=null;
     	        	finish();
     	        	
     	        	}
//                	 main_open_record.setVisibility(View.VISIBLE);
//                	 main_open_record_fade.setVisibility(View.GONE);
//                	 lay_play_sound.setVisibility(View.GONE);
//                	 lay_pause_sound.setVisibility(View.GONE);
//                	 lay_seekbar.setVisibility(View.GONE);
//                	 text_save.setVisibility(View.GONE);
                	 
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

	

}
