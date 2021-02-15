//Erick Bernal Márquez		317042522
//Practica 6:arboles		enero/2021
//Feliz año

package fciencias.edatos.practica06;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.DOMException;
import javax.xml.parsers.ParserConfigurationException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Directorio{

    public static ArbolBinarioBusqueda<Ciudad> direc = new ArbolBinarioBusqueda<>(); //arbol de directorio
    public static ArrayList<Ciudad> escribe = new ArrayList<>(); //pa escribir y leer
    
    
    /** Recorre el árbol y pone en una lista los elementos que estén dentro 
     * de cierto rango, desde bajo hasta alto.
     * @param bajo el limite inferior.
     * @param alto el limite superior
     */
    public static void recorrex(ArbolBinarioBusqueda<Ciudad>.NodoBinario<Ciudad> raiz, int bajo, int alto){
	if (raiz == null)
	    return;
	if (raiz.getElemento().coorx >= bajo && raiz.getElemento().coorx <= alto){
	    //recorre.add(raiz.getElemento());	    //lo añade a la lista
	    System.out.println(raiz.getElemento()+"\n"); 
	}
	if(raiz.hayIzquierdo())
	    recorrex(raiz.getIzquierdo(), bajo, alto); //el metodo
	if(raiz.hayDerecho())
	    recorrex(raiz.getDerecho(), bajo, alto); //el metodo
    }

     public static void recorrey(ArbolBinarioBusqueda<Ciudad>.NodoBinario<Ciudad> raiz, int bajo, int alto){
	if (raiz == null)
	    return;
	if (raiz.getElemento().coory >= bajo && raiz.getElemento().coory <= alto){
	    //recorre.add(raiz.getElemento());//lo añade a la lista
	    System.out.println(raiz.getElemento()+"\n"); //no c porque da null, así que mejor lo imprimo gg
	}
	if(raiz.hayIzquierdo())
	    recorrex(raiz.getIzquierdo(), bajo, alto); //el metodo
	if(raiz.hayDerecho())
	    recorrex(raiz.getDerecho(), bajo, alto); //el metodo
    }
    
    
    public static void main(String[] args)throws ParserConfigurationException, SAXException, IOException, DOMException{
	//lector y escritor
	LectorDom lector = new LectorDom();
	EscritorJdom escritor = new EscritorJdom();
	
	Scanner sc = new Scanner(System.in);
	String menu= null; //con un String para no estar verificando si es un numero o no

	
	//DESCOMENTAR estas lineas por si quieres ciudades ya agregadas

	/*	Ciudad c1 = new Ciudad("Asgard","Noruega?", 10.4, 11.3);
	Ciudad c2 = new Ciudad("Titirilquén", "Perú", 15.3, 9.8);
	Ciudad c3 = new Ciudad("Gasmoxia", "Espacio", 21.9, 20.0);
	Ciudad c4 = new Ciudad("Quien sabe", "No sé cual", 25, 5.2);
	direc.insert(c1, c1.getNombre());
	direc.insert(c2, c2.getNombre());
	direc.insert(c3, c3.getNombre());
	direc.insert(c4, c4.getNombre());
	escribe.add(c1);
	escribe.add(c2);
	escribe.add(c3);
	escribe.add(c4);
	try{
	    escritor.escribe("Ciudades.xml", escribe);
	} catch(Exception e){
	    System.out.println("Ocurrió un error en la escritura del archivo XML");
	    }*/
	
	
	
	// Aqui empieza el main
	
	do{
    	System.out.println("\nDirectorio de ciudades, ¿Qué desea hacer?\n0.-Salir(Esto guarda/escribe automáticamente el XML)\n1.-Agregar una ciudad\n2.-Eliminar una ciudad\n3.-Mostrar una ciudad en específico\n4.-Determinar ciudades en cierto rango\n5.-Ver direcotrio como Árbol.\n6.-Ver directorio como Lista(XML)"); 
	
	/*	//Estos BLOQUES son para verificar que sea un numero
	while(!sc.hasNextInt()){
	    System.out.println("No es un número");
	    sc.next();
	}*/
	menu = sc.nextLine();
	
	switch(menu){
	case "1":
	    
	    System.out.println("Ingrese nombre de la ciudad");
	    String nombre = sc.nextLine();
	    System.out.println("Estado donde se encuentra a ciudad");
	    String estado = sc.nextLine();
	    System.out.println("Las coordenadas \"x\" ");
	    
	    while(!sc.hasNextInt()){
		System.out.println("No es un número");
		sc.next();
	    }
	    
	    int coorx = sc.nextInt();
	    
	    System.out.println("Las coordenasas \"y\" ");
	    while(!sc.hasNextInt()){
		System.out.println("No es un número");
		sc.next();
	    }
	    
	    int coory = sc.nextInt();
	    Ciudad nueva = new Ciudad(nombre, estado, coorx, coory); //crea ciudad
	    direc.insert(nueva, nueva.getNombre()); //agrego al arbol
	    System.out.println("Ciudad agregada");
	    sc.nextLine();
	    System.out.println(nueva);//imprime la ciudad agregada
	    
	    escribe.add(nueva);//agregar a la lista que escribe
	    try{
		escritor.escribe("Ciudades.xml", escribe);
	    } catch(Exception e){
		System.out.println("Ocurrió un error en la escritura del archivo XML");
	    }
	    
	    break;

	    
	case "2":
	    System.out.println("Inserte el nombre de la ciudad a eliminar");
	    String elim = sc.nextLine();
	    Ciudad t = direc.delete(elim); //se elimina del arbol
	    if (t!=null){//es porque la encontró
		System.out.println("Ciudad "+t.getNombre()+" eliminada");
		escribe.remove(t); //lo elimina de la lista también
	    }
	    
	    else System.out.println("La ciudad "+elim+" no se encuentra en el directorio");
	    //guarda cualquier cambio
	    try{
		escritor.escribe("Ciudades.xml", escribe);
	    } catch(Exception e){
		System.out.println("Ocurrió un error en la escritura del archivo XML");
	    }
	    
	    break;
	    
	case "3":
	    System.out.println("Ingrese nombre de la ciudad a buscar");
	    String busca = sc.nextLine();
	    Ciudad r = direc.retrieve(busca);
	    if(r!=null)//la encontró
		System.out.println("Ciudad "+r.getNombre()+" encontrada"+"\n"+r);
	    else System.out.println("La ciudad "+busca+ " no se encuentra en el directorio");//no la encontró ahi dice xd
	    break;

	case "4":
	    System.out.println("Buscar por coordenadas: \"x\", \"y\" ");
	    String coor = sc.next();
	    if (coor.equals("x") || coor.equals("X")){
		System.out.println("Inserte un limite inferior");
		while(!sc.hasNextInt()){
		    System.out.println("No es un número");
		    sc.next();
		}
		int infe = sc.nextInt();
		System.out.println("Límite superior");
		while(!sc.hasNextInt()){
		    System.out.println("No es un número");
		    sc.next();
		}
		int supe = sc.nextInt();
		recorrex(direc.getRaiz(), infe, supe);

		sc.nextLine();
	    }
	    
	    else if (coor.equals("y") || coor.equals("Y")){
	    	System.out.println("Inserte un limite inferior");
		while(!sc.hasNextInt()){
		    System.out.println("No es un número");
		    sc.next();
		}
		int infe = sc.nextInt();
		System.out.println("Límite superior");
		while(!sc.hasNextInt()){
		    System.out.println("No es un número");
		    sc.next();
		}
		int supe = sc.nextInt();
		recorrey(direc.getRaiz(), infe, supe);

		sc.nextLine();
	    }

	    else{
		System.out.println("Coordenadas inexistentes, ingrese \"x\" o \"y\""); //si no es x o y
		sc.nextLine();
	    }
	    
	    break;

	case "5":
	    if (direc!=null)
		System.out.println(direc);
	    else  System.out.println("Directorio vacìo");
	    break;

	case "6":
	    lector.lee("Ciudades.xml");
	    break;

	case "0":
	    try{
		escritor.escribe("Ciudades.xml", escribe);
		} catch(Exception e){
		    System.out.println("Ocurrió un error en la escritura del archivo XML");
		}
		System.out.println("\nArchivo guardaddo como \"Ciudades.xml\" ");
	    System.out.println("Programa terminado");
	    return; //pa salir el ciclo
	    
	default:
	    System.out.println("Opicón inválida");
	    continue;
	}

	}while(true);
	
    }
	
}

