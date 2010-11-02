package com.gtugGT.demo.Reader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.graphics.BitmapFactory;

public class MyAdapter extends ArrayAdapter<String> {
	List<URL> links;
	LayoutInflater inf;
	public MyAdapter(Context context, int textViewResourceId,
			List<String> objects, List<URL> links) {
		super(context, textViewResourceId, objects);
		this.links = links;
		this.inf = LayoutInflater.from(context);
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View row;
		
		if (null == convertView) {
			row = inf.inflate(R.layout.row, null);
			/*
			 * Paso 8: agregamos una imagen que vamos a ir a traer a internet, ver método loadFromUrl y
			 * modificar también row.xml
			 * */

			ImageView iv = (ImageView) row.findViewById(R.id.imgEntry);
			URL link = links.get(position);
			if (link == null) {
				try {
					link = new URL("http://a3.twimg.com/profile_images/1112101815/rwwlogo_twitter.png");
				} catch (MalformedURLException e) {}
			}
			iv.setImageBitmap(loadFromUrl(link));			        
	        iv.setScaleType(ImageView.ScaleType.FIT_XY);
	        
			TextView tv = (TextView) row.findViewById(R.id.txtEntry);
			tv.setText(getItem(position));			
		} else {
			row = convertView;
		}
 
		return row;    			
	}
	
	/*
	 * Método necesario para el paso 8
	 * */
	
	private Bitmap loadFromUrl(URL link) {		
		Bitmap bitmap = null;
		InputStream in = null;       
		try {
			in = link.openConnection().getInputStream();
		    bitmap = BitmapFactory.decodeStream(in, null, null);
		    in.close();
		} catch (IOException e) {}
		return bitmap; 
	}
	 
	
}
