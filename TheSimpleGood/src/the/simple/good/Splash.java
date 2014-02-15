package the.simple.good;

import the.simple.good.utility.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Splash extends Activity{

	Button reg,login;
	Activity a= this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		reg=(Button)findViewById(R.id.btnRegister);
		login=(Button)findViewById(R.id.btnLogin);
//		
		
		reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Utils.showShortToast(a, "Register Clicked");
				Utils.startActivity(a, Register.class);
			}
		});
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Utils.showShortToast(a, "Login Clicked");
				Utils.startActivity(a, Login.class);
			}
		});
		
	}
}
