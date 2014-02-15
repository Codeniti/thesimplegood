package the.simple.good;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import the.simple.good.utility.DataFromUrl;
import the.simple.good.utility.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Feed extends Activity {

	ListView lstFeed;
	FeedListAdapter adap;
	Activity a=this;
	String [] imgPath;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;
	the.simple.good.utility.ActionBar ab;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed);
		lstFeed=(ListView)findViewById(R.id.lstFeed);
		ab=(the.simple.good.utility.ActionBar)findViewById(R.id.actionbar);
		
		ab.setActionTitle("Feeds");
		
		try {
			DataFromUrl.getWebData(Utils.url+"feed.php",feedHandler, a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_launcher).cacheInMemory().cacheOnDisc()
		.build();
		
	}
	
	public Handler feedHandler = new Handler(){
		
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case 1:
				Toast.makeText(getBaseContext(), "Connection error", Toast.LENGTH_SHORT).show();
				break;
				
			case 3:
				Toast.makeText(getBaseContext(), msg.toString(), Toast.LENGTH_SHORT).show();
				break;
				
			case 2:
				
				try {
					
					JSONArray parent=new JSONArray(msg.getData().getString("text"));
					
					imgPath=new String[parent.length()];
					
					for (int i = 0; i < parent.length(); i++) {
						JSONObject main=parent.getJSONObject(1);
						String iPath=main.getString("ipath");
						imgPath[i]=iPath;
//						Utils.showShortToast(a, iPath);
					}
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				adap=new FeedListAdapter(a, imgPath, imageLoader, options);
				lstFeed.setAdapter(adap);
			}
		}
	};
	 
}
