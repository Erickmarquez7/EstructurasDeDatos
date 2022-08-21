//Erick Bernal Márquez		317042522
//Practica Rep: Pilas		enero/2021
package fciencias.edatos.practica08;
import java.util.EmptyStackException;
import java.util.Scanner;

import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class Bett{
//los tres metodos esos

    static PilaArreglo<String> pila = new PilaArreglo<>();

    
    //pa meter una cadena
    public static void pushString(String s){
	//ponemos la cadena al revés para que al sacar la saquemos bien
	for(int i = s.length()-1; i>=0; i--){
	    String c = ""+s.charAt(i);
	    pila.push(c);
	}
	String aux = ""+s.length();
	pila.push(aux);
	System.out.println(pila);
    }

    
    //pa saber el último
    public static String topString(){
	Integer n = Integer.parseInt(pila.top());
	return "Se requieren "+n+" operaciones para extraer la siguiente cadena";
    }

    //pa extraer la cadena
    public static String popString(){
	//primero sacamos el entero k
	Integer n = Integer.parseInt(pila.pop());
	//pa saber cuantas veces
	String s = "";
	for(int i = 0; i<n; i++)
	    s+=pila.pop();
	return s;
    }

    
    /*Na mas el escribe porque el pdf dice 
      Las cadenas a insertar en la pila deben almacenarse en un archivo XML o de texto. Al final de la
      ejecución, los cambios deben reflejarse en el archivo.
      Mas que no que debe leerse, 
      Y también porque decidí ser feliz
     */
    public static void escribir(){
	BufferedWriter bw = null;
	try{
	    FileWriter w = new FileWriter("pila.txt");
	    bw = new BufferedWriter(w);
	    Object[] aux = pila.getArreglo();
	    for(int i = 0; i <255; i++){
		bw.write(""+aux[i]+"\n");
	    }
	    System.out.println("Archivo guardado");
	    
	}catch(IOException e){
	    System.out.println("Algo salió mal");
	}finally{
	    try{
		bw.close();
	    }catch(Exception e){
		System.out.println();
	    }
	}
	
    }
    
    //
    public static void leer(){
	
    }
    
    
    
    public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	
	String menu;
	
	do{
	    
	    System.out.println("\nPila ¿Qué quiere?\n0.-Salir\n1.-Insertar cadena\n2.-Ver la longitud de la ùltima cadena\n3.-Extraer última cadena\n4.-Ver pila ");
	    
	    menu = sc.nextLine();
	    
	    switch(menu){
	    case "1":
		System.out.println("Escriba cadena a insetar");
		String cadena = sc.next();
		Bett.pushString(cadena);
		//	System.out.println(Bett.imprimir());
		escribir();
		sc.nextLine();
		
		break;
		
	    case "2":
		System.out.println(Bett.topString());
		break;
		
	    case "3":
		try{
		    System.out.println(Bett.popString());
		    escribir();
		}catch(EmptyStackException ese){
		    System.out.println("La pila es vacia");
		}
		
		break;

	    case "4":
		System.out.println(pila);
		break;
		
	    case "0": //pa salir del ciclo
		return;
		
	    default: //pa seguir hasta que se le pase un 0
		System.out.println("Opción no valida");
		continue;
	    }
	    
	}while(true);
	
    }
    
}
