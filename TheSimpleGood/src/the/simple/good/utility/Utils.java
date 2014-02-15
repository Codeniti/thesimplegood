package the.simple.good.utility;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Utils {
	
	public static String url="http://www.annucaterers.com/services/";
	
	public static void startActivity(Activity a,Class className) {
		Intent i = new Intent(a, className);
		a.startActivity(i);
	}
	
	public static void showShortToast(Activity a,String toastMsg){
		Toast.makeText(a, toastMsg, Toast.LENGTH_SHORT).show();
	}
	
	public static void showLongToast(Activity a,String toastMsg){
		Toast.makeText(a, toastMsg, Toast.LENGTH_LONG).show();
	}
	
	public static boolean validateIsEmail(String email, Activity a){
    	return EMAIL_ADDRESS.matcher(email).matches();
    }

	public static final Pattern EMAIL_ADDRESS
    = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"
    );

	public static Boolean isNetAvailable(Activity act)  {
  	  
        try{
            ConnectivityManager connectivityManager = (ConnectivityManager)       
                                                                      act.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileInfo = 
                                 connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiInfo.isConnected() || mobileInfo.isConnected()) {
            	
                return true;
            }
        }
        catch(Exception e){
           e.printStackTrace();
      
        }
        return false;
    }

	
	
	/**
     * Blank String validation
     * 
     * @param text
     * @param a
     * @return true if it is blank and false if it is not blank
     * 
     * @author hari
     */
    public static boolean isBlank(String text,Activity a){
    	if (text.length()==0 || text==null || text.equalsIgnoreCase("null") || text.length()==0) {
			return true;
		}
		else {
			return false;
		}
    }

    
}
