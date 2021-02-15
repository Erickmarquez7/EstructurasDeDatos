//Erick Bernal Márquez		317042522
//Practica 5:xml		dic/2020

package fciencias.edatos.practica05;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.DOMException;
import javax.xml.parsers.ParserConfigurationException;

import java.util.ArrayList;
import java.util.Scanner;

public class Autos{
    //atributos
    String marca;
    
    String modelo;
    
    String placas=""; //porqueson opcionales
    
    String anio="";
    
    String nombre;
    
    String paterno;
    
    String materno;

    String opinion;

    static ArrayList<Autos> lista = new ArrayList<>();//lista para escribir
    
    //constructor

    public Autos(String marca, String modelo, String nombre, String paterno, String materno, String placas, String anio){
    	this.marca=marca;
    	this.modelo=modelo;
    	this.nombre=nombre;
    	this.materno=materno;
    	this.paterno=paterno;
	this.placas=placas;
	this.anio=anio;
    }


    public String daOpinion(){
	return opinion;
    }

    public void opinion(String opinion){
	this.opinion=opinion;

    }


    
    public static void main(String[] args)throws ParserConfigurationException, SAXException, IOException, DOMException{
	//lector y escritor
	LectorDom lector = new LectorDom();
	EscritorJdom escritor = new EscritorJdom();

	//autos precargados, no sé de autos así que le pondré lo que sea xd
	//marca, modelo, nombre, paterno, materno, placas, anio
	
	Autos a1 = new Autos("Nissan", "bmw", "Erick", "Ber", "Mar", "7h1-182", "2991");
	a1.opinion("Hay mejores");
	
	Autos a2 = new Autos("Honda", "mustang", "Mel", "Vasco", "Risa", "hd2-21w", "2001");
	a2.opinion("Lo que dice Erick");

	//Autos a3 = new Autos("Nissan", "carroza", "Elisa", "Madero", "López", "", "1910");
	//a3.opinion("Pudo ser peor");

	lista.add(a1);
	//lista.add(a2);
	//lista.add(a3);
	//Descomenta estas lineas para agregar los Autos ya predefinidos
	
	Scanner sc = new Scanner(System.in);

	
	System.out.println("Eliga una opción\n1.-Leer xml\n2.-Escribir xml\n3.-Salir");

	do{
	    //Estos BLOQUES son para verificar que sea un numero
	    while(!sc.hasNextInt()){
		System.out.println("No es un número");
		sc.next();
	    }
	    
	    int elec = sc.nextInt();

	    switch(elec){
	    case 1:
		System.out.println("Selecciones el documento .xml que quiere leer\n1.-Automoviles\nOtro numero.-AutomovilesEscritos");
		while(!sc.hasNextInt()){
		    System.out.println("No es un número");
		    sc.next();
		}
		int elec1 = sc.nextInt();
		if (elec1 == 1) lector.lee("Automoviles.xml");
		else lector.lee("AutomovilesEscritos.xml");
		System.out.println("\nEliga una opción\n1.-Leer xml\n2.-Escribir xml\n3.-Salir");
		break;
		
	    case 2:
		System.out.println("¿Desea agregar un nuevo carro?\n1.-Sí\nOtro número.-No");
		while(!sc.hasNextInt()){
		    System.out.println("No es un número");
		    sc.next();
		}
		int elec2=sc.nextInt();
		if(elec2==1){
		    System.out.println("Indique la marca: ");
		    String marca = sc.next();
		    System.out.println("Indique el modelo: ");
		    String modelo = sc.next();
		    System.out.println("El nombre: ");
		    String nombre = sc.next();
		    System.out.println("Apellido paterno: ");
		    String paterno = sc.next();
		    System.out.println("Apellido materno ");
		    String materno = sc.next();
		    Autos nuevo = new Autos(marca, modelo, nombre, paterno, materno,"","");
		    System.out.println("Deje una opinión");
		    sc.nextLine();
		    String op = sc.nextLine();
		    nuevo.opinion(op);
		    lista.add(nuevo);
		}
		    
		try{
		    escritor.escribe("AutomovilesEscritos.xml", lista);
		} catch(Exception e){
		    System.out.println("Ocurrió un error en la escritura del archivo XML");
		}
		System.out.println("\nArchivo guardaddo como <<AutomovilesEScritos.xml>>");		
		System.out.println("\nEliga una opción\n1.-Leer xml\n2.-Escribir xml\n3.-Salir");
		
		break;
	    case 3:
		System.out.println("Adios");
		return;
	    default:
		System.out.println("\nOpción inválida, eliga de nuevo");
		continue;
		
	    }

	}while(true);
	
    }
    
}

