//Erick Bernal Márquez		317042522
//Practica 5:xml		dic/2020

package fciencias.edatos.practica05;

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
    public void escribe(String nombre, ArrayList<Autos> lista) 
	throws IOException{
	
        // Se crea la etiqueta raíz y se asigna a un documento nuevo.
        Element raiz = new Element("Automoviles");
        Document documento = new Document(raiz);
	
        // Se crean los elementos necesarios. Uno por etiqueta.
        Element elemento, propietario, opinion;
	
        /* Se crea un ejemplar auxiliar del tipo de los elementos
	 * de la estructura. En este caso Auto*/
        Autos actual;
        
        for(int i = 0; i<lista.size(); i++){
            // Se obtiene el elemento en la posición i.
            actual = lista.get(i);
	    
            // Se crea una nueva etiqueta
            elemento = new Element("Auto");
            // Se crea y asigna un nuevo atributo en la etiqueta del elemento.
            elemento.setAttribute(new Attribute("marca", actual.marca)); //para marca
	    elemento.setAttribute(new Attribute("modelo", actual.modelo)); //modelo
	    
	    elemento.setAttribute(new Attribute("placas", actual.placas)); //placas
	    elemento.setAttribute(new Attribute("anio", actual.anio)); //anio
	    
	    
            // Se le da un padre a la etiqueta. En este caso la raiz.
            documento.getRootElement().addContent(elemento);

            // Se crea una nueva etiqueta
            propietario = new Element("Propietario");
            // Se crean y se asignan atributos a la etiqueta.
            propietario.setAttribute(new Attribute("nombre", ""+actual.nombre));
            propietario.setAttribute(new Attribute("materno", ""+actual.materno));
            propietario.setAttribute(new Attribute("paterno", ""+actual.paterno));

            // Se le da un padre a la etiqueta. En este caso dentro del elemento.
            elemento.addContent(propietario);

            // Se asigna texto dentro de las etiquetas.
            opinion = new Element("Opinion");
            opinion.setText(actual.daOpinion());

            // Se añade un padre a la etiqueta.
            elemento.addContent(opinion);
        }

        // Se escribe el archivo XML con todas sus características.
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(documento, new FileWriter(nombre));
    }

}


