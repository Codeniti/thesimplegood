package the.simple.good;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Upload extends Activity {

	Button upload;
	Dialog selectDialog,previewDialog;
	LinearLayout camera,gallery;
	Button save,discard;
	Activity a=this;
	ImageView preview,imgUpload;
	String _path;
	protected boolean _taken;
	
	private static final int SELECT_PICTURE = 1;
	protected static final String PHOTO_TAKEN	= "photo_taken";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_picture);
		
		upload=(Button)findViewById(R.id.btnUpload);
		imgUpload=(ImageView)findViewById(R.id.imgUpload);
		
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
        camera=(LinearLayout)selectDialog.findViewById(R.id.camera);
        gallery=(LinearLayout)selectDialog.findViewById(R.id.gallery);
        
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
    	 
		upload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				selectDialog.show();
			}
		});
		
		
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
	}
	
	public void executeMultipartPost() throws Exception {
		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			BitmapDrawable drawable = (BitmapDrawable) imgUpload.getDrawable();

			Bitmap bitmap = drawable.getBitmap();

			bitmap.compress(CompressFormat.JPEG, 50, bos);

			byte[] data = bos.toByteArray();

			HttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://annucaterers.com/services/image.php");
	
			String fileName = String.format("File_%d.png",new Date().getTime());
			ByteArrayBody bab = new ByteArrayBody(data, fileName);
			
			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			reqEntity.addPart("avatar", bab);

			postRequest.setEntity(reqEntity);
			int timeoutConnection = 60000;
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters,
					timeoutConnection);
			int timeoutSocket = 60000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			HttpConnectionParams.setTcpNoDelay(httpParameters, true);
			HttpResponse response = httpClient.execute(postRequest);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
			response.getEntity().getContent(), "UTF-8"));
			String sResponse;
			StringBuilder s = new StringBuilder();

			while ((sResponse = reader.readLine()) != null) {
				s = s.append(sResponse);
			}

			System.out.println("Response: " + s);
			showAlert(s.toString(), a, "Response");
		
		Bitmap bmSrc1 = ((BitmapDrawable)preview.getDrawable()).getBitmap();
		Bitmap bmSrc2 = bmSrc1.copy(bmSrc1.getConfig(), true);
		
		imgUpload.setImageBitmap(bmSrc2);
		
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
	
	public void pickPhoto(View view) {
    	//TODO: launch the photo picker
    	Intent intent = new Intent();
    	 intent.setType("image/*");
    	 intent.setAction(Intent.ACTION_GET_CONTENT);
    	 startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
    }
	
	protected void startCameraActivity()
    {
    	File file = new File( _path );
    	Uri outputFileUri = Uri.fromFile( file );
    	
    	Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
    	intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
    	
    	startActivityForResult( intent, 0 );
    }
}
