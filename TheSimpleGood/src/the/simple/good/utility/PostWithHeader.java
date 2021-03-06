package the.simple.good.utility;



import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import the.simple.good.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class PostWithHeader {

	static TimerTask splashTask;
	static ProgressDialog progressDialog;

	 public static void getWebData(final String url,final List<NameValuePair> header,final List<NameValuePair> post, final Handler callback,final Activity act) throws IOException
	    {
		 if (!Utils.isNetAvailable(act)) {
			 
			 

//			 Log.d("Url", url);
//			 Log.d("Header", header.toString());
//			 Log.d("Post", post.toString());
//			 
//			 Utils.writeFileToInternalStorage(act,url);
//			 Utils.writeFileToInternalStorage(act, header.toString());
//			 Utils.writeFileToInternalStorage(act,post.toString());
			 
		 
		 progressDialog = ProgressDialog.show(act, "Please Wait", "Loading...");
		 final Message msg = Message.obtain();

		
		// Thread t = new Thread(new Runnable(){
		 splashTask = new TimerTask() {
	    	
		     public void run() {
	                try{
							
	    		        msg.what=2;
	                	HttpPost httpPost = new HttpPost(url);
	              
//	                	HttpParams httpParameters = new BasicHttpParams();
//	                	int timeoutConnection = 120000;
//	                	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
//	                	int timeoutSocket = 120000;
//	                	HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
	                	
	                	
	               for (int i = 0; i < header.size(); i++) {
	            	  httpPost.addHeader(header.get(i).getName(),header.get(i).getValue());
		        }

	               DefaultHttpClient httpClient = new DefaultHttpClient();//httpParameters);

	               httpPost.setEntity(new UrlEncodedFormEntity(post));
	        			
	        			HttpResponse response = httpClient.execute(httpPost);
	        			int httpRes=response.getStatusLine().getStatusCode();
	        			
 	        			if (httpRes==200) {
							
	        				HttpEntity httpEntity = response.getEntity();
	        				String	con = EntityUtils.toString(httpEntity);
	        				
	        				
	        				// Log.d("Result", con);
	        				 System.out.println(con);
	        				// Utils.writeFileToInternalStorage(act, con);
	        				
 	        				Bundle b = new Bundle();
	        				b.putString("text", con);
	        				msg.setData(b);
	        				callback.sendMessage(msg); 
	        			
	        				
						}
	        			else {
	        				Log.d("Text", httpRes+"");
					Toast.makeText(act, "Something went wrong", Toast.LENGTH_SHORT).show();
						}
	                	
	                }
	                catch (ConnectTimeoutException e) {
	                	 msg.what=1;
	                	 callback.sendMessage(msg); 	
					}
	                catch (SocketTimeoutException e) {
	                	 msg.what=1;
 	            		 callback.sendMessage(msg); 	
	                }
	                catch (ParseException e) {
	                	msg.what=3;
	            		callback.sendMessage(msg); 
					} 
	                catch (IOException e) {
						msg.what=3;
	            		callback.sendMessage(msg); 
					}
	                finally{
                	splashTask.cancel();
	                progressDialog.dismiss();
	                	
	                }
	                
		     }};
	       // t.start();
	       new Timer().schedule(splashTask,0);
	        
		 }
		 else {
			 Toast.makeText(act, R.string.network_error, Toast.LENGTH_SHORT).show();
			 final Message msg = Message.obtain();
			 msg.what=4;
			 callback.sendMessage(msg); 
		 }
    }
}
