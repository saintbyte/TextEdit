package com.sg.textedit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.sg.textedit.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button butt_new,butt_save,butt_open,butt_exit;
    TextView txt;
	SharedPreferences sh;
    SharedPreferences.Editor editor;
    String filename;
    String basePath,stgPath;
    File f;
    static final int PICK_CONTACT_REQUEST = 0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
    		Bundle extras;
            if (resultCode == RESULT_OK) {
            	extras = data.getExtras();
            	String from  = extras.getString("from");
            	filename  = extras.getString("filename");
            	if (from.equals("selectname"))
            	{
            		this.saveFile();
            	}
            	if (from.equals("filelist"))
            	{
            		this.openFile();
            	}
                // A contact was picked.  Here we will just display it
                // to the user.
                //startActivity(new Intent(Intent.ACTION_VIEW, data));
            }
    }
	private void openFile() {
		try
		{
			FileReader fstream = null;
			try {
				Log.d(stgPath,stgPath+ File.separator + filename);
				fstream = new FileReader(stgPath+ File.separator + filename);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	BufferedReader out = new BufferedReader(fstream);
		  	String s;
		  	String all = new String("");
		  	try {
				while((s = out.readLine()) != null) { 
					all +=s; 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		  	txt.setText(all);
		  //Close the output stream
		  	try {
				fstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	
		}
		finally
		{
			
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

      //---------------------------------------------------------
        try{
        	Log.e("MKDIR", "BEGIN");
        	try
        	{
        	basePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        	}
        	catch(Exception E)
        	{
        		basePath = "";
        	}
        	stgPath = basePath + File.separator + "textedit";
        	Log.e("MKDIR stgPath", stgPath);
        	f = new File(stgPath);
        	if (f.exists() && f.isDirectory())
        	{
        	 // Все ОК
        	}
        	else
        	{
        		Log.e("MKDIR stgPath", "Begin mkdir");
        		Boolean b = f.mkdir();
        		Log.e("MKDIR stgPath", "END mkdir");
        	}
        } 
        finally
        {
        	
        }
		//---------------------------------------------------------
		
		txt = (TextView)findViewById(R.id.editText1);
		sh = getPreferences(MODE_PRIVATE);
        try 
        {
        	filename = sh.getString("lastfile", "");
        	if (!filename.equals(""))
        	{
        		// Загружаем файл
        		
        	}
        }
        catch(Exception E)
        {

        }
      //---------------------------------------------------------
		butt_save = (Button)findViewById(R.id.button2);
		butt_save.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                		saveFile();
                }
        });	
		//--------------------------------------------------------
		butt_open = (Button)findViewById(R.id.button3);
		butt_open.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                	startActivityForResult(new Intent(MainActivity.this, FilelistActivity.class),1);
                }
        });	
		//---------------------------------------------------------
		butt_new = (Button)findViewById(R.id.button1);
		butt_new.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                       txt.setText("");
                       filename = new StringBuilder().toString();
                }
        });	
		//---------------------------------------------------------
		butt_exit = (Button)findViewById(R.id.button4);
		butt_exit.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                		finish();
                        System.gc();
                        System.exit(0);
                }
        });	
	}
	public boolean saveFile()
	{
		if (filename.equals(""))
		{	
			startActivityForResult(new Intent(MainActivity.this, SelectnameActivity.class),1);
			return false;
		} else
		{
			//stgPath
			  try{
				  // Create file 
				  	FileWriter fstream = new FileWriter(stgPath+ File.separator + filename);
				  	BufferedWriter out = new BufferedWriter(fstream);
				  	out.write(txt.getText().toString());
				  //Close the output stream
				  	out.close();
				  	return true;
				  }
			  catch (Exception e){//Catch exception if any
					  System.err.println("Error: " + e.getMessage());
					  return false;
				  }
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
