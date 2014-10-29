package com.example.cimpli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import cimpli.services.User_jobs;



import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class dashboard extends Activity implements OnClickListener {
	
	InputMethodManager imm;
	TextView text_acrhives,text_done,text_voicenote,text_scanimg,text_form,text_cimpli,
	text_voicenote_done,text_scanimg_done,text_form_done;
	ImageView img__voicenote,img_scanimg,img_form,arrow_voicenote,arrow_scanimg,arrow_form,
	img__voicenote_done,arrow_img_done,img_scanimg_done,arrow_scanimg_done,img_form_done,arrow_form_done;
	public static EditText text_jobtittle;
	LinearLayout lay_text_acrhives,lay_text_done,lay_img_voicenote,lay_text_voicenote,lay_arrow_voicenote,
	lay_img_scanimg,lay_text_scanimg,lay_arrow_scanimg,lay_img_form;
	static LinearLayout main_form_layout;
	LinearLayout lay_text_form;
	LinearLayout lay_arrow_form;
	static LinearLayout main_voice_note;
	static LinearLayout main_form_layout_done;
	static LinearLayout main_voice_note_done;
	
	 public static Uri selectedImageUri;
	public static LinearLayout main_scan_img,main_scan_img_done;
	public static LinearLayout main_project_saved,main_dashboard;
	String font_path,font_path_light;
	Typeface tf;
	Typeface tf_light;
	public boolean check=true;
	SharedPreferences shf_name;
	int request_code=1;
	public static Bitmap bitmap = null;
	Uri imageUri;
	private int h,w;
	Context con;
String jobtittle="";
	private static final int PICK_Camera_IMAGE= 2;
	private static final int pick_voice_mode= 1;
	private static final int pick_form= 3;
	
	public static File file;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		shf_name=this.getSharedPreferences("cimpli", MODE_PRIVATE);
		
		imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		
		font_path="fonts/Futura-Book.ttf";
		font_path_light="fonts/FuturaEF-Light.ttf";
		tf=Typeface.createFromAsset(getAssets(),font_path);
		tf_light=Typeface.createFromAsset(getAssets(),font_path_light);
		
		text_acrhives=(TextView)findViewById(R.id.text_acrhives);
		text_done=(TextView)findViewById(R.id.text_done);
		text_voicenote=(TextView)findViewById(R.id.text_voicenote);
		text_scanimg=(TextView)findViewById(R.id.text_scanimg);
		text_form=(TextView)findViewById(R.id.text_form);
		text_cimpli=(TextView)findViewById(R.id.text_cimpli);
		
		text_voicenote_done=(TextView)findViewById(R.id.text_voicenote_done);
		text_scanimg_done=(TextView)findViewById(R.id.text_scanimg_done);
		text_form_done=(TextView)findViewById(R.id.text_form_done);
		
		
		con=dashboard.this;
		
		text_acrhives.setTypeface(tf);
		text_done.setTypeface(tf);
		text_cimpli.setTypeface(tf);
		text_voicenote.setTypeface(tf_light);
		text_scanimg.setTypeface(tf_light);
		text_form.setTypeface(tf_light);
		
		text_voicenote_done.setTypeface(tf_light);
		text_scanimg_done.setTypeface(tf_light);
		text_form_done.setTypeface(tf_light);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();
		h = display.getHeight();
		SharedPreferences.Editor edit=shf_name.edit();
		edit.putString("height",h+"");
		edit.putString("width",w+"");

		
		edit.commit();
		
		text_acrhives.setOnClickListener(this);
		text_done.setOnClickListener(this);
		text_voicenote.setOnClickListener(this);
		text_scanimg.setOnClickListener(this);
		text_form.setOnClickListener(this);
		
		text_voicenote_done.setOnClickListener(this);
		text_scanimg_done.setOnClickListener(this);
		text_form_done.setOnClickListener(this);
		
		
		text_jobtittle=(EditText)findViewById(R.id.text_jobtittle);
		text_jobtittle.setTypeface(tf_light);
		
		img__voicenote=(ImageView)findViewById(R.id.img__voicenote);
		img_scanimg=(ImageView)findViewById(R.id.img_scanimg);
		img_form=(ImageView)findViewById(R.id.img_form);
		arrow_voicenote=(ImageView)findViewById(R.id.arrow_voicenote);
		arrow_scanimg=(ImageView)findViewById(R.id.arrow_scanimg);
		arrow_form=(ImageView)findViewById(R.id.arrow_form);
		
		
		img__voicenote_done=(ImageView)findViewById(R.id.img__voicenote_done);
		arrow_img_done=(ImageView)findViewById(R.id.arrow_img_done);
		img_scanimg_done=(ImageView)findViewById(R.id.img_scanimg_done);
		arrow_scanimg_done=(ImageView)findViewById(R.id.arrow_scanimg_done);
		img_form_done=(ImageView)findViewById(R.id.img_form_done);
		arrow_form_done=(ImageView)findViewById(R.id.arrow_form_done);
		
		
		img__voicenote_done.setOnClickListener(this);
		arrow_img_done.setOnClickListener(this);
		img_scanimg_done.setOnClickListener(this);
		arrow_scanimg_done.setOnClickListener(this);
		img_form_done.setOnClickListener(this);
		arrow_form_done.setOnClickListener(this);
		
		
		img__voicenote.setOnClickListener(this);
		img_scanimg.setOnClickListener(this);
		img_form.setOnClickListener(this);
		arrow_voicenote.setOnClickListener(this);
		img_form.setOnClickListener(this);
		arrow_form.setOnClickListener(this);
		
		lay_text_acrhives=(LinearLayout)findViewById(R.id.lay_text_acrhives);
		lay_text_done=(LinearLayout)findViewById(R.id.lay_text_done);
		lay_img_voicenote=(LinearLayout)findViewById(R.id.lay_img_voicenote);
		lay_text_voicenote=(LinearLayout)findViewById(R.id.lay_text_voicenote);
		lay_arrow_voicenote=(LinearLayout)findViewById(R.id.lay_arrow_voicenote);
		lay_img_scanimg=(LinearLayout)findViewById(R.id.lay_img_scanimg);
		lay_text_scanimg=(LinearLayout)findViewById(R.id.lay_text_scanimg);
		lay_arrow_scanimg=(LinearLayout)findViewById(R.id.lay_arrow_scanimg);
		lay_img_form=(LinearLayout)findViewById(R.id.lay_img_form);
		lay_text_form=(LinearLayout)findViewById(R.id.lay_text_form);
		lay_arrow_form=(LinearLayout)findViewById(R.id.lay_arrow_form);
		main_scan_img=(LinearLayout)findViewById(R.id.main_scan_img);
		main_voice_note=(LinearLayout)findViewById(R.id.main_voice_note);
		main_form_layout=(LinearLayout)findViewById(R.id.main_form_layout);
		
		main_project_saved=(LinearLayout)findViewById(R.id.main_project_saved);
		main_dashboard=(LinearLayout)findViewById(R.id.main_dashboard);
		
		
		main_scan_img_done=(LinearLayout)findViewById(R.id.main_scan_img_done);
		main_voice_note_done=(LinearLayout)findViewById(R.id.main_voice_note_done);
		main_form_layout_done=(LinearLayout)findViewById(R.id.main_form_layout_done);
		
		
		
		main_scan_img.setOnClickListener(this);
		lay_img_scanimg.setOnClickListener(this);
		arrow_scanimg.setOnClickListener(this);
		img_scanimg.setOnClickListener(this);
		
		lay_text_acrhives.setOnClickListener(this);
		
		lay_text_done.setOnClickListener(this);
		
		text_voicenote.setOnClickListener(this);
		arrow_voicenote.setOnClickListener(this);
		img__voicenote.setOnClickListener(this);
		main_voice_note.setOnClickListener(this);
		
		
		
		main_form_layout.setOnClickListener(this);
		text_form.setOnClickListener(this);
		img_form.setOnClickListener(this);
		arrow_form.setOnClickListener(this);
		
		main_voice_note_done.setOnClickListener(this);
		main_scan_img_done.setOnClickListener(this);
		main_form_layout_done.setOnClickListener(this);
		
		imm.hideSoftInputFromWindow(text_jobtittle.getWindowToken(), 0);
		text_jobtittle.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(text_jobtittle.getText().toString().trim().length()>0)
				{
					text_done.setVisibility(View.VISIBLE);
				}
				else if(text_jobtittle.getText().toString().trim().length()==0)
				text_done.setVisibility(View.GONE);
					
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(check==true)
		{

		 InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                 INPUT_METHOD_SERVICE);
		 imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
		 return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_scanimg:
			String fileName = "new-photo-name.jpg";
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            values.put(MediaStore.Images.Media.DESCRIPTION,
                            "Image capture by camera");
            imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            values);
            // create new Intent
            Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent,PICK_Camera_IMAGE );
			
			break;
			case R.id.img_scanimg:
				String fileNamee = "new-photo-name.jpg";
	            ContentValues valuess = new ContentValues();
	            valuess.put(MediaStore.Images.Media.TITLE, fileNamee);
	            valuess.put(MediaStore.Images.Media.DESCRIPTION,
	                            "Image capture by camera");
	            imageUri = getContentResolver().insert(
	                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
	                            valuess);
	            // create new Intent
	            Intent intentt= new Intent(
	                            MediaStore.ACTION_IMAGE_CAPTURE);
	            intentt.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
	            intentt.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
	            startActivityForResult(intentt,PICK_Camera_IMAGE );
				break;
			
				
			case R.id.arrow_scanimg:
				String fileNameeee = "new-photo-name.jpg";
	            ContentValues valuessss = new ContentValues();
	            valuessss.put(MediaStore.Images.Media.TITLE, fileNameeee);
	            valuessss.put(MediaStore.Images.Media.DESCRIPTION,
	                            "Image capture by camera");
	            imageUri = getContentResolver().insert(
	                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
	                            valuessss);
	            // create new Intent
	            Intent intentttt = new Intent(
	                            MediaStore.ACTION_IMAGE_CAPTURE);
	            intentttt.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
	            intentttt.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
	            startActivityForResult(intentttt,PICK_Camera_IMAGE );
				break;
				
			case R.id.main_scan_img:
				String fileNameeee1 = "new-photo-name.jpg";
	            ContentValues valuessss1 = new ContentValues();
	            valuessss1.put(MediaStore.Images.Media.TITLE, fileNameeee1);
	            valuessss1.put(MediaStore.Images.Media.DESCRIPTION,
	                            "Image capture by camera");
	            imageUri = getContentResolver().insert(
	                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
	                            valuessss1);
	            // create new Intent
	            Intent intentttt1 = new Intent(
	                            MediaStore.ACTION_IMAGE_CAPTURE);
	            intentttt1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
	            intentttt1.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
	            startActivityForResult(intentttt1,PICK_Camera_IMAGE );
				break;
				
			case R.id.img__voicenote:
				
				Intent i=new Intent(this,Voicemode.class);
				startActivityForResult(i,pick_voice_mode);
				
				break;
			case R.id.text_voicenote:
				
				Intent ii=new Intent(this,Voicemode.class);
				startActivityForResult(ii,pick_voice_mode);
				
				break;
			case R.id.arrow_voicenote:
	
				Intent iii=new Intent(this,Voicemode.class);
				startActivityForResult(iii,pick_voice_mode);
	
				break;
			case R.id.main_voice_note:
	
				Intent iiii=new Intent(this,Voicemode.class);
				startActivityForResult(iiii,pick_voice_mode);
	
				break;
			case R.id.text_form:
				
				Intent i1=new Intent(this,Form_cimple.class);
				startActivityForResult(i1, pick_form);
	
				break;
			case R.id.img_form:
				
				Intent i2=new Intent(this,Form_cimple.class);
				startActivityForResult(i2, pick_form);
	
				break;
			case R.id.arrow_form:
				
				Intent i3=new Intent(this,Form_cimple.class);
				startActivityForResult(i3, pick_form);
	
				break;
			case R.id.main_form_layout:
				Intent i4=new Intent(this,Form_cimple.class);
				startActivityForResult(i4, pick_form);
	
				break;
				
				
			case R.id.text_acrhives:
//				
				Intent i5=new Intent(this,Archives_cimpli.class);
				startActivity(i5);
				break;
			case R.id.lay_text_acrhives:
				
				Intent i44=new Intent(this,Archives_cimpli.class);
				startActivity(i44);
	
				break;
				
//			case R.id.main_scan_img_done:
//				Intent move=new Intent(this,Dispaly_image.class);
//				startActivity(move);
//				break;
//		
				
			case R.id.img_scanimg_done:
			Intent move1=new Intent(this,Dispaly_image.class);
			startActivity(move1);
			break;
	
			
			case R.id.arrow_scanimg_done:
				Intent move2=new Intent(this,Dispaly_image.class);
				startActivity(move2);
				break;

			case R.id.text_scanimg_done:
				Intent move3=new Intent(this,Dispaly_image.class);
				startActivity(move3);
				break;
			
			
			case R.id.text_voicenote_done:
				Intent listen_audio=new Intent(this,Listen_audio.class);
				startActivity(listen_audio);
				
				
				break;
			case R.id.img__voicenote_done:
				Intent listen_audio1=new Intent(this,Listen_audio.class);
				startActivity(listen_audio1);
				
				
				break;
			case R.id.arrow_img_done:
				Intent listen_audio2=new Intent(this,Listen_audio.class);
				startActivity(listen_audio2);
				
				
				break;
			case R.id.main_voice_note_done:
				Intent listen_audio3=new Intent(this,Listen_audio.class);
				startActivity(listen_audio3);
				
				
				break;
			
			case R.id.text_done:
				if(text_jobtittle.getText().toString().trim().length()>0)
				{
					String items_rate="";
					String items_rate2="";
					String items_spinner="";
					String items_desc="";
					String items_hours="";
					
					jobtittle=text_jobtittle.getText().toString();
					int l=shf_name.getInt("rate_length", 0);
//					for(int ad=0;ad<l;ad++)
//					{
//						items_rate=items_rate+shf_name.getString("rate"+ad, "")+", ";
//						items_rate2=items_rate2+shf_name.getString("rate2"+ad, "")+", ";
//						items_spinner=items_spinner+shf_name.getString("spineer_id"+ad, "")+", ";
//						items_desc=items_desc+shf_name.getString("descrption"+ad, "")+", ";
//						items_hours=items_hours+shf_name.getString("hoursss"+ad, "")+", ";Log.d("dash_board_rate", shf_name.getString("rate", ""));
//						Log.d("dash_board_rate", shf_name.getString("rate2", ""));
//						Log.d("dash_board_rate", shf_name.getString("spineer_id", ""));
//						Log.d("dash_board_rate", shf_name.getString("descrption", ""));
//						Log.d("dash_board_rate", shf_name.getString("hoursss", ""));
//						
//					}
					Log.d("dash_board_rate", shf_name.getString("rate", ""));
					Log.d("dash_board_rate1", shf_name.getString("rate2", ""));
					Log.d("dash_board_rate2", shf_name.getString("spineer_id", ""));
					Log.d("dash_board_rate3", shf_name.getString("descrption", ""));
					Log.d("dash_board_rate4", shf_name.getString("hoursss", ""));
					
				new User_jobs(con, shf_name.getString("userpic", ""), shf_name.getString("sound_file", ""),shf_name.getString("user_id", ""), shf_name,jobtittle,shf_name.getString("job_title_form", ""),
						shf_name.getString("account_name", ""),shf_name.getString("contact_name", ""),shf_name.getString("from_phone", ""),
						shf_name.getString("source_form", ""),shf_name.getString("form_rate", ""),shf_name.getString("form_rate2", ""),shf_name.getString("rate", ""),
						shf_name.getString("rate2", ""),shf_name.getString("spineer_id", ""),shf_name.getString("descrption", ""),shf_name.getString("hoursss", "")).execute("main");
				imm.hideSoftInputFromWindow(text_jobtittle.getWindowToken(), 0);
				check=false;
				
				}
				else{
					Toast.makeText(con, "Please Fill Job Tittle", 1000).show();
				}
				break;
		}
		
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		switch (requestCode) {
		case PICK_Camera_IMAGE:
			
			
		selectedImageUri = null;
		    String filePath = null;
		    
		 if (resultCode == RESULT_OK) {
	            // use imageUri here to access the image
	            selectedImageUri = imageUri;
		 }
		if(selectedImageUri != null)
		{
            try {
            	
            	main_scan_img.setVisibility(View.GONE);
    		    main_scan_img_done.setVisibility(View.VISIBLE);
    		    text_done.setVisibility(View.VISIBLE);
                String filemanagerstring = selectedImageUri.getPath();

                String selectedImagePath = getPath(selectedImageUri);

                if (selectedImagePath != null) {
                    filePath = selectedImagePath;
                    File file=new File(filePath);
                } else if (filemanagerstring != null) {
                    filePath = filemanagerstring;
                } else {
                    Toast toast1 = Toast.makeText(dashboard.this,"Unknown path.", Toast.LENGTH_LONG);
                    toast1.setGravity(Gravity.CENTER, 0, 0);
                    toast1.show();
//                    Toast.makeText(getApplicationContext(), "Unknown path",
//                            Toast.LENGTH_LONG).show();

                }
                if (filePath != null) {

                    decodeFile(filePath);
                 
                   ByteArrayOutputStream baos=new  ByteArrayOutputStream();
                   bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
                   byte [] b=baos.toByteArray();
                   
                   
                   String temp=Base64.encodeToString(b, Base64.DEFAULT);
                   
                String root = Environment.getExternalStorageDirectory().toString();
       	        File myDir = new File(root + "/saved_images");    
       	        myDir.mkdirs();
       	        String fname = "Profilepic"+".png";
       	        file = new File (myDir, fname);
       	        if (file.exists ())
	        	{
	        	file.delete (); 
	        	}
       	        try {
       	        	ByteArrayOutputStream out = new ByteArrayOutputStream();
	               bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
	               
	               FileOutputStream out1 = new FileOutputStream(file);
	               bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out1);
	               
	               out1.flush();
	               out1.close();

       	        } catch (Exception e) {
	               e.printStackTrace();
	               
       	        }
       	        Log.d("userpic", myDir+fname);
                SharedPreferences.Editor editir=shf_name.edit();
   				
   				editir.putString("userpic", myDir+"/"+fname);
   				editir.putString("imagetype", "1");
   				
   				editir.commit();
                  
                } else {
                    bitmap = null;
                }
            } catch (Exception e) {
                Toast toast1 = Toast.makeText(dashboard.this,"Internal error.", Toast.LENGTH_LONG);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
//                Toast.makeText(getApplicationContext(), "Internal error",
//                        Toast.LENGTH_LONG).show();

            }

        }
		
		break;
		
		case pick_voice_mode:
			
			Log.d("sound value", shf_name.getString("sound_file", ""));
			
			if(resultCode==RESULT_OK)
			{
				if(shf_name.getString("sound_file", "")!=null)
				{
					main_voice_note.setVisibility(View.GONE);
					main_voice_note_done.setVisibility(View.VISIBLE);
					text_done.setVisibility(View.VISIBLE);
				}
			}
			
			break;
			
		case pick_form:
			if(resultCode==RESULT_OK)
			{
				if(shf_name.getString("job_title_form", "")!=null||shf_name.getString("account_name", "")!=null||shf_name.getString("contact_name", "")!=null||shf_name.getString("from_phone", "")!=null|| 
						shf_name.getString("source_form", "")!=null||shf_name.getString("form_rate", "")!=null||shf_name.getString("form_rate2", "")!=null)
				{
					main_form_layout.setVisibility(View.GONE);
					main_form_layout_done.setVisibility(View.VISIBLE);
					text_done.setVisibility(View.VISIBLE);
				}
			}
			
			break;
		

	default:
		break;
	}
}
	
	public String getPath(Uri uri) {
		Cursor cursor = null;
		int column_index = 0;
		try {
			String[] projection = { MediaStore.Images.Media.DATA };
			cursor = managedQuery(uri, projection, null, null, null);
			column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return cursor.getString(column_index);
	}
	public void decodeFile(String filePath) {
		// Decode image size
		try {
			ExifInterface exif = new ExifInterface(filePath);
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);

			int angle = 0;

			if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
				angle = 90;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
				angle = 180;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
				angle = 270;
			}

			final	Matrix mat = new Matrix();
			mat.postRotate(angle);

			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, o);

			// The new size we want to scale to
			

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp < w*2 && height_tmp < h*2)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			int scale1 = 1;
			Bitmap check=null;
			try{
				int www=(int) (w/1.5);
				int hhh=(int) (h/3.5);
				// Find the correct scale value. It should be the power of 2.
				int width_tmp1 = o.outWidth, height_tmp1 = o.outHeight;
				while (true) {
					if (width_tmp1 < www*2 && height_tmp1 < hhh*2)
					{
						break;
					}
					/*else if(width_tmp1>1700 || height_tmp1>2000)
					{
						width_tmp1 /= 5;
						height_tmp1 /= 5;
						scale1 *= 5;	
					}*/
					else{
					width_tmp1 /= 2;
					height_tmp1 /= 2;
					scale1 *= 2;
					}
				}
			}
			catch(Exception e)
			{
				
			}
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale1;

			final BitmapFactory.Options o3 = new BitmapFactory.Options();
			o3.inSampleSize = scale;
			try {

				bitmap = BitmapFactory.decodeFile(filePath, o2);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
						bitmap.getHeight(), mat, true);
				
				

			} catch (Exception e) {
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}

	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
if(check==false)
{
	check=true;	
}

		super.onResume();
	}
	public static void show_layout() {
		main_form_layout_done.setVisibility(View.GONE);
		main_scan_img_done.setVisibility(View.GONE);
		main_voice_note_done.setVisibility(View.GONE);
		
		text_jobtittle.setText("");
		main_form_layout.setVisibility(View.VISIBLE);
		main_scan_img.setVisibility(View.VISIBLE);
		main_voice_note.setVisibility(View.VISIBLE);
	}
}
