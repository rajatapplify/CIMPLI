package cimpli.services;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.example.cimpli.dashboard;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;



public class User_jobs extends AsyncTask<String, Integer, Void> {
	String responseStr;
	Context ctxxx;
	String user_id,pictures,imagetype,job_sound;
	 ProgressDialog bar;
	 SharedPreferences sp;
	 String job_title;
	
	 String job_title_form;
	 String account_name;
	 String contact_name; 
	 String from_phone;
	 String source_form;
	 String form_rate;
	 String form_rate2;
	 String rate;
	 String rate2; 
	 String spineer_id;
	 String descrption;
	 String hoursss;
	
	public User_jobs(Context ctx, String userpic, String sound, String id, SharedPreferences shf_name, String jobtittle, String job_title_form, String account_name, String contact_name, String from_phone, String source_form, String form_rate, String form_rate2, String rate, String rate2, String spineer_id, String descrption, String hoursss) {
		// TODO Auto-generated constructor stub
		this.ctxxx=ctx;
		
		this.pictures=userpic;
		this.job_sound=sound;
		this.user_id=id;
		this.sp=shf_name;
		this.job_title=jobtittle;
		this.job_title_form=job_title_form;
		this.account_name=account_name;
		this.contact_name=contact_name;
		this.from_phone=from_phone;
		this.source_form=source_form;
		this.form_rate=form_rate;
		this.form_rate2=form_rate2;
		this.rate=rate;
		this.rate2=rate2;
		this.spineer_id=spineer_id;
		this.descrption=descrption;
		this.hoursss=hoursss;
		
		/*this.imagetype1=imagetype;
		this.userpic1=userpic;
		this.sp=shf_name;*/
		
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
//		 bar = new ProgressDialog(ctxxx);
//         bar.setMessage("Loading...");
//         bar.setIndeterminate(true);     
//         bar.show();
		dashboard.main_project_saved.setVisibility(View.VISIBLE);
		dashboard.main_dashboard.setVisibility(View.GONE);
			}
	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		 
		 try{
			 
		Log.d("image", pictures);
		Log.d("sound", job_sound);
		Log.d("id", user_id);
		Log.d("jobtittle", job_title);
		Log.d("rate", rate);
		Log.d("rate2", rate);
		Log.d("spineer_id", spineer_id);
		Log.d("descrption", descrption);
		Log.d("hoursss", hoursss);
		
		FileBody file_body = null;
		FileBody file_body_sound=null;
		if(!pictures.equalsIgnoreCase(""))
		{
			File file=new File(pictures);
			 file_body=new FileBody(file);
		}
		
		if(!job_sound.equalsIgnoreCase(""))
		{
			File file_sound=new File(job_sound);
			 file_body_sound=new FileBody(file_sound);
		}
		
		 @SuppressWarnings("unused")
		HttpClient httpClient=new DefaultHttpClient();
		 HttpPost httpPost=new HttpPost("http://admin.cimpli.com.au/user_jobs");
		
		 MultipartEntity nameValuePairs=new MultipartEntity();
		 
		// nameValuePairs.addPart("phone",new StringBody(phone1));
//		 nameValuePairs.addPart("accesstoken", new StringBody(accesstoken));
//		 nameValuePairs.addPart("name",new StringBody(name));
		// nameValuePairs.addPart("devicetoken", new StringBody(devicetoken1));
		 nameValuePairs.addPart("job_title", new StringBody(job_title));
		 
		 
		 if(!pictures.equalsIgnoreCase(""))
				{
			 nameValuePairs.addPart("pictures",file_body);
				}
			 else
			 {
				 nameValuePairs.addPart("pictures",new StringBody(""));
			 }
		 if(!job_sound.equalsIgnoreCase(""))
			{
		 nameValuePairs.addPart("job_sound",file_body_sound);
			}
		 else
		 {
			 nameValuePairs.addPart("job_sound",new StringBody(""));
		 }
		 
		 
		 nameValuePairs.addPart("user_id", new StringBody(user_id));

		 nameValuePairs.addPart("account_name", new StringBody(account_name));
		 nameValuePairs.addPart("contact_name", new StringBody(contact_name));
		 nameValuePairs.addPart("phone", new StringBody(from_phone));
		 nameValuePairs.addPart("source", new StringBody(source_form));
		 nameValuePairs.addPart("rate_1", new StringBody(form_rate));
		 nameValuePairs.addPart("rate_2", new StringBody(form_rate2));
		 nameValuePairs.addPart("multi_rate_1", new StringBody(rate));
		 nameValuePairs.addPart("multi_rate_2", new StringBody(rate2));
		 nameValuePairs.addPart("job_label", new StringBody(spineer_id));
		 nameValuePairs.addPart("description", new StringBody(descrption));
		 nameValuePairs.addPart("job_hours", new StringBody(hoursss));
		 
//		 nameValuePairs.addPart("userpic",file_body);
		 
		
			 httpPost.setEntity((nameValuePairs));
		 try{
			 
		 
// List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(2);
//		 
//		 nameValuePairs.add(new BasicNameValuePair("accesstoken",accesstoken));
//		// nameValuePairs.addPart("name",new StringBody(user_name1));
//		 nameValuePairs.add(new BasicNameValuePair("name", name));
//		 nameValuePairs.add(new BasicNameValuePair("imagetype", imagetype));
//		 //nameValuePairs.addPart("imagetype", new StringBody(imagetype1));
//		 
//		 if(!userpic.equalsIgnoreCase(""))
//			{
//			 nameValuePairs.add(new BasicNameValuePair("userpic",file_body+""));
//			}
//		 else
//		 {
//			 nameValuePairs.add(new BasicNameValuePair("userpic",""));
//		 }
//		
//		 
//		
//			 httpPost.setEntity((new UrlEncodedFormEntity(nameValuePairs)));
		 
		
		 HttpClient client=new DefaultHttpClient();
			 HttpResponse response = client.execute(httpPost);
             HttpEntity resEntity;
             resEntity = response.getEntity();
             responseStr= EntityUtils.toString(resEntity);
             Log.d("Response", responseStr);
             SharedPreferences.Editor edit=sp.edit();
 			edit.putString("userpic", "");
 			edit.putString("sound_file", "");
 			
// 			edit.putString("user_id", "");
 			
// 			edit.putString("job_title_form", "");
 			
 			edit.putString("account_name", "");
 			
 			edit.putString("contact_name", "");
 			
 			edit.putString("from_phone", "");
 			
 			edit.putString("source_form", "");
 			
 			edit.putString("form_rate", "");
 			
 			edit.putString("form_rate2", "");
 			
 			edit.putString("rate", "");
 			
 			edit.putString("rate2", "");
 			
 			edit.putString("spineer_id", "");
 			
 			edit.putString("descrption", "");
 			
 			edit.putString("hoursss", "");
 			
 			
 			
 			edit.commit();
		 }
		 catch(ClientProtocolException e)
		 {
			 e.printStackTrace();
		 }
			 
		 }
		 catch (IOException e) {
			// TODO Auto-generated catch block
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
			
			JSONObject object1 = (JSONObject) new JSONTokener(object.getString("response")).nextValue();
//		 if(bar.isShowing()){
//             bar.dismiss();
//     }
			
			SharedPreferences.Editor edit=sp.edit();
			edit.putString("userpic", "");
			edit.putString("sound_file", "");
			
//			edit.putString("user_id", "");
			
//			edit.putString("job_title_form", "");
			
			edit.putString("account_name", "");
			
			edit.putString("contact_name", "");
			
			edit.putString("from_phone", "");
			
			edit.putString("source_form", "");
			
			edit.putString("form_rate", "");
			
			edit.putString("form_rate2", "");
			
			edit.putString("rate", "");
			
			edit.putString("rate2", "");
			
			edit.putString("spineer_id", "");
			
			edit.putString("descrption", "");
			
			edit.putString("hoursss", "");
			
			
			
			edit.commit();
			dashboard.show_layout();
			dashboard.main_project_saved.setVisibility(View.GONE);
			dashboard.main_dashboard.setVisibility(View.VISIBLE);
		}
		 catch(Exception e)
		 {
			 
		 }

	} 
}
