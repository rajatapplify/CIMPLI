package cimpli.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cimpli.dashboard;

public class Registration extends AsyncTask<String, String, String> {
	
	
	
	
//	Registeruser_adapter rgs_adapter;

	String responseStr;
	Context ctxxx;
	String first_name,last_name;
	 ProgressDialog bar;
	
	
	SharedPreferences shf_name;
	String group_name;
	
	
	

	public Registration(Context ctxx, SharedPreferences shf_name, String first_name, String last_name, String group_name) {
		
		// TODO Auto-generated constructor stub
		this.ctxxx=ctxx;
		
		this.shf_name=shf_name;
		this.first_name=first_name;
		this.last_name=last_name;
		this.group_name=group_name;
		
		
		
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
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		 
		
		try{
		
		 HttpPost httpPost=new HttpPost("http://admin.cimpli.com.au/registration");
		
		List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(2);
		 
		 nameValuePairs.add(new BasicNameValuePair("last_name",last_name));
		 nameValuePairs.add(new BasicNameValuePair("group_name",group_name));
		 nameValuePairs.add(new BasicNameValuePair("first_name",first_name));
		 
		 
		
		 
		
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
				
			}
		
		 
		
		
		return null;
		 
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		
		try{
			JSONObject object = (JSONObject) new JSONTokener(responseStr).nextValue();
			
			JSONObject object1 = (JSONObject) new JSONTokener(object.getString("response")).nextValue();
			try{
					
					SharedPreferences.Editor editer4 = shf_name.edit();
		            editer4.putString("user_id", object1.getString("user_id"));
		           
		            editer4.putString("access_token", object1.getString("access_token"));
		           
		            
		            editer4.commit();
		            
		            Intent mainIntent1 = new Intent(ctxxx,dashboard.class);
		            ctxxx.startActivity(mainIntent1); 
		            ((Activity) ctxxx).overridePendingTransition(0, 0);
		            ((Activity) ctxxx).finish();

				}
				catch(Exception e)
				{
					
				}
				
				
				
				
				
							
			
			}

		
		catch(Exception e)
		{
			Log.d("Exception", e+"");
		}
		 if(bar.isShowing()){
             bar.dismiss();
     }
		 
	} }
