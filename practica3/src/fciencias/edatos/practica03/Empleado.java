//Erick Bernal Márquez        317042522
//Practica 3                  Nov/2020

package fciencias.edatos.practica03;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Empleado implements Serializable{

    String nombre;
    int anio;
    String fecha;
    String rfc;
    int sueldo;
    String puesto;
    
    public Empleado(String nombre, int dia, int mes, int anio, int sueldo, String puesto) throws Exception{
	this.nombre = nombre;
	this.anio = anio;
	this.fecha = ""+dia+"/"+mes+"/"+anio;
	this.rfc = ""+nombre.charAt(0)+nombre.charAt(1)+dia+mes+anio;
	this.sueldo = sueldo;
	this.puesto = puesto;
	
    }
    
    
    public String getNombre(){
	return nombre;
    }
    
    public void setNombre(String nombre){
	this.nombre = nombre;
    }
    
    public int getAnio(){
    	return anio;
    }
    
    public void setAnio(int anio){
    	this.anio = anio;
    }
    
    public String getFecha(){
	return fecha;
    }
    
    public void setFecha(String fecha){
	this.fecha = fecha;
    }
    
    
    public String getRfc(){
	return rfc;
    }
    
    public void setRfc(String rfc){
	this.rfc = rfc;
    }
    
    public int getSueldo(){
	return sueldo;
    }
    
    public void setSueldo(int sueldo){
	this.sueldo = sueldo;
    }
    
    public String getPuesto(){
	return puesto;
    }
    
    public void setPuestoe(String puesto){
	this.puesto = puesto;
    }
    
    public String toString(){
	String cadena = String.format("Nombre  : %s\n" +
				      "Fecha   : %s\n" +
				      "RFC     : %s\n" +
				      "Sueldo  : %s\n" +
				      "puesto  : %s\n",
				      nombre, fecha, rfc, sueldo, puesto);
	return cadena;
    }
    
    /** Nombre el noombre que se le pondrá al archivo.
     * Empleado el empleado a serializar.
     */
    public static void serializa(String nombre, Empleado e) throws IOException{
	FileOutputStream file = new FileOutputStream(nombre);
	ObjectOutputStream output = new ObjectOutputStream(file);
	output.writeObject(e);
	output.close();
    }
    
    /** Empleado a deserializar.
     */
    public static Empleado deserializa(String nombre) throws IOException, ClassNotFoundException{
	FileInputStream file = new FileInputStream(nombre);
	ObjectInputStream input = new ObjectInputStream(file);
	Empleado e;
	e = (Empleado) input.readObject();
	input.close();
	return e;
    }
}
