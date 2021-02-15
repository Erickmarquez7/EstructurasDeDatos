//Erick Bernal Márquez		317042522
//Practica 7:mapas		enero/2021

package fciencias.edatos.practica07;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import org.w3c.dom.Element;
import org.w3c.dom.DOMException;


public class LectorDom{

    /**
     * Realiza la lectura de un archivo XML.
     * @param nombre el nombre del archivo a leer.
     * @throws ParserConfigurationException si ocurre algún problema
     * al instanciar el parser.
     * @throws SAXException si ocurre un problema durante la lectura
     * del archivo.
     * @throws IOException si ocurre un problema al abrir el archivo
     * o en la busqueda de este.
     * @throws DOMException si se solicitara información que no existe o
     * no está disponible en las etiquetas del archivo.
     */
     
     
    public UnsortedTableMap<String, Elemento> lee(String nombre, int n) throws ParserConfigurationException, 
					  SAXException, IOException, DOMException{ //la n es solo para saber si imprime la tabla o no
	// Archivo que representa el xml
        File archivo = new File(nombre);
        
        UnsortedTableMap<String, Elemento> mapa = new UnsortedTableMap<>(); //para que cuando los leamos loa almacenemos en un mapa
        
        //para ir guardando los elemento que pondremos en el mapa
        Elemento nuevoElemento;
        
	
        // Se crean las fabricas necesarias para generar un creador de documentos
        DocumentBuilderFactory fabricaDeConstructores = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeConstructores.newDocumentBuilder();
        Document documento = constructor.parse(archivo);
	
        // Se crea la lista de elementos
        NodeList elementos = documento.getElementsByTagName("elemento"); //los que tienen como etiqueta elemento
        Node nodo;
	
        //System.out.println("Los Elementos son:");
	
        // Se crean los elementos necesarios. Esto corresponde a las etiquetas. //solo hay una xd
        Element elemento;
        	
        for(int i = 0; i<elementos.getLength(); i++){
	    
            // Se obtiene el nodo del primer elemento
            nodo = elementos.item(i);
	    
            // Se convierte el nodo a un elemento.
            elemento = (Element) nodo;
	    
            /* Se acceden a los valores de los atributos almacenados en 
	     * la etiqueta perteneciente a un elemento. */
	    if (n == 0) //solo para saber si la imprime
            System.out.println("Elemento: "+elemento.getAttribute("nombre")+
			       "\nSímbolo : "+elemento.getAttribute("simbolo")+
			       "\nNumero  : "+elemento.getAttribute("numero")+
			       "\nMasa    : "+elemento.getAttribute("masa")+"\n");
			       //esto era para imprimirlo pero solo quiero convertirlos a objeto xd
	    
	    //creamos el elemento
	    Integer numero = Integer.valueOf(elemento.getAttribute("numero"));
	    Double masa = Double.valueOf(elemento.getAttribute("masa"));
	    nuevoElemento = new Elemento(elemento.getAttribute("nombre"), elemento.getAttribute("simbolo"), numero, masa);
	    //lo añadimos al mapa
	    mapa.put(nuevoElemento.getSimbolo(), nuevoElemento);
	    
	    
	}
	//regresamos el mapa
	return mapa;
	/*   public static void main(String[] args) throws ParserConfigurationException,
	     SAXException, IOException, DOMException{
	     
	     LectorDom lector = new LectorDom();
	     lector.lee("Automoviles.xml");
	     }*/
    }

}
