package cimpli.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Models_Adapters.Archives_list_adapter;
import Models_Adapters.Archives_list_model;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.example.cimpli.Archives_cimpli;
import com.example.cimpli.dashboard;


public class GetUserJobs extends AsyncTask<String, Integer, Void> {
	String responseStr;
	String access_token;
	Context ctxxx;
	int height;
	int width;
	InputMethodManager imm;
	SharedPreferences shf_name;
	ProgressDialog bar;
	 Typeface tf; 
	 Typeface tf_light;
	 Archives_list_adapter list_adapter;
	
	public GetUserJobs(Context ctx, int height, int width, InputMethodManager imm, SharedPreferences shf_name, String access_token, Typeface tf, Typeface tf_light) {
		// TODO Auto-generated constructor stub
		this.ctxxx=ctx;
		
		this.height=height;
		this.width=width;
		this.imm=imm;
		this.shf_name=shf_name;
		this.access_token=access_token;
		this.tf=tf;
		this.tf_light=tf_light;
				
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		 bar = new ProgressDialog(ctxxx);
         bar.setMessage("Loading...");
         bar.setIndeterminate(true);     
         bar.show();
	
			}
	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		 
		 try{
			 
		Log.d("height", height+"");
		Log.d("width", width+"");
		Log.d("access_token", access_token);
		
		try{
			
			 HttpPost httpPost=new HttpPost("http://admin.cimpli.com.au/getUserJobs");
			
			List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(2);
			 
			 nameValuePairs.add(new BasicNameValuePair("access_token",access_token));
		
			 
		     httpPost.setEntity((new UrlEncodedFormEntity(nameValuePairs)));
				 
			 
			
			
			 HttpClient client=new DefaultHttpClient();
				HttpResponse response = client.execute(httpPost);
	             HttpEntity resEntity;
	             resEntity = response.getEntity();
	             responseStr= EntityUtils.toString(resEntity);
				 Log.d("Response", responseStr);
			  
			}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
			 
			
			
			return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		
//		
		try{
			JSONObject object = (JSONObject) new JSONTokener(responseStr).nextValue();
			
			JSONArray object1 = (JSONArray) new JSONTokener(object.getString("response")).nextValue();
			
			Archives_cimpli.Get_archives_list=new ArrayList<Archives_list_model>();
			for(int i=0;i<object1.length();i++)
			{
//				JSONObject object3 = (JSONObject) new JSONTokener(object1.get(i)+"").nextValue();
			JSONObject object3=(JSONObject)new JSONTokener( object1.get(i)+"").nextValue();
			Archives_list_model archives_listtt=new Archives_list_model();
//			archives_listtt.setUser_id(shf_name.getString("user_id", ""));
			archives_listtt.setJob_id(object3.getString("job_id"));
			
			archives_listtt.setJob_title(object3.getString("job_title"));
			archives_listtt.setCreated(object3.getString("created"));
			archives_listtt.setImage(object3.getString("image"));
			archives_listtt.setImage_path(object3.getString("image_path"));
			archives_listtt.setSound_path(object3.getString("sound_path"));
			archives_listtt.setJob_sound(object3.getString("job_sound"));
			archives_listtt.setForms(object3.getString("forms"));
			archives_listtt.setForms_path(object3.getString("forms_path"));
			
			
			Archives_cimpli.Get_archives_list.add(archives_listtt);
//			Log.d("name", object3.getString("user_id")+"//"+Archives_cimpli.Get_archives_list.get(i).getJob_title());
			
			list_adapter=new Archives_list_adapter(ctxxx, Archives_cimpli.Get_archives_list, height, width, tf_light, tf,shf_name);
			
			
			
			
			
			
			
			
			
			}
			Archives_cimpli.archives_job_list.setAdapter(list_adapter);
			
			
		
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 if(bar.isShowing()){
             bar.dismiss();
     }	
	}  
}
