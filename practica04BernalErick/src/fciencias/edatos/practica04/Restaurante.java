//Erick Bernal Márquez        317042522
//Practica 4                  Nov/2020

package fciencias.edatos.practica04;
/*import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;*/
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.Random;

public class Restaurante {

    //EL Número de mesas
    static int nMesas;
    //arreglo de mesas
    static Mesa[] mesas;
    //cola para los clientes 
    static Cola<Cliente> clientes = new Cola<Cliente>();
    
    //dia
    static int dia;
    
    static Random rn = new Random();

    //lista auxiliar para guardar la bitacora atendidos
    static Cola<Cliente> auxCola= new Cola<Cliente>();

    //auxiliar paea guardar los clientes no atendidos
    static Cola<Cliente> auxNoCola = new Cola<Cliente>();

    
    /* Muestra las mesas.
     */
    public static void muestraMesas(){
	for(int m = 0; m<nMesas; m++)
	    System.out.println(mesas[m]);
    }

    
    /* Ordena el areglo
     */
    public static void selectionSort(Mesa[] arreglo) {
	//la primera posicion
	int m;
	for(int i = 0; i<arreglo.length; i++){
	    //la siguiente
            m = i;
            for(int j = i+1; j<arreglo.length; j++){
		if(arreglo[j].getCapacidad()<arreglo[m].getCapacidad())
		    m = j;
            }
            intercambia(arreglo, i, m);
	}
    }
    
    private static void intercambia(Mesa[] arreglo, int pos1, int pos2){
	//temporal
        Mesa mesaTemp = arreglo[pos1];
        arreglo[pos1] = arreglo[pos2];
        arreglo[pos2] = mesaTemp;
    }

    private static int maximo(){
	int maximo = 0;
	for(int i = 0; i<nMesas; i++){
	    if (mesas[i].getCapacidad()>maximo)
		maximo=mesas[i].getCapacidad();
	}
	return maximo;
    }


    /*Atiende a los clientes en las mesas
     */
    public static void atiende(){

	for(int i=0; i<nMesas && !clientes.isEmpty();i++){
	    //el maximo numero de asientos que todas las mesas disponen
	    int maximo=maximo();
	    Cliente espera = clientes.first();
	    //si las personas del acompañante son menores que el acompañante entonces será antendido, si son mayores pues no xd
	    if(espera.getPersonas()<=maximo){
		//si está disponible y las personas sean menores o guales a la capacidad
		if(mesas[i].getDispo() && espera.getPersonas()<=mesas[i].getCapacidad()){
		    //se quita en la cola
		    clientes.dequeue();
		    //se asigna a la mesa
		    mesas[i].setComensales(espera.getPersonas());
		    mesas[i].setNombre(espera.getNombre());
		    //y solito se pone ocupada
		    //el tiempo de espera
		    espera.tiempo=1+rn.nextInt(10);
		    
		    System.out.println("La disposición de las mesas fue: ");
		    muestraMesas();

		    try{
		    Thread hilo = new Thread();
		    hilo.sleep(espera.getTiempo()*1000);
		    }catch(Exception e){}
		    
		    mesas[i].setComensales(0);
		    //   el else es nada mas para que se lo salte y no haga nada
		    if (i==nMesas-1) i = 0;
		    auxCola.enqueue(espera);
		}else {

		}//fue atendido y verificò la dispo y las personas
		if(i == nMesas) i=0;
	    }else {
		System.out.println("Cliente "+espera.getNombre()+" fue descartado por exceder los asientos.");
		auxNoCola.enqueue(espera);
		if(i==nMesas-1) i = 0;
		clientes.dequeue();
	    }
	    if(i==nMesas-1) i= 0;
	    //no fue atendido, lo quita de la cola
	    
	}
    }
    

    /*Para guardar el dìa
     */
    public static void guarda(){
	dia++;
	BufferedWriter bw = null;
	try{
	    FileWriter w = new FileWriter("dias.txt");
	    bw = new BufferedWriter(w);
	    bw.write(""+dia);
	}catch(Exception e){
	    System.out.println("Algo salió mal");
	}finally{
	    try{
		bw.close();
	    }catch(Exception e){
		System.out.println();
	    }
	}
    }

         
    public static void guarda1(){
	BufferedWriter bw1 = null;
	try{
	    FileWriter w1 = new FileWriter("dia "+dia);
	    bw1 = new BufferedWriter(w1);
	    Cliente c = auxCola.first();
	    while(c!=null)
		bw1.write("El cliente: "+auxCola.first().getNombre() + " fue atendido y se tardó "+auxCola.dequeue().getTiempo()+" segundos");
	    Cliente nc = auxNoCola.first();
	    while(nc!=null)
		bw1.write("El cliente: "+auxNoCola.dequeue()+" no fue atendido por exceso de personas");
	}catch(Exception e){
	    System.out.println("Algo salió mal");
	}finally{
	    try{
		bw1.close();
	    }catch(Exception e){
		System.out.println();
	    }
	}
    }

    /*Para leer el dìa
     */
    public static int lee(){
	BufferedReader br = null;
	try{
	    FileReader r = new FileReader("dias.txt");
	    br = new BufferedReader(r);
	    
	    String linea = br.readLine();
	    dia = Integer.parseInt(linea);
	    //siguiente linea
	    linea = br.readLine();

	    System.out.println("Días abiertos: " +dia);
	    
	}catch(FileNotFoundException flne){
	    System.out.println("Primer dìa abierto");
	}catch(Exception e){
	    System.out.println("");
	}finally{
	    try{
	    br.close();
	    }catch(Exception cmodin){
	    }
	}
	return dia;
    }
    
    
    public static void main(String[] args){
	
	Scanner sc = new Scanner(System.in);

	//Incio
   	System.out.println("Bienvenido al restaurante Chefsito\nPara empezar seleccione el número de mesas. Las mesas se van a ordenar de menor a mayor para aprovechar al máximo su capacidad.");
	dia = lee();
   	//Estos BLOQUES son para verificar que sea un numero
	while(!sc.hasNextInt()){
	    System.out.println("No es un número");
	    sc.next();
	}
	
	//Declaración de mesas
	nMesas = sc.nextInt();
	mesas = new Mesa[nMesas];

	//creacion y asignación de mesas
	for (int i = 0; i<nMesas; i++){
	    mesas[i] = new Mesa("Mesa "+ i);
	}
	
	System.out.println("Se registraron "+ nMesas +" mesas.\nSeleccione la capacidad de las mesas\n");
	
	//Asiganción de asientos
	for(int i=0; i<nMesas; i++){
	    System.out.println("Lugares para la mesa "+ ""+i+".");
	    
	    //BLOQUE
	    while(!sc.hasNextInt()){
		System.out.println("No es un número");
		sc.next();
	    }
	    mesas[i].capacidad = sc.nextInt();        
	}
	
	
	System.out.println("La disposición de las mesas es la siguiente: ");
	
	//oredenar arreglo
	selectionSort(mesas);
	
	for(int i = 0; i<nMesas; i++){
	    System.out.println(mesas[i].getNombre() + " tiene una capacidad de "+mesas[i].getCapacidad());
	}
	//hasta aqui el inicio de las mesas
	
	//inicio de clientes
	System.out.println("\n¿Cuantos clientes son en total?");
	
	//BLOQUE
	while(!sc.hasNextInt()){
	    System.out.println("No es un número");
	    sc.next();
	}
	
	int nClientes=sc.nextInt();
	//sc.nextLine();
	//crea clientes
	for(int c=0; c<nClientes;c++){
	    System.out.println("¿Cuál es el nombre del cliente "+c+"?");
	    String nombre = sc.next();
	    System.out.println("¿Cuántos son sus acompañantes en total?");
	    //BLOQUE
	    while(!sc.hasNextInt()){
		System.out.println("No es un número");
		sc.next();
	    }
	    
	    int personas = sc.nextInt();
	    
	    Cliente nuevo = new Cliente(nombre, personas);
	    //tiempo que se va a tardar cada personas
	    int tiempo = 1+rn.nextInt(10);
	    //tiempo total
	    nuevo.setTiempo(tiempo*nuevo.getPersonas());
	    clientes.enqueue(nuevo);

	}

	System.out.println("La cola es la siguiente: ");
	clientes.muestra();
	
	// para atender a los clientes
	atiende();
	guarda();
	guarda1();

    }
}

