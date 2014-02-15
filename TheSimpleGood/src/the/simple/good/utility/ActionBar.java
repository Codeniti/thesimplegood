package the.simple.good.utility;

import the.simple.good.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ActionBar extends RelativeLayout{

	public static TextView actionTitle;
	
	public ActionBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.actionbar, this);
	}

	public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }
	
	public ActionBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
        initViews(context, attrs);
    }
	
	public void setActionTitle(String strName) {
		actionTitle.setText(strName);
	}
	
	private void initViews(Context context, AttributeSet attrs) {

		LayoutInflater.from(context).inflate(R.layout.actionbar, this);
 
        //left text view
         actionTitle = (TextView) this.findViewById(R.id.txtActionTitle);
        
    }
	
}
