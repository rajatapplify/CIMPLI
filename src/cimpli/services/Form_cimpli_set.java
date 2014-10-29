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

import Models_Adapters.Archives_list_adapter;
import android.app.ProgressDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cimpli.Archives_cimpli;
import com.example.cimpli.R;

public class Form_cimpli_set extends AsyncTask<String, Integer, Void> {
	
	
	
	
//	Registeruser_adapter rgs_adapter;
	public static String [] seprated;
	public static String [] seprated1;
	public static String [] seprated2;
	public static String [] seprated3;
	public static String [] seprated4;
	
	String responseStr;
	Context ctxxx;
	String accesstoken;
	 ProgressDialog bar;
	 SharedPreferences sp;
	LinearLayout hide_lay;
	String Chat_id;
	String listidd,nameidd;
	SharedPreferences shf_name;
	int height1;
	int width1;
	Typeface tf,tf_light;
	InputMethodManager imm;
	ArrayList<String> job_id;
	TextView account_name;
	TextView contact_name;
	TextView form_phone;
	TextView form_source;
	TextView form_rate;
	TextView from_ndrate;
	LinearLayout addition_layout;
	int w;
	int h;
	public Form_cimpli_set(Context ctxx, SharedPreferences shf_name, Typeface tf, Typeface tf_light, TextView account_name, TextView contact_name, TextView form_phone, TextView form_source, TextView form_rate, TextView from_ndrate, LinearLayout addition_layout, int w, int h) {
		
		// TODO Auto-generated constructor stub
		this.ctxxx=ctxx;
		
		this.shf_name=shf_name;
		this.tf=tf;
		this.tf_light=tf_light;
		this.account_name=account_name;
		this.contact_name=contact_name;
		this.form_phone=form_phone;
		this.form_source=form_source;
		this.form_rate=form_rate;
		this.from_ndrate=from_ndrate;
		this.addition_layout=addition_layout;
		this.h=h;
		this.w=w;
		
	
		
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
			Log.d("acess_token", shf_name.getString("access_token", "")+"//"+shf_name.getString("job_iddddd", ""));
		 HttpClient httpClient=new DefaultHttpClient();
		 HttpPost httpPost=new HttpPost("http://admin.cimpli.com.au/getUserJobsForms");
		 try{
		List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(2);
		 
		 nameValuePairs.add(new BasicNameValuePair("access_token",shf_name.getString("access_token", "")));
		 nameValuePairs.add(new BasicNameValuePair("job_id",shf_name.getString("job_iddddd", "")));
//		 
//		
//		 
//		
			 httpPost.setEntity((new UrlEncodedFormEntity(nameValuePairs)));
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
		
		
		try{
			JSONObject object = (JSONObject) new JSONTokener(responseStr).nextValue();
			
			JSONArray object1 = (JSONArray) new JSONTokener(object.getString("response")).nextValue();
			
			for(int i=0;i<object1.length();i++)
			{
				JSONObject object3=(JSONObject)new JSONTokener( object1.get(i)+"").nextValue();
				
				account_name.setText(object3.get("account_name").toString());
				contact_name.setText(object3.get("contact_name").toString());
				form_phone.setText(object3.get("phone").toString());
				form_source.setText(object3.get("source").toString());
				form_rate.setText(object3.get("rate_1").toString());
				from_ndrate.setText(object3.get("rate_2").toString());
				SharedPreferences.Editor editer4 = shf_name.edit();
				editer4.putString("multi_rate_1",object3.getString("multi_rate_1"));
				editer4.putString("multi_rate_2",object3.getString("multi_rate_2"));
				editer4.putString("job_label",object3.getString("job_label"));
				editer4.putString("description",object3.getString("description"));
				editer4.putString("job_hours",object3.getString("job_hours"));
				editer4.commit();
				Log.d("checking",shf_name.getString("multi_rate_1", ""));
				Log.d("checking",shf_name.getString("multi_rate_2", ""));
				
				seprated=shf_name.getString("multi_rate_1", "").split(",");
				seprated1=shf_name.getString("multi_rate_2", "").split(",");
				seprated2=shf_name.getString("job_label", "").split(",");
				seprated3=shf_name.getString("description", "").split(",");
				seprated4=shf_name.getString("job_hours", "").split(",");
				
				Log.d("length", Form_cimpli_set.seprated.length+"");
				for(int ii=0;ii<Form_cimpli_set.seprated.length;ii++)
				{
					
				Log.d("again", "first5");
				
				LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
				
				LinearLayout.LayoutParams layoutParams13 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				layoutParams13.setMargins(0, w/16, 0, 0);
				
				LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
				
				layoutParams11.setMargins(0, w/32, 0, 0);
				
				LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1f);
				
				layoutParams12.gravity=Gravity.CENTER|Gravity.CENTER_VERTICAL;
//				layoutParams11.gravity=Gravity.CENTER|Gravity.CENTER_VERTICAL;
				
				LinearLayout first_lay=new LinearLayout(ctxxx);
				LinearLayout inside_first_lay=new LinearLayout(ctxxx);
				inside_first_lay.setBackgroundResource(R.drawable.header);
				first_lay.setBackgroundColor(Color.parseColor("#383838"));
				first_lay.setLayoutParams(layoutParams11);
				
				LinearLayout rate= new LinearLayout(ctxxx);
				rate.setLayoutParams(layoutParams1);
				CheckBox rate_chk=new CheckBox(ctxxx);
				
				rate_chk.setText("Rate");
				rate_chk.setEnabled(false);
				rate.addView(rate_chk);
				
				if(seprated[ii].equals("1"))
				{
					rate_chk.setChecked(true);
				}
				else if(seprated[ii].equals("0"))
				{
					rate_chk.setChecked(false);
				}
//				rate1_chck=new TextView(this);
//				rate1_chck.setId(idsss1);
//				
//				rate1_chck.setText("0");
//				
				layoutParams1.gravity=Gravity.CENTER_VERTICAL|Gravity.LEFT;
				
				LinearLayout rate_2=new LinearLayout(ctxxx);
				rate_2.setPadding(w/32, 0, w/32, 0);
				CheckBox rate2_chk=new CheckBox(ctxxx);
				rate2_chk.setText("2nd Rate");
				rate2_chk.setEnabled(false);
				rate_2.addView(rate2_chk);
				
				if(seprated1[ii].equals("1"))
				{
					rate2_chk.setChecked(true);
					
				}
				else if(seprated1[ii].equals("0"))
				{
					rate2_chk.setChecked(false);
					
				}
				
				rate_2.setLayoutParams(layoutParams1);
				
				LinearLayout rate_3=new LinearLayout(ctxxx);
				rate_3.setLayoutParams(layoutParams1);
				rate_3.setLayoutParams(layoutParams1);
				TextView spinner_value=new TextView(ctxxx);
				
				spinner_value.setText(seprated2[ii]);
				spinner_value.setTextColor(Color.WHITE);
				spinner_value.setTextSize(18);
				
				spinner_value.setBackgroundColor(Color.parseColor("#383838"));
				
				rate_3.addView(spinner_value);
				
				inside_first_lay.setLayoutParams(layoutParams12);
				inside_first_lay.addView(rate);
				inside_first_lay.addView(rate_2);
				inside_first_lay.addView(rate_3);
				first_lay.addView(inside_first_lay);
				
				LinearLayout Second_lay=new LinearLayout(ctxxx);
//			Second_lay.setOrientation(LinearLayout.VERTICAL);
				
				LinearLayout insider_Second_lay=new LinearLayout(ctxxx);
				insider_Second_lay.setOrientation(LinearLayout.VERTICAL);
				insider_Second_lay.setLayoutParams(layoutParams12);
				insider_Second_lay.setBackgroundResource(R.drawable.header);
				Second_lay.setBackgroundColor(Color.parseColor("#383838"));
				Second_lay.setLayoutParams(layoutParams11);
				
				
				TextView desc_hint=new TextView(ctxxx);
//				desc_hint.setLayoutParams(layoutParams13);
				
				desc_hint.setText("Description");
				desc_hint.setTextColor(Color.parseColor("#dc5100"));
				desc_hint.setTextSize(18);
				
				TextView desc_value=new TextView(ctxxx);
//				desc_value.setLayoutParams(layoutParams13);
				if(seprated3[ii].equals("no info filled"))
				{
					
					desc_value.setText("");
					
				}
				else
				{
					desc_value.setText(seprated3[ii]);
				}
//				desc_value.setText("Material");
				desc_value.setTextColor(Color.WHITE);
				desc_value.setTextSize(22);
				
				insider_Second_lay.addView(desc_hint);
				insider_Second_lay.addView(desc_value);
				Second_lay.addView(insider_Second_lay);
				
				LinearLayout Third_lay=new LinearLayout(ctxxx);
				LinearLayout inside_Third_lay=new LinearLayout(ctxxx);
				inside_Third_lay.setOrientation(LinearLayout.VERTICAL);
				inside_Third_lay.setBackgroundResource(R.drawable.header);
				inside_Third_lay.setLayoutParams(layoutParams12);
				Third_lay.setBackgroundColor(Color.parseColor("#383838"));
				
				Third_lay.setLayoutParams(layoutParams11);
//				Third_lay.setOrientation(LinearLayout.VERTICAL);
				TextView hours_hint=new TextView(ctxxx);
//				hours_hint.setLayoutParams(layoutParams13);
				hours_hint.setText("Hours");
				hours_hint.setTextColor(Color.parseColor("#dc5100"));
				hours_hint.setTextSize(18);
				
				TextView hour_value=new TextView(ctxxx);
				
//				hour_value.setLayoutParams(layoutParams13);
				Log.d("decs checking",seprated4[ii]+"");
				if(seprated4[ii].equals("no info filled"))
				{
					
					hour_value.setText("");
					
				}
				else
				{
					hour_value.setText(seprated4[ii]);
				}
//				hour_value.setText("Material");
				hour_value.setTextColor(Color.WHITE);
				hour_value.setTextSize(22);
				
				
				View v=new View(ctxxx);
				v.setBackgroundColor(Color.parseColor("#1e1e1e"));
				v.setLayoutParams(layoutParams13);
				
				inside_Third_lay.addView(hours_hint);
				inside_Third_lay.addView(hour_value);
				Third_lay.addView(inside_Third_lay);
				
				
				addition_layout.addView(v);
				addition_layout.addView(first_lay);
//				addition_layout.addView(v);
				addition_layout.addView(Second_lay);
//				addition_layout.addView(v);
				addition_layout.addView(Third_lay);
//				addition_layout.addView(v);
				}
	
			}
//		try{
//			
//				
//			}
//			
//				
////			 Intent mainIntent1 = new Intent(ctxxx,Startpage.class);
////			
////			 shf_name.edit().clear().commit();
////			 ctxxx.startActivity(mainIntent1);
////	         ((Activity) ctxxx).overridePendingTransition(0, 0);
////	         ((Activity) ctxxx).finish();
////			
			
			
		}
//		}
		catch(Exception e)
		{
			Log.d("Exception", e+"");
		}
		 if(bar.isShowing()){
             bar.dismiss();
     }
		
	}  
}
