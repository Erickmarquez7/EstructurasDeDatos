//Erick Bernal Márquez		317042522
//Practica 6:arboles		enero/2021
//Feliz año
package fciencias.edatos.practica06;

public class Ciudad{
    
    /** Nombre de la ciudad.
     */
    String nombre;

    /** Estado el estado de la ciudad.
     */
    String estado;

    /** Coorx las coordenadas x de la ciudad.
     */
    double coorx;

    /** Coory las coordenadas y de la ciudad.
     */
    double coory;
    
    /** @param Nombre el nombre de la ciudad,
     * @param Estado el estado de la república donde se encuentra,
     * @param coorx las coordenadas x de la ciudad,
     * @param coory las coordenadas y de la ciudad,
     */
    public Ciudad(String nombre, String estado, double coorx, double coory){ //en el toString pongo la coma
	this.nombre = nombre;
	this.estado = estado;
	this.coorx= coorx;
	this.coory = coory;
    }

    /** @return nombre el nombre de la ciudad.
     */
    public String getNombre(){
	return nombre;
    }

    /** @return ciudad el nombre de la ciudad.
     */
    public String getEstado(){
	return estado;
    }

    /** @return coorx las coordenadas x de la ciudad.
     */
    public double getCoorx(){
	return coorx;
    }

    /** @return coory las coordenadas y de la ciudad.
     */
    public double getCoory(){
	return coory;
    }

    /** @return coor las cordenadas "x" y "y" de la ciudad en formato.
     */
    public String getCoor(){
	String coor = ""+ getCoorx()+ ", "+getCoory();
	return coor;
    }

    /** @return res la represantación en cadena del objeto.
     */
    public String toString(){
	String res = "Ciudad      : " + getNombre()+
	             "\nEstado      : " + getEstado()+
	             "\nCoordenadas : " + getCoor();
	return res;
	
    }
     
}
