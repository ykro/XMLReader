package com.gtugGT.demo.Reader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	static final String RSS = "rss";
	static final String LINK = "link";
	static final String ITEM = "item";	
	static final String TITLE = "title";	
	static final String CHANNEL = "channel";
	static final String PUB_DATE = "pubDate";
	static final String DESCRIPTION = "description";

	private URL url;
	
	public XMLParser(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public List<Entry> parse() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		List<Entry> entries = new ArrayList<Entry>();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(this.url.openConnection().getInputStream());
			Element root = dom.getDocumentElement();
			NodeList items = root.getElementsByTagName(ITEM);
			for (int i=0;i<items.getLength();i++){
				Entry Entry = new Entry();
				Node item = items.item(i);
				NodeList properties = item.getChildNodes();
				for (int j=0;j<properties.getLength();j++){
					Node property = properties.item(j);
					String name = property.getNodeName();
					if (name.equalsIgnoreCase(TITLE)){
						Entry.setTitle(property.getFirstChild().getNodeValue());
					} else if (name.equalsIgnoreCase(LINK)){
						Entry.setLink(property.getFirstChild().getNodeValue());
					} else if (name.equalsIgnoreCase(DESCRIPTION)){
						StringBuilder text = new StringBuilder();
						NodeList chars = property.getChildNodes();
						for (int k=0;k<chars.getLength();k++){
							text.append(chars.item(k).getNodeValue());
						}
						Entry.setDescription(text.toString());
						
						Pattern p = Pattern.compile(".*<img[^>]*src=\"([^\"]*)",Pattern.CASE_INSENSITIVE);
						Matcher m = p.matcher(text.toString());
						if (m.find()) {
							Entry.setImage(m.group(1));
						}
					} else if (name.equalsIgnoreCase(PUB_DATE)){
						Entry.setDate(property.getFirstChild().getNodeValue());
					}
				}
				entries.add(Entry);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		if (!SplashScreen.status){
			SplashScreen.status = true;
		} 
		return entries;
	}	
}
