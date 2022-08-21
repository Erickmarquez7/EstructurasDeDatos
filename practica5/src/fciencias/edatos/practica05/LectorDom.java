//Erick Bernal Márquez		317042522
//Practica 5:xml		dic/2020

package fciencias.edatos.practica05;

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
					  SAXException, IOException, DOMException{
	// Archivo que representa el xml
        File archivo = new File(nombre);

        // Se crean las fabricas necesarias para generar un creador de documentos
        DocumentBuilderFactory fabricaDeConstructores = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeConstructores.newDocumentBuilder();
        Document documento = constructor.parse(archivo);

        // Se crea la lista de libros
        NodeList autos = documento.getElementsByTagName("Auto");
        Node nodo;

        System.out.println("Los automóviles son:");

        // Se crean los elementos necesarios. Esto corresponde a las etiquetas.
        Element auto, propietario, opinion;

        // Lista que almacena los propietarios de cada Auto
	NodeList propietarios;

	//con las opiniones
	NodeList opiniones;

        for(int i = 0; i<autos.getLength(); i++){

            // Se obtiene el nodo con el primer auto
            nodo = autos.item(i);

            // Se convierte el nodo a un elemento.
            auto = (Element) nodo;

            /* Se acceden a los valores de los atributos almacenados en 
            * la etiqueta perteneciente a un auto. */
            System.out.println("Auto: "+auto.getAttribute("marca")+
                "\nAutor: "+auto.getAttribute("modelo"));

            /* No todos los autos cuentan con placas o anio. Así que se verifica
            * si existen esos atributos. */
            if(auto.hasAttribute("placas"))
                System.out.println("PLacas: "+auto.getAttribute("placas"));
            if(auto.hasAttribute("anio"))
                System.out.println("Año: "+auto.getAttribute("anio"));
	    

            // Se almacena en secciones una lista con los propietarios del auto.
            propietarios = auto.getElementsByTagName("Propietario");

            // Se imprimen los propietarios del libro, así como su contenido
            for(int j = 0; j<propietarios.getLength(); j++){
		
                propietario = (Element) propietarios.item(j);
		
                System.out.println("\tPropietarios: "+propietario.getAttribute("nombre")+" "+propietario.getAttribute("paterno")+" "+propietario.getAttribute("materno"));
		
            } // Termina for
	    	    
	    // Se almacena en secciones una lista con las opiniones
            opiniones = auto.getElementsByTagName("Opinion");
	    
            // Se imprimen las opiniones de dicho auto
            for(int j = 0; j<opiniones.getLength(); j++){
		
                opinion = (Element) opiniones.item(j);
		
                System.out.println("\tOpiniones: "+opinion.getTextContent());
            } // Termina for
	    
            System.out.println();
            
	} // Termina el recorrido de autos
	
	
    }
    
 /*   public static void main(String[] args) throws ParserConfigurationException,
    SAXException, IOException, DOMException{
    
        LectorDom lector = new LectorDom();
        lector.lee("Automoviles.xml");
    }*/
}
