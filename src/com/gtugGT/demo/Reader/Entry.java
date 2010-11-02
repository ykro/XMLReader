package com.gtugGT.demo.Reader;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry implements Comparable<Entry> {
	static SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	private String title;
	private URL link;
	private URL image;
	private String description;
	private Date date;

	public String getTitle() {
		return this.title;
	}

	public URL getLink() {
		return this.link;
	}

	public String getDescription() {
		return this.description;
	}
	
	public String getDate() {
		return FORMATTER.format(this.date);
	}
	
	public URL getImage(){
		return this.image;
	}
	
	public void setTitle(String title) {
		this.title = title.trim();
	}
	
	public void setLink(String link) {
		try {
			this.link = new URL(link);
		} catch (java.net.MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public void setDate(String date) {
		try {
			this.date = FORMATTER.parse(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void setImage(String image){		
		try {			
			this.image = new URL(image);
		} catch (Exception e) {
			this.image = null;
		}
	}

	public int compareTo(Entry another) {
		if (another == null) return 1;
		// sort descending, most recent first
		return another.date.compareTo(date);
	}
}




