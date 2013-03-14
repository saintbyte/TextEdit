package com.sg.textedit;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FilelistActivity extends Activity {
    Button ok,cancel;
    ListView  lst;
    String stgPath, basePath;
    String[] theNamesOfFiles;
    File[] filelist;
    File dir;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filelist);
		//ok = (Button)findViewById(R.id.button_ok);
		//cancel = (Button)findViewById(R.id.button_cancel);
		lst = (ListView)findViewById(R.id.listView1);
		Log.e("FilelistActivity","---");
		try
		{
			basePath = Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		catch(Exception E)
		{
			basePath = "";
		}
		stgPath = basePath + File.separator + "textedit";
		Log.e("FilelistActivity",stgPath);
		try
		{
			dir = new File(stgPath);
			Log.e("FilelistActivity","1");
			filelist = dir.listFiles();
			Log.e("FilelistActivity","2");
			if (filelist != null)
			{
				theNamesOfFiles = new String[filelist.length];
				Log.e("FilelistActivity","3");
				for (int i = 0; i < theNamesOfFiles.length; i++) {
					   Log.e("FilelistActivity",filelist[i].getName());
					   theNamesOfFiles[i] = filelist[i].getName();
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, theNamesOfFiles);
				lst.setAdapter(adapter); 
			}
		}
		catch(Exception E)
		{
			Log.e("FilelistActivity E.getMessage:",E.getMessage());
		}
		Log.e("FilelistActivity","4");
		lst.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				    String s = (String) ((TextView)view).getText();
	        		Intent intent = new Intent();
	        		intent.putExtra("from", "filelist");
	        		intent.putExtra("filename", s);
	        		setResult(RESULT_OK, intent);
	        		finish();
			  }
			}); 
		Log.e("FilelistActivity","OK");
		/*
		ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	    Intent intent = new Intent();
            	    //intent.putExtra("name", etName.getText().toString());
            	    setResult(RESULT_OK, intent);
            	    finish();
            }
        });
		Log.e("FilelistActivity","Cancel");
		cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
        	    //setResult(RESULT_CANCEL, intent);
        	    finish();
            }
        });	
        */
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_filelist, menu);
		return true;
	}

}
