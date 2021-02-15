//Erick Bernal Márquez        317042522
//Practica 4                  Nov/2020

package fciencias.edatos.practica04;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Mesa implements Serializable{
//clase que representa una mesa

    String nombre; //el identificador de la mesa    
    int comensales; //los clientes que tiene la mesa de la mesa
    int capacidad; //la capacidad de la mesa
    boolean dispo = true; //para saber si la mesa está ocupada
    
    //constructor xd
    public Mesa(String nombre){
	this.nombre = nombre;
	//this.comensales = comensales;
    }
    
    //tipicos métodos
    public String getNombre(){
	return nombre;
    }
    
    public void setNombre(String nombre){
	this.nombre = nombre;
    }
    
    public int getCapacidad(){
    	return capacidad;
    }
    
    public int getComensales(){
    	return comensales;
    }
    
    
    public void setComensales(int comensales){
    	this.comensales = comensales;
    }
    
    //La mesa está disponible si no tiene comensales
    public boolean getDispo(){
    	return comensales == 0;
    }
    
    
    //el toString
    public String toString(){
	String cadena = String.format("Nombre      : %s\n" +
				       "Comensales  : %s\n"+
				       "Capacidad   : %s\n",
				      nombre, comensales, capacidad);
	return cadena;
    }
    
    /** Nombre el noombre que se le pondrá al archivo.
     * Empleado el empleado a serializar.
     */
    public static void serializa(String nombre, Mesa m) throws IOException{
	FileOutputStream file = new FileOutputStream(nombre);
	ObjectOutputStream output = new ObjectOutputStream(file);
	output.writeObject(m);
	output.close();
    }
    
    /** Empleado a deserializar.
     */
    public static Mesa deserializa(String nombre) throws IOException, ClassNotFoundException{
	FileInputStream file = new FileInputStream(nombre);
	ObjectInputStream input = new ObjectInputStream(file);
	Mesa m;
	m = (Mesa) input.readObject();
	input.close();
	return m;
    }
}
