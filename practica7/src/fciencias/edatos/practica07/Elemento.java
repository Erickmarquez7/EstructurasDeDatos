//Erick Bernal Márquez		317042522
//Practica 7:mapas		enero/2021

package fciencias.edatos.practica07;

public class Elemento{
    
    String nombre;
    String simbolo;
    Integer numero;
    Double masa;

    public Elemento(String nombre, String simbolo, Integer numero, Double masa){
	this.nombre=nombre;
	this.simbolo=simbolo;
	this.numero=numero;
	this.masa=masa;
    }
    
    public String getNombre(){
	return nombre;
	
    }
    
    public String getSimbolo(){
	return simbolo;
	
    }
    
    public Integer getNumero(){
	return numero;
	
    }
    
    public Double getMasa(){
	return masa;
	
    }
    
    public String toString(){
        return "Nombre : "+this.nombre+"\nSimbolo: "+this.simbolo+"\nNumero : "+this.numero+"\nMasa   : "+this.masa+"\n";
    }
    
}
