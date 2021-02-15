//Erick Bernal Márquez		317042522
//Practica 6:arboles		enero/2021
//Feliz año
package fciencias.edatos.practica06;

import org.jdom2.input.SAXBuilder;
import java.io.IOException;
import org.jdom2.JDOMException;
import org.jdom2.Document;
import java.io.FileInputStream;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import java.util.List;
import java.util.ArrayList;
import org.jdom2.Attribute;
import org.jdom2.output.Format;
import java.io.FileWriter;

public class EscritorJdom{

    /**
     * Escribe un archivo XML usando JDOM.
     * @param nombre el nombre del archivo a escribir.
     * @param estructura la estructura que contiene los elementos
     * a represetar en el archivo XML.
     * @throws IOException en caso de ocurrir un error durante la
     * escritura del archivo.
     */
    public void escribe(String nombre, ArrayList<Ciudad> lista) 
	throws IOException{
	
        // Se crea la etiqueta raíz y se asigna a un documento nuevo.
        Element raiz = new Element("Ciudades");
        Document documento = new Document(raiz);
	
        // Se crean los elementos necesarios. Uno por etiqueta.
        Element elemento, ciudad, coordenadas;
	
        /* Se crea un ejemplar auxiliar del tipo de los elementos
	 * de la estructura. En este caso Ciudad*/
        Ciudad actual;
        
        for(int i = 0; i<lista.size(); i++){
            // Se obtiene el elemento en la posición i.
            actual = lista.get(i);
	    
            // Se crea una nueva etiqueta
            elemento = new Element("Ciudad");
            // Se crea y asigna un nuevo atributo en la etiqueta del elemento.
            elemento.setAttribute(new Attribute("nombre", actual.nombre)); //para marca
	    elemento.setAttribute(new Attribute("estado", actual.estado)); //modelo
	    	    
	    
            // Se le da un padre a la etiqueta. En este caso la raiz.
            documento.getRootElement().addContent(elemento);

            // Se crea una nueva etiqueta
            coordenadas = new Element("Coordenadas");
            // Se crean y se asignan atributos a la etiqueta.
            coordenadas.setAttribute(new Attribute("coordenadasX", ""+actual.coorx));
            coordenadas.setAttribute(new Attribute("coordenadasY", ""+actual.coory));

            // Se le da un padre a la etiqueta. En este caso dentro del elemento.
            elemento.addContent(coordenadas);

        }

        // Se escribe el archivo XML con todas sus características.
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(documento, new FileWriter(nombre));
    }

}


