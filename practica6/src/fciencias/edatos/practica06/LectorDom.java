//Erick Bernal Márquez		317042522
//Practica 6:arboles		enero/2021
//Feliz año
package fciencias.edatos.practica06;
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
    public void lee(String nombre) throws ParserConfigurationException,  
					  SAXException, IOException, DOMException{//ciudades.xml
	// Archivo que representa el xml
        File archivo = new File(nombre);

        // Se crean las fabricas necesarias para generar un creador de documentos
        DocumentBuilderFactory fabricaDeConstructores = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeConstructores.newDocumentBuilder();
        Document documento = constructor.parse(archivo);

        // Se crea la lista de Ciudades
        NodeList ciudades = documento.getElementsByTagName("Ciudad");
        Node nodo;

        System.out.println("Las ciudades son:\n");

        // Se crean los elementos necesarios. Esto corresponde a las etiquetas.
        Element ciudad, coordenada;
	
	/*/        // Lista que almacena los propietarios de cada Auto
	NodeList ciudades;*/

	//con las opiniones
	NodeList coordenadas;

        for(int i = 0; i<ciudades.getLength(); i++){

            // Se obtiene el nodo con el primer auto
            nodo = ciudades.item(i);

            // Se convierte el nodo a un elemento.
            ciudad = (Element) nodo;

            /* Se acceden a los valores de los atributos almacenados en 
            * la etiqueta perteneciente a una ciudad. */
            System.out.println("Ciudad: "+ciudad.getAttribute("nombre")+ "\n\tEstado: "+ciudad.getAttribute("estado"));
	    
            /* No todos los autos cuentan con placas o anio. Así que se verifica
	     * si existen esos atributos. 
	     if(auto.hasAttribute("placas"))
	     System.out.println("PLacas: "+auto.getAttribute("placas"));
	     if(auto.hasAttribute("anio"))
	     System.out.println("Año: "+auto.getAttribute("anio"));*/
	    
	    
            // Se almacena en secciones una lista con los propietarios del auto.
            coordenadas = ciudad.getElementsByTagName("Coordenadas");
	    
            // Se imprimen los propietarios del libro, así como su contenido
            for(int j = 0; j<coordenadas.getLength(); j++){
		
                coordenada = (Element) coordenadas.item(j);
		
                System.out.println("\tCoordenadas: "+coordenada.getAttribute("coordenadasX")+", "+coordenada.getAttribute("coordenadasY"));
		
		
	    } // Termina for
	    
	    /*	    
	    // Se almacena en secciones una lista con las opiniones
            opiniones = auto.getElementsByTagName("Opinion");
	    
            // Se imprimen las opiniones de dicho auto
            for(int j = 0; j<opiniones.getLength(); j++){
	    
	    opinion = (Element) opiniones.item(j);
	    
	    System.out.println("\tOpiniones: "+opinion.getTextContent());
            } // Termina for
	    */
            System.out.println();
            
	} // Termina el recorrido de autos
	
	
    }
    
    /*   public static void main(String[] args) throws ParserConfigurationException,
	 SAXException, IOException, DOMException{
	 
	 LectorDom lector = new LectorDom();
	 lector.lee("Automoviles.xml");
	 }*/
    
}

