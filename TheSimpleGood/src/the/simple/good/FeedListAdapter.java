package the.simple.good;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FeedListAdapter extends BaseAdapter{

	Activity activity;
	ImageLoader imageLoader;
	DisplayImageOptions options;
	LayoutInflater inflater;
	String [] imagePath;
	
	public FeedListAdapter(Activity a,String [] imgPath,ImageLoader img,DisplayImageOptions opt){
		imageLoader=img;
    	options=opt;
        activity = a;
        this.imagePath=imgPath;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View vi=arg1;

//	        if(arg1==null)
	            vi = inflater.inflate(R.layout.feed_list_item, null);
	        
	        ImageView imgPath=(ImageView)vi.findViewById(R.id.imgFeed);
//	        String str="http://services.annucaterers.com/uploads/sample1.jpg";
	        imgPath.setBackgroundResource(R.drawable.img);
//	        imageLoader.displayImage(imagePath[arg0],imgPath,options);
	        
		return vi;
	}

}
