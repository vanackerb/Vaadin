package be.groept.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LabelActivity extends Activity {

	Button b;
	TextView tv;
	EditText e;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_label);
		
		b = (Button)findViewById(R.id.back);
		tv = (TextView)findViewById(R.id.textView1);
		e = (EditText)findViewById(R.id.editText1);
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				setContentView(R.layout.activity_main);
			}
		});
	}
}
