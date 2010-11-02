package com.gtugGT.demo.Reader;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;

public class EntryList extends ListActivity {
	static List<Entry> data;	
	static String feedUrl = "http://feeds.feedburner.com/readwriteweb";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /*
         * Paso 1: ver la clase Entry que almacena cada uno de los entries del feed
         * Paso 2: ver la clase XMLParser encargada de obtener y parsear en entries el contenido
         * Paso 3: instanciar un parser nuevo y ejecutarlo
         * Luego, para el paso 9 se comentará esta línea
         * */
//    	data = new XMLParser(feedUrl).parse();
    	
        /*
         * Paso 4: creamos 2 estructuras auxiliares qué tendrán enlaces y títulos
         * */
        
    	List<URL> links = new ArrayList<URL>(data.size());
    	List<String> titles = new ArrayList<String>(data.size());
    	for (Entry e : data){
    		titles.add(e.getTitle());
    		links.add(e.getImage());    		
    	}
    	
        
        /*
         * Paso 5: ver la clase MyAdapter, utilizada para modificar los views de un ArrayAdapter
         * Paso 6: creamos un adaptador nuevo utilizando el layout de la fila enviandole los títulos y los links
         * 
         * */
    	this.setListAdapter(new MyAdapter(this, R.layout.row,titles,links));         
    }
    
    /*
     * Paso 7: agregamos una interacción con los enlaces para qué al darle click se abra el navegador
     * */
    
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent viewMessage = new Intent(Intent.ACTION_VIEW, 
				Uri.parse(data.get(position).getLink().toExternalForm()));
		this.startActivity(viewMessage);
	}
	
}