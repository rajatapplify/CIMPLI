package image_loader_cimpli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageLoader {
    
    MemoryCache memoryCache=new MemoryCache();
    FileCache fileCache;
    private Map<ImageView, String> imageViews=Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService; 
//    ImageHelper yeshelp = new ImageHelper();
    int height;
    int width;
    public ImageLoader(Context context){
        fileCache=new FileCache(context);
        executorService=Executors.newFixedThreadPool(5);
    }
    
  //  final int stub_id = R.drawable.placeholder;
    @SuppressWarnings({ "deprecation" })
	public void DisplayImage(String url, ImageView imageView,ProgressBar progressBar, int height, int width)
    {
    	this.height=height;
    	this.width=width;
    	Log.d("DisplayImage", url);
        imageViews.put(imageView, url);
        Bitmap bitmap=memoryCache.get(url);  
        if(bitmap!=null)
        {
//        	bitmap=yeshelp.getRoundedShape(bitmap,height,width);
//        	bitmap.setDensity(Bitmap.DENSITY_NONE);
//        	bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        	
        	Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.WHITE);
            paint.setShadowLayer(4.0f, 0.0f, 2.0f, Color.WHITE);
            
            // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                    bitmap.getWidth() / 3, paint);
           // paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, null);
                Drawable d = new BitmapDrawable(output);
        	
        	
        	
//        	Drawable d = new BitmapDrawable(bitmap);  
        	
        	imageView.setBackgroundDrawable(d);
        	
           // imageView.setImageBitmap(bitmap);
            progressBar.setVisibility(View.GONE);
            try {
            	/* if(bitmap.isRecycled())
             	{
             		
             	}
             	else{
             		bitmap.recycle();
             	}
            	 bitmap=null;*/
            	 d=null;
			} catch (Exception e) {
				// TODO: handle exception
			}
           
        }
        else
        {
            queuePhoto(url, imageView);
           // imageView.setImageResource(stub_id);
            try{
            progressBar.setVisibility(View.GONE);
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            }
        }
    }
        
    private void queuePhoto(String url, ImageView imageView)
    {
        PhotoToLoad p=new PhotoToLoad(url, imageView);
        executorService.submit(new PhotosLoader(p));
    }
    
    private Bitmap getBitmap(String url) 
    {
        File f=fileCache.getFile(url);
        
        //from SD cache
        Bitmap b = decodeFile(f);
        if(b!=null)
            return b;
        
        //from web
        try {
            Bitmap bitmap=null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is=conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Utils.CopyStream(is, os);
            os.close();
            bitmap = decodeFile(f);
            return bitmap;
        } catch (Exception ex){
           ex.printStackTrace();
           return null;
        }
    }

    //decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f){
        try {
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);
            
            //Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE=100;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }
            
            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }
    
    //Task for the queue
    private class PhotoToLoad
    {
        public String url;
        public ImageView imageView;
        public PhotoToLoad(String u, ImageView i){
            url=u; 
            imageView=i;
        }
    }
    
    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;
        PhotosLoader(PhotoToLoad photoToLoad){
            this.photoToLoad=photoToLoad;
        }
        
        @Override
        public void run() {
            if(imageViewReused(photoToLoad))
                return;
            Bitmap bmp=getBitmap(photoToLoad.url);
            memoryCache.put(photoToLoad.url, bmp);
            if(imageViewReused(photoToLoad))
                return;
            BitmapDisplayer bd=new BitmapDisplayer(bmp, photoToLoad);
            Activity a=(Activity)photoToLoad.imageView.getContext();
            a.runOnUiThread(bd);
        }
    }
    
    boolean imageViewReused(PhotoToLoad photoToLoad){
        String tag=imageViews.get(photoToLoad.imageView);
        if(tag==null || !tag.equals(photoToLoad.url))
            return true;
        return false;
    }
    
    //Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable
    {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        public BitmapDisplayer(Bitmap b, PhotoToLoad p){bitmap=b;photoToLoad=p;}
        @SuppressWarnings("deprecation")
		public void run()
        {
            if(imageViewReused(photoToLoad))
                return;
            if(bitmap!=null)
            {
//            	bitmap=yeshelp.getRoundedShape(bitmap,height,width);
            	Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                        bitmap.getHeight(), Config.ARGB_8888);
                Canvas canvas = new Canvas(output);

                final int color = 0xff424242;
                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(Color.WHITE);
                
                paint.setShadowLayer(4.0f, 0.0f, 2.0f, Color.WHITE);
                // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
                canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                        bitmap.getWidth() / 3, paint);
               // paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
                canvas.drawBitmap(bitmap, rect, rect, null);
                    @SuppressWarnings("deprecation")
					Drawable d = new BitmapDrawable(output);
            	
            	
//            	Drawable d = new BitmapDrawable(bitmap);  
            	
            	photoToLoad.imageView.setBackgroundDrawable(d);
            	
//            	photoToLoad.imageView.setPadding(width/64, width/64, width/64, width/64);
//                photoToLoad.imageView.setImageBitmap(bitmap);
                try {
                	/* if(bitmap.isRecycled())
                 	{
                 		
                 	}
                 	else{
                 		bitmap.recycle();
                 	}
                	 bitmap=null;*/
				} catch (Exception e) {
					// TODO: handle exception
				}
               
            }
            
                
                /*   else
                photoToLoad.imageView.setImageResource(stub_id);*/
        }
    }

    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }

}