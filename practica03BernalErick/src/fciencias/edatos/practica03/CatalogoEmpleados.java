//Erick Bernal Márquez        317042522
//Practica 3                  Nov/2020
package fciencias.edatos.practica03;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CatalogoEmpleados{
    Lista<Empleado> empleados;
    
    public CatalogoEmpleados(){
	empleados = new Lista<Empleado>();
    }
    
    /** Muestra el catalogo de empleados
     */
    public void muestraEmpleados(){
    	empleados.muestra();
    }
    
    
    /** Agrega un empleado al catalogo de empleados.
     * @param empleado el empleado a agregar.
     */
    public void alta(Empleado e){
	empleados.add(0, e);
    }
    
    /** Elimina el empleado en la posción i.
     * @param i el indice del empleadp.
     */
    public void baja(int i){
	empleados.remove(i);
    }
    
    /** Regresa los empleados que concidan con el puesto.
     * y no sobre pasen algún sueldo.
     * @param puesto el puesto del empleado.
     * @param sueldo el sueldo del empleado.
     */
    public Lista<Empleado> puestos(String puesto, int sueldo){
	Lista<Empleado> copia = new Lista<>();
	int cont = 0;
	for(int i = 0; i<empleados.size(); i++){
	    if(empleados.get(i).puesto.equals(puesto)&&empleados.get(i).sueldo<=sueldo){
		copia.add(cont,empleados.get(i));
		cont++;
	    }
	}
	return copia;
    }
    
    
    /** Aumenta el sueldo a 1000 pesos a los empleados.
     * cuyo año sea mayor.
     * @param año el año solicitado.
     */
    public void aumenta(int anio){
	for(int i = 0; i<empleados.size(); i++){
	    if(empleados.get(i).anio>anio){
		empleados.get(i).sueldo+=1000;
	    }
	}
    }

    public void muestra(){
	if(empleados.isEmpty())
	    System.out.println("Lista vacia");
	else
	    empleados.muestra();
    }

    
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
    	CatalogoEmpleados cata = new CatalogoEmpleados();
	String menu = null;
	
	do{
	    System.out.println("\nCatálogo de empleados\nSeleccione que desea hacer.");
	    System.out.println("\n1.- Crear empleado\n2.- Agregar Empleado al catalogo\n3.- Eliminar empleado del catalogo\n4.- Encontrar empleados con puesto y sueldo solicitados\n5.- Aumentar 1000$ a empleados cuyo año sea mañor al dado\n0.- Salir");
	    menu = sc.nextLine();
	    //	    sc.nextLine();
	    switch(menu){
	    case "1":
		try{
		System.out.println("Ingrese nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Ingrese dia: ");
		int dia = sc.nextInt();
		System.out.println("Mes: ");
		int mes = sc.nextInt();
		System.out.println("Año: ");
		int anio = sc.nextInt();
		System.out.println("Sueldo: ");
		int sueldo = sc.nextInt();
	       	sc.nextLine();
		System.out.println("Puesto: ");
		String puesto = sc.nextLine();
		Empleado e = new Empleado(nombre, dia, mes, anio, sueldo, puesto);
		Empleado.serializa(nombre, e);
		System.out.println("Empleado guardado como "+nombre);
		}catch(Exception e){
		    System.out.println("Datos inválidos, fecha inválida");
		    
		}
		//sc.nextLine();
		break;
		
	    case "2":
		System.out.println("Escriba el nombre del empleado a agregar: ");
		String nombre = sc.nextLine();
		try{
		    Empleado e = Empleado.deserializa(nombre);
		    cata.alta(e);
		    System.out.println("Empleado agregado al catalogo\n");
		    cata.muestra();
		}catch(Exception e){
		    System.out.println("Archivo no encontrado");
		}
		//sc.nextLine();
		break;
		
	    case "3":
		System.out.println("Seleccione el indice del empleado que quiere eliminar: ");
		try{
		    int elim = sc.nextInt();
		    cata.baja(elim);
		    System.out.println("Empleado eliminado ):\n");
		    cata.muestra();
		}catch(Exception e){
		    System.out.println("No es un numero o índice fuera de rango");
		}
		sc.nextLine();
		break;
		
	    case "4":
		System.out.println("Escriba el puesto solicitado: ");
		String pues = sc.nextLine();
		System.out.println("Escriba sueldo solicitado");
		try{
		    int sueld = sc.nextInt();
		    Lista<Empleado> cop = cata.puestos(pues, sueld);
		    cop.muestra();
		}catch(Exception e){
		    System.out.println("No es un número");
		}
		sc.nextLine();
		break;
			
	    case "5":
		System.out.println("Ingrese año a buscar: (Mayor estricto)");
		try{
		    int anio=sc.nextInt();
		    cata.aumenta(anio);
		    System.out.println("Sueldo aumentado\n");
		    cata.muestra();
		    
		}
		catch(Exception e){
		    System.out.println("No es un año");
		}
		sc.nextLine();
		break;
		
	    case "0":
		System.out.println("Programa terminado");
		return;
	    default:
		System.out.println("Opción inválida1");
		continue;
	    }

	}while(true);
	    	
    }
    
}
