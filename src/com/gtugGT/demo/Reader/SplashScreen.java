package com.gtugGT.demo.Reader;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {		
	static boolean status = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		comentar para paso 9
//		startActivity(new Intent("com.gtugGT.demo.Reader.EntryList"));
		/*
		 * Paso 9: agregaremos una pantalla splash con un thread y mientras se muestra otro thread
		 * hara las veces del parser
		 */
		
	    setContentView(R.layout.splash);
	    Thread getThread = new Thread(){
	    	public void run(){
	    		try {
	    			EntryList.data = new XMLParser(EntryList.feedUrl).parse();
	    		} catch (Exception e) {
	    		}
	    	}
	    };
	    Thread splashThread = new Thread() {
	    	public void run() {
	    		try {
	                while(!status) {
	                	sleep(100);
	                }
	    		} catch(Exception e) {
	            } finally {
	            	finish();	     
	            	startActivity(new Intent("com.gtugGT.demo.Reader.EntryList"));
	                stop();
	            }
	          }
	    };
	    splashThread.start();
	    getThread.start();
	}
}
