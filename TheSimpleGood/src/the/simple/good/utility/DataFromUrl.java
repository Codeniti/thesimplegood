package the.simple.good.utility;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import the.simple.good.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class DataFromUrl {

	static TimerTask splashTask;
	static ProgressDialog progressDialog;

	 public static void getWebData(final String url, final Handler callback,final Activity act) throws IOException
	    {

	 if (!Utils.isNetAvailable(act)) {
		 	
		 progressDialog = ProgressDialog.show(act, "Please Wait", "Loading...");
		 final Message msg = Message.obtain();

		System.out.println(url);
		
		 Thread t = new Thread(new Runnable(){
		// splashTask = new TimerTask() {
	    	
		     public void run() {
	                try{
						
	    		        msg.what=2;
	                	HttpGet httpget = new HttpGet(url);
	                	HttpParams httpParameters = new BasicHttpParams();
	                	int timeoutConnection = 30000;
	                	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	                	int timeoutSocket = 50000;
	                	HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
	                	
	                	DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

	        			HttpResponse response = httpClient.execute(httpget);
	        			int httpRes=response.getStatusLine().getStatusCode();
	        			
	        			if (httpRes==200) {
							
	        				HttpEntity httpEntity = response.getEntity();
	        				String	con = EntityUtils.toString(httpEntity);
	        				
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
	                	
					} catch (IOException e) {
						msg.what=3;
	            		callback.sendMessage(msg); 

					}
	                finally{
	                	progressDialog.dismiss();
	                }
	                
		     }});
	        t.start();
	       //new Timer().schedule(splashTask,0);
	 }
	 else {
         Toast.makeText(act, R.string.network_error, Toast.LENGTH_SHORT).show();
		}
    }
	 
	 
	 public static void PostWebData(final String url, final Handler callback,final Activity act) throws IOException
	    {

	 if (Utils.isNetAvailable(act)) {
		 	
		 progressDialog = ProgressDialog.show(act, "Please Wait", "Loading...");
		 final Message msg = Message.obtain();

		
		
		 Thread t = new Thread(new Runnable(){
		// splashTask = new TimerTask() {
	    	
		     public void run() {
	                try{
						
	    		        msg.what=2;
	                	HttpPost httpget = new HttpPost(url);
	                	HttpParams httpParameters = new BasicHttpParams();
	                	int timeoutConnection = 30000;
	                	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	                	int timeoutSocket = 50000;
	                	HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
	                	
	                	DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

	        			HttpResponse response = httpClient.execute(httpget);
	        			int httpRes=response.getStatusLine().getStatusCode();
	        			
	        			if (httpRes==200) {
							
	        				HttpEntity httpEntity = response.getEntity();
	        				String	con = EntityUtils.toString(httpEntity);
	        				
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
	                	
					} catch (IOException e) {
						msg.what=3;
	            		callback.sendMessage(msg); 

					}
	                finally{
	                	progressDialog.dismiss();
	                }
	                
		     }});
	        t.start();
	       //new Timer().schedule(splashTask,0);
	 }
	 else {
   Toast.makeText(act, R.string.network_error, Toast.LENGTH_SHORT).show();
		}
}

	 
}
