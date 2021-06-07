package aplicacion;

 /*clase para interactuar con el usuario
 *@author Alexis Hernández Magaña
 *
 */ 
import java.util.* ;

public class Menu {

 /*Método principal menu
 *@author Alexis Hernández Magaña
 *
 */ 

    public static void main (String[]  args){

	Scanner scan = new Scanner(System.in);
        int [] arreglo ;
	int respuesta, respuesta2, respuesta3 ;
	respuesta = respuesta2 = respuesta3 = 0 ;
	boolean bandera2 = true ;
	Juego table = null ;
	
	do {
	try {
        System.out.println("¿De qué tamaño desea su tablero (el número mínimo será 8x8) y cuántas minas tendrá?");

	while (respuesta < 8){
        System.out.println("Ingrese el número de filas ");
        respuesta = scan.nextInt() ;
	if (respuesta < 8){
        System.out.println("Ingrese un número válido");
	}
	}
       
	
	while (respuesta2 < 8){
	System.out.println("Ingrese el número de columnas");
        respuesta2 = scan.nextInt();
	if (respuesta2 < 8){
        System.out.println("Ingrese un número válido");
	}
	}
		
	
	while ( (respuesta3 > (respuesta*respuesta2)) || (respuesta3 <= 0)){
       
	System.out.println("Ingrese el número de minas");
	respuesta3 = scan.nextInt();
	
	if ((respuesta3 > (respuesta*respuesta2)) || (respuesta3 < 0)){
	System.out.println("Ingrese un número válido");
	}
	}
	
        table = new Juego(respuesta, respuesta2, respuesta3) ;
	table.generarPartida();
	bandera2 = false ;
         } catch (Exception e){
	scan.next();
	System.out.println("Ingrese números enteros, porfavor");
        
	}
	}while (bandera2 == true) ;
	

	
	boolean bandera = true ;
	boolean bandera3 = true ;
	boolean bandera4 = true ;
	
       do {
       System.out.println("¿Qué desea realizar?");
       System.out.println("Opciones: ");
       System.out.println("1.-Mostrar una casilla");
       System.out.println("2.-Marcar una casilla donde creo que hay una mina");
       System.out.println("3.-Desmarcar una casilla");
       System.out.println("4.-Salir") ;
	   respuesta = scan.nextInt();
       
       switch(respuesta){
       case 1 :
	   do {
	       try{
		   arreglo = pedirCoordenadas();
		   if ((arreglo[0] > table.obtenerTableroPantalla().length) || (arreglo[1] > table.obtenerTableroPantalla()[0].length) ){
		 throw new InputMismatchException("");
		   }
		   table.mostrarCasilla(arreglo[0], arreglo[1]);
		   bandera3 = false ;
	      } catch(Exception e ){
		   System.out.println("Ingrese un número válido") ;
	       }
	   } while (bandera3 == true );
	   

	   break ;
       case 2 :
	   do {
	       try {
	     System.out.println("¿Cuál casilla desea marcar?");
	     arreglo = pedirCoordenadas() ;
	     if ((arreglo[0] > table.obtenerTableroPantalla().length) || (arreglo[1] > table.obtenerTableroPantalla()[0].length) ){
		 throw new InputMismatchException("");
	     }
	     table.marcarCasilla(arreglo[0], arreglo[1]);
	     bandera4 = false ;
	       } catch(Exception e ){
	    System.out.println("Ingrese un número válido") ;
	       }
	   } while (bandera4 == true );
	   
	     break ;
	     
       case 3 :
	   
	     System.out.println("¿Cuál casilla desea desmarcar?");
	     arreglo = pedirCoordenadas() ;
	     table.desmarcarCasilla(arreglo[0], arreglo[1]);
	     break ;
	   
       case 4:
	   bandera = false ;
	   break;
	   
       default :
	     System.out.println("Opción no válida");
	     break ;
       }
       } while (bandera == true) ;



    }

    
 /*Método para pedir coordenadas de una casilla al usuario
 *@author Alexis Hernández Magaña
 *@return int [] - arreglo con las coordenadas necesitadas
 */ 

    
    public static int[] pedirCoordenadas(){
	Scanner scan = new Scanner(System.in);
	int respuesta = 0 ;
	int respuesta2 = 0 ;
	int[] arreglo = null ;
	boolean bandera = false ;
	do {
	try {
	System.out.println("Ingrese la primera coordenada. ¿En qué fila se encuentra la casilla que desea abrir?(si desea la primera fila deberá marcar 0) ");
        respuesta = scan.nextInt() ;

	System.out.println("Ingrese la segunda coordenada. ¿En qué columna se encuentra la casilla que desea abrir? (si desea la primer columna deberá marcar 0)");
	   respuesta2 = scan.nextInt();
	    arreglo = new int[2];
	    arreglo [0] = respuesta ;
	    arreglo [1] = respuesta2 ;
	    
	   bandera = true;
	} catch(Exception e ){
	    System.out.println("Ingrese un número válido") ;
	}
	} while (bandera = false );

    return arreglo ;
}
}
