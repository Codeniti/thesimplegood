package the.simple.good;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.json.JSONException;
import org.json.JSONObject;

import the.simple.good.utility.ActionBar;
import the.simple.good.utility.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	ActionBar ab;
	EditText uName,pass;
	Button signIn;
	TextView forgotPass;
	
	Activity a=this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		ab=(ActionBar)findViewById(R.id.actionbar);
		ab.setActionTitle("Login");
		uName=(EditText)findViewById(R.id.txtUsername);
		pass=(EditText)findViewById(R.id.txtPassword);
		forgotPass=(TextView)findViewById(R.id.txtForgotPassword);
		signIn=(Button)findViewById(R.id.btnSignIn);
		
		signIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!Utils.isBlank(uName.getText().toString(), a)) {
					if (!Utils.isBlank(pass.getText().toString(), a)) {
//						CODE TO LOGIN
						postData();
//						final List<NameValuePair> header = new ArrayList<NameValuePair>(2);
//						header.add(new BasicNameValuePair("username", uName.getText().toString()));
//						header.add(new BasicNameValuePair("pwd", pass.getText().toString()));
//						
//						final List<NameValuePair> body = new ArrayList<NameValuePair>(0);
//						
//						try {
//							PostWithHeader.getWebData(Utils.url+"login.php", header, body, loginHandler, a);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
					else {
						Utils.showShortToast(a, "Enter password");
					}
				}
				else {
					Utils.showShortToast(a, "Enter username or email");
				}
			}
		});
	}
	public void postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://annucaterers.com/services/login.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("username", uName.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("pwd", pass.getText().toString()));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        String msg= EntityUtils.toString(response.getEntity());
	        
	        JSONArray parent;
			try {
				parent = new JSONArray(msg);
				JSONObject main = parent.getJSONObject(0);
				Utils.showShortToast(a, main.toString());
				String status;
				
				status=main.getString("success");
				String message=main.getString("message");
				
				if (status.equalsIgnoreCase("1")) {
					String userId=main.getString("user_id");
					Intent i = new Intent(a, Feed.class);
					startActivity(i);
				}
				else {
					Utils.showShortToast(a, message);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Utils.showShortToast(a, e.getMessage());
			}
			

//	        Toast.makeText(getBaseContext(), msg.toString(), Toast.LENGTH_SHORT).show();

	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    	Toast.makeText(getBaseContext(), "ClientProtocolException exception", Toast.LENGTH_SHORT).show();
	    } 
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	    	Toast.makeText(getBaseContext(), "IO exception"+e.getLocalizedMessage()+"--"+e.getMessage()+"--"+e.getCause(), Toast.LENGTH_SHORT).show();
	    }
	} 
	
	public Handler loginHandler = new Handler(){
		
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
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
					JSONObject main = parent.getJSONObject(0);
					
					int status;
					
					status=main.getInt("status");
					String message=main.getString("message");
					
					if (status==1) {
						Utils.showShortToast(a, "success");
						String userId=main.getString("user_id");
					}
					else {
						Utils.showShortToast(a, "zero");
					}
//					Utils.showShortToast(a, message);
//					Toast.makeText(getBaseContext(), message+" "+status ,Toast.LENGTH_SHORT).show();
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Utils.showShortToast(a, e.getMessage());
				}
				
				
			}
		}
		
	};

		
}
