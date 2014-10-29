package Models_Adapters;


import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cimpli.Archives_cimpli;
import com.example.cimpli.Open_archives;
import com.example.cimpli.R;


public class Archives_list_adapter extends BaseAdapter implements OnClickListener{
	Context ctx;
	ArrayList<Archives_list_model> archive_list_adapter;
	LayoutInflater contact_inflate;
	public static List<String> job_title=new ArrayList<String>();
	public static List<String> created=new ArrayList<String>();
	int ii;
	public static ArrayList<String> check_ids=new ArrayList<String>();
	
	int tick=0;
	int tick1=0;
	public static Bitmap p ;
	
	ProgressBar bar;
	 int height;
	 int width;
	 int rotation;
	 boolean showingFirst=true;
//	 ImageloaderSecond imageload;
	
	 Typeface tf,tf_light;
	
	 SharedPreferences shf_name;


	public Archives_list_adapter(Context ctxxx,
			ArrayList<Archives_list_model> get_archives_list, int height,
			int width, Typeface tf_light, Typeface tf, SharedPreferences shf_name) {
		this.archive_list_adapter=get_archives_list;
		this.height=height;
		this.width=width;
		this.tf=tf;
		this.tf_light=tf_light;
		this.contact_inflate=LayoutInflater.from(ctxxx);
		this.ctx=ctxxx;
		this.shf_name=shf_name;
		
		
		// TODO Auto-generated constructor stub
	}



	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.d("getcount", archive_list_adapter.size()+"");
		return archive_list_adapter.size();

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		Log.d("getitem", archive_list_adapter.get(position)+"");
		return archive_list_adapter.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Log.d("getitemid", position+"");
		return position;
	}


	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(convertView==null)
		{
			convertView=contact_inflate.inflate(R.layout.list_archives, null);
			holder=new ViewHolder();
			LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			layoutParams11.gravity=Gravity.CENTER_VERTICAL|Gravity.LEFT;
			
			layoutParams11.setMargins(width/32, 0, 0, 0);
			holder.job_tittle_list=(TextView)convertView.findViewById(R.id.job_tittle_list);
			holder.job_tittle_list.setLayoutParams(layoutParams11);
			holder.job_tittle_list.setGravity(Gravity.LEFT);
			holder.job_tittle_list.setTypeface(tf);
			holder.time_list=(TextView)convertView.findViewById(R.id.time_list);
			holder.time_list.setTypeface(tf);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, height/8);
			layoutParams.setMargins(0, width/32, 0, 0);
			holder.main_list_archives=(LinearLayout)convertView.findViewById(R.id.main_list_archives);
			
			
			holder.main_list_archives.setLayoutParams(layoutParams);
			LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,.6f);
			layoutParams1.gravity=Gravity.CENTER_VERTICAL;
			LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,1f);
			LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,1.2f);
        	
        	holder.archive_check=(CheckBox)convertView.findViewById(R.id.archive_check);
			holder.lay_tittle_list=(LinearLayout)convertView.findViewById(R.id.lay_tittle_list);
			holder.lay_arrow_next=(LinearLayout)convertView.findViewById(R.id.lay_arrow_next);
			holder.lay_time_list=(LinearLayout)convertView.findViewById(R.id.lay_time_list);
			holder.move_archives=(LinearLayout)convertView.findViewById(R.id.move_archives);
			holder.arrow_next=(ImageView)convertView.findViewById(R.id.arrow_next);
			
			
			holder.lay_arrow_next.setLayoutParams(layoutParams3);
			holder.lay_tittle_list.setLayoutParams(layoutParams1);
			holder.lay_time_list.setLayoutParams(layoutParams2);
			
			
//		
			
			
			
			
		
			convertView.setTag(holder);
		}
		else
			holder=(ViewHolder)convertView.getTag();
		
		if(	Archives_cimpli.check==1)
		{
			holder.arrow_next.setVisibility(View.GONE);
			holder.archive_check.setVisibility(View.VISIBLE);
			
		}
		
			holder.job_tittle_list.setText(archive_list_adapter.get(position).getJob_title());
			
			
		holder.time_list.setText(archive_list_adapter.get(position).getCreated());
		if(Archives_cimpli.check==0)
		{
		holder.move_archives.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor edit=shf_name.edit();
				edit.putString("job_tittle_archives", archive_list_adapter.get(position).getJob_title());
				edit.putString("imag_path_archive", archive_list_adapter.get(position).getImage_path());
				edit.putString("sound_path_archive",archive_list_adapter.get(position).getSound_path());
				edit.putString("from_path_archive",archive_list_adapter.get(position).getForms_path());
				edit.putString("job_iddddd",archive_list_adapter.get(position).getJob_id());
				Log.d("sound_path", archive_list_adapter.get(position).getSound_path());
				edit.commit();
				Intent i=new Intent(ctx,Open_archives.class);
				ctx.startActivity(i);
			}
		});
		}
		
		holder.move_archives.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Archives_cimpli.check=1;
				Archives_cimpli.getimnage();
				check_ids=new ArrayList<String>();
				return false;
			}
		});
		if(	Archives_cimpli.check==1)
		{
			holder.archive_check.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					check_ids.add(archive_list_adapter.get(position).getJob_id());
					
					
				}
			});
		}
	

	
		return convertView;
		

	}
	public static class ViewHolder
	{
		public TextView job_tittle_list,time_list;
		public LinearLayout main_list_archives,lay_tittle_list,lay_time_list,lay_arrow_next,move_archives;
		public ImageView arrow_next;
		public CheckBox archive_check;
		
	
//		public ProgressBar pro1,pro2;
//		public CheckBox first_check,Second_check;
//		public RelativeLayout first_image_lay,Second_image_lay;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.move_archives:
//			
//			break;

		default:
			break;
		}
		
	}
	

}



