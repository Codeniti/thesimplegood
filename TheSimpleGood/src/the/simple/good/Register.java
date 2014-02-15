package the.simple.good;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import the.simple.good.utility.ActionBar;
import the.simple.good.utility.Utils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Register extends Activity {

	ActionBar ab;
	EditText userName,pass,fullName,email,phone;
	ImageView profilePic,preview;
	Button btnReg;
	Dialog selectDialog,previewDialog;
	LinearLayout camera,gallery;
	Button save,discard;
	Activity a=this;
	String _path;
	protected boolean _taken;
	
	private static final int SELECT_PICTURE = 1;
	protected static final String PHOTO_TAKEN	= "photo_taken";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		_path="file:///android_asset/logo.png";
		
		ab=(ActionBar)findViewById(R.id.actionbar);
		profilePic=(ImageView)findViewById(R.id.imgProfilePic);
		userName=(EditText)findViewById(R.id.txtUsername);
		pass=(EditText)findViewById(R.id.txtPassword);
		fullName=(EditText)findViewById(R.id.txtFullName);
		email=(EditText)findViewById(R.id.txtEmail);
		phone=(EditText)findViewById(R.id.txtPhone);
		btnReg=(Button)findViewById(R.id.btnRegister);
		
		ab.setActionTitle("Register");
		
		selectDialog=new Dialog(a);
        selectDialog.setContentView(R.layout.photo_pick);
        selectDialog.setTitle("Profile Photo");
        
        previewDialog=new Dialog(a);
        previewDialog.setContentView(R.layout.preview);
        previewDialog.setTitle("Set Picture ");
        
        preview=(ImageView)previewDialog.findViewById(R.id.imgPreview);
        
        camera=(LinearLayout)selectDialog.findViewById(R.id.camera);
        gallery=(LinearLayout)selectDialog.findViewById(R.id.gallery);
        save=(Button)previewDialog.findViewById(R.id.btnUpload);
        discard=(Button)previewDialog.findViewById(R.id.discard);
        
        
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				previewDialog.dismiss();
				try {
					executeMultipartPost();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
         
         discard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				previewDialog.dismiss();
			}
		});
         
         
        profilePic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				selectDialog.show();
			}
		});
        
        
        camera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectDialog.dismiss();
				startCameraActivity();
			}
		});
    	 
    	 
    	 gallery.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectDialog.dismiss();
				pickPhoto(v);
			}
		});
    	 
		btnReg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!Utils.isBlank(userName.getText().toString(), a)) {
					if (!Utils.isBlank(pass.getText().toString(), a)) {
						if (!Utils.isBlank(fullName.getText().toString(), a)) {
							if (!Utils.isBlank(email.getText().toString(), a)) {
								if (Utils.validateIsEmail(email.getText().toString(), a)) {
									
									postData();
									
								}
								else {
									Utils.showShortToast(a, "Enter valid emial");
								}
							}
							else {
								Utils.showShortToast(a, "Enter email");
							}
						}
						else {
							Utils.showShortToast(a, "Enter Name");
						}
					}
					else {
						Utils.showShortToast(a, "Enter password");
					}
				}
				else {
					Utils.showShortToast(a, "Enter username");
				}
			}
		});
		
	}
	
	public void pickPhoto(View view) {
    	//TODO: launch the photo picker
    	Intent intent = new Intent();
    	 intent.setType("image/*");
    	 intent.setAction(Intent.ACTION_GET_CONTENT);
    	 startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
    }
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    {	
    	if(requestCode==0)
    	{
    	switch( resultCode )
    	{
    		case 0:
    			Log.i( "MakeMachine", "User cancelled" );
    			break;
    			
    		case -1:
    			onPhotoTaken();
    			break;
    	}
    	}
    	else if (requestCode==1) {
    		
    		Bitmap bitmap = getPath(data.getData());
    		previewDialog.show();
    		preview.setImageBitmap(bitmap);
    		
		}
    }
	
	private Bitmap getPath(Uri uri) {

 		String[] projection = { MediaStore.Images.Media.DATA };
 		Cursor cursor = managedQuery(uri, projection, null, null, null);
 		int column_index = cursor
 				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
 		cursor.moveToFirst();
 		String filePath = cursor.getString(column_index);
 		cursor.close();
 		// Convert file path into bitmap image using below line.
 		Bitmap bitmap = BitmapFactory.decodeFile(filePath);

 		return bitmap;
 	}
	
	protected void onPhotoTaken()
    {
    	Log.i( "MakeMachine", "onPhotoTaken" );
    	
    	_taken = true;
    	
    	BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
     
    	
    	Bitmap bitmap = BitmapFactory.decodeFile( _path );
    	previewDialog.show();
    	preview.setImageBitmap(bitmap);
    	
    }
	
	protected void onSaveInstanceState( Bundle outState ) {
    	outState.putBoolean( Register.PHOTO_TAKEN, _taken );
    }
	
	public void executeMultipartPost() throws Exception {
		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			BitmapDrawable drawable = (BitmapDrawable) profilePic.getDrawable();

			Bitmap bitmap = drawable.getBitmap();

			bitmap.compress(CompressFormat.JPEG, 50, bos);

			byte[] data = bos.toByteArray();

//			HttpClient httpClient = new DefaultHttpClient();
//			HttpPost postRequest = new HttpPost("http://rewardzwallet.com/api/v1/my/avatar/edit.json");
	
			String fileName = String.format("File_%d.png",new Date().getTime());
			ByteArrayBody bab = new ByteArrayBody(data, fileName);
			
			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			reqEntity.addPart("avatar", bab);

//			postRequest.setEntity(reqEntity);
//			int timeoutConnection = 60000;
//			HttpParams httpParameters = new BasicHttpParams();
//			HttpConnectionParams.setConnectionTimeout(httpParameters,
//					timeoutConnection);
//			int timeoutSocket = 60000;
//			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//			HttpConnectionParams.setTcpNoDelay(httpParameters, true);
//			HttpResponse response = httpClient.execute(postRequest);
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//
//			response.getEntity().getContent(), "UTF-8"));
//			String sResponse;
//			StringBuilder s = new StringBuilder();
//
//			while ((sResponse = reader.readLine()) != null) {
//				s = s.append(sResponse);
//			}
//
//			System.out.println("Response: " + s);
//			showAlert(s.toString(), a, "Response");
		
		Bitmap bmSrc1 = ((BitmapDrawable)preview.getDrawable()).getBitmap();
		Bitmap bmSrc2 = bmSrc1.copy(bmSrc1.getConfig(), true);
		
		profilePic.setImageBitmap(bmSrc2);
		
		} catch (Exception e) {

			// handle exception here
			showAlert(e.getMessage(), a, "Error");

			// Log.e(e.getClass().getName(), e.getMessage());

		}
}
	
	public static void showAlert(final String msg, final Activity act,final String title){
    	AlertDialog.Builder alertDialog=new AlertDialog.Builder(act);
    	alertDialog.setTitle(title);
    	alertDialog.setMessage(msg);
    	alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
    		
    	AlertDialog ad=alertDialog.create();
    	ad.setOwnerActivity(act);

    	ad.show();
    }
	
	protected void onRestoreInstanceState( Bundle savedInstanceState){
    	Log.i( "MakeMachine", "onRestoreInstanceState()");
    	if( savedInstanceState.getBoolean( Register.PHOTO_TAKEN ) ) {
    		onPhotoTaken();
    	}
    }
	
	protected void startCameraActivity()
    {
    	File file = new File( _path );
    	Uri outputFileUri = Uri.fromFile( file );
    	
    	Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
    	intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
    	
    	startActivityForResult( intent, 0 );
    }
	
	public void postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://annucaterers.com/services/register.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
	        nameValuePairs.add(new BasicNameValuePair("username", userName.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("password", pass.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("email", email.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("name", fullName.getText().toString()));
//	        nameValuePairs.add(new BasicNameValuePair("file", ));
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
//					String userId=main.getString("user_id");
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
}
