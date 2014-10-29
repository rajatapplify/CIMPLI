package cimpli.services;

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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.example.cimpli.Signup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;



public class Getgroups extends AsyncTask<String, Integer, Void> {
	
	
	
	
//	Registeruser_adapter rgs_adapter;
	public static ArrayList<String> grour_name=new ArrayList<String>();
	public static ArrayList<String> grour_id=new ArrayList<String>();
	String responseStr;
	Context ctxxx;
	String accesstoken,contacts,countrycode;
	 ProgressDialog bar;
	 SharedPreferences sp;
	LinearLayout hide_lay;
	String Chat_id;
	String listidd,nameidd;
	SharedPreferences shf_name;
	int height1;
	int width1;
	Typeface tf;
	InputMethodManager imm;
//	public Broadcast_message_to_registered_user(Context ctx, String accesstoken, String userid, String username,String message, SharedPreferences shf_name) {
//		// TODO Auto-generated constructor stub
//		this.ctxxx=ctx;
//		this.accesstoken=accesstoken;
//		this.contacts=contacts;
//		this.countrycode=countrycode;
//	this.sp=shf_name;
//	this.user_list=user_list;
	
		
	//}
	public Getgroups(Context ctxx, SharedPreferences shf_name) {
		
		// TODO Auto-generated constructor stub
		this.ctxxx=ctxx;
		
		this.shf_name=shf_name;
		
		
		
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
//			Log.d("accesstoken",accesstoken );
		
		try{
		 HttpClient httpClient=new DefaultHttpClient();
		 HttpPost httpPost=new HttpPost("http://admin.cimpli.com.au/getGroups");
		 try{
//		List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(2);
//		 
//		 nameValuePairs.add(new BasicNameValuePair("accesstoken",accesstoken));
//		 
//		
//		 
//		
//			 httpPost.setEntity((new UrlEncodedFormEntity(nameValuePairs)));
//			 
		 
		
		
		 HttpClient client=new DefaultHttpClient();
			HttpResponse response = client.execute(httpPost);
             HttpEntity resEntity;
             resEntity = response.getEntity();
             responseStr= EntityUtils.toString(resEntity);
			 Log.d("Response", responseStr);
		  
		 }
			catch(ClientProtocolException e)
			{
				
			}
		}
	
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
		
		return null;
		 
	}
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		try{
			JSONObject object1= (JSONObject) new JSONTokener(responseStr).nextValue();
			if(object1.has("error"))
			{
//				if(object1.getString("error").equalsIgnoreCase("Invalid access token.")){
//					Intent mainIntent1 = new Intent(ctxxx,Startpage.class);
//					 mainIntent1.setAction(Intent.ACTION_MAIN);
//					 mainIntent1.addCategory(Intent.CATEGORY_HOME);
//					 mainIntent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					// mainIntent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//					 mainIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//					 
//					 sp.edit().clear().commit();
//					 ctxxx.startActivity(mainIntent1);
//			         ((Activity) ctxxx).overridePendingTransition(0, 0);
//			         ((Activity) ctxxx).finish();
//					
//					
//				}
				
			}
			else
			{
				JSONArray object2= (JSONArray) new JSONTokener(object1.getString("response")).nextValue();
				
				
				grour_name.add(0, "Select Group");
				grour_id.add(0, "Select Group");
				for(int i=0;i<object2.length();i++)
				{
					JSONObject object = (JSONObject) new JSONTokener(object2.get(i)+"").nextValue();
					grour_name.add(object.getString("group_name"));
					grour_id.add(object.getString("id"));
					Log.d("name", object.getString("group_name"));
					Log.d("id", object.getString("id"));
					ArrayAdapter<String> adapter=new ArrayAdapter<String>(ctxxx,com.example.cimpli.R.layout.spinner_item, grour_name);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					
					Signup.spinner_groupname.setAdapter(adapter);
					
				}
				
				
//			 Intent mainIntent1 = new Intent(ctxxx,Startpage.class);
//			 mainIntent1.setAction(Intent.ACTION_MAIN);
//			 mainIntent1.addCategory(Intent.CATEGORY_HOME);
//			 mainIntent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			// mainIntent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//			 mainIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//			 
//			 shf_name.edit().clear().commit();
//			 ctxxx.startActivity(mainIntent1);
//	         ((Activity) ctxxx).overridePendingTransition(0, 0);
//	         ((Activity) ctxxx).finish();
//			
			
			}

		}
		catch(Exception e)
		{
			Log.d("Exception", e+"");
		}
		 if(bar.isShowing()){
             bar.dismiss();
     }
		
	} 
}
