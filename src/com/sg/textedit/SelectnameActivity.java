package com.sg.textedit;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SelectnameActivity extends Activity {
	Button ok;
	EditText tx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectname);
			tx = (EditText)findViewById(R.id.editText_name);
			ok = (Button)findViewById(R.id.button_filename_sel);
			ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	String s = tx.getText().toString();
            	if (!s.equals("")){
            		Intent intent = new Intent();
            		intent.putExtra("from", "selectname");
            		intent.putExtra("filename", s);
            		setResult(RESULT_OK, intent);
            		finish();
            	}
            	else
            	{
            		
            	}
            }
        });	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_selectname, menu);
		return true;
	}

}
