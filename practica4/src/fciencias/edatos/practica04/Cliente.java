//Erick Bernal Márquez        317042522
//Practica 4                  Nov/2020

package fciencias.edatos.practica04;
import java.io.Serializable;

public class Cliente implements Serializable{
    
    //nombre del cliente
    String nombre;
    
    //personas con el cliente incluyendo al mismo cliente
    int personas;
    
    //la cantidad de tiempo del cliente
    int tiempo;
    
    public Cliente(String nombre, int personas){
	this.nombre=nombre;
	this.personas=personas;
    }
    
    //Métodos get
    public String getNombre(){
	return nombre;
    }
    
    public int getPersonas(){
	return personas;
    }
    
    public int getTiempo(){
	return tiempo;
    }
    
    
    public void setTiempo(int tiempo){
	this.tiempo = tiempo;
    }
 
    public String toString(){
    	return "Nombre   : "+ nombre+
    	     "\nPersonas : "+personas+"\n";
    }   
}
