package be.groept.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button b;
	Intent i;
	EditText e;
	String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		e = (EditText)findViewById(R.id.editText1);
		b = (Button)findViewById(R.id.button1);
		i = new Intent(getApplicationContext(), LabelActivity.class );
		
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
								
					
				
				Context context = getApplicationContext();
				CharSequence text = "Onderweg!";
				int duration = Toast.LENGTH_LONG;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				startActivity(i);
				
			}
		});	
	}	
}
