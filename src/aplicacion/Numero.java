package aplicacion ;
/*clase para indicar el número de minas que hay alrededor de una posicion en el tablero
 *@author Alexis Hernández Magaña
 *
 */ 

public class Numero extends Posicion {

    private int numero ;

    public Numero (){
	numero = 0 ;

    }


/*Método para aumentar el número de una posicion de acuerdo al número de minas
 *@author Alexis Hernández Magaña
 *
 */ 
    public void aumentarNumero(){
	numero = ++numero ;

    }

    
    public Numero(int num){
	numero = num ;

    }

    public void asignarNumero(int n ){

	numero = n ;
    }

    
	
    public String toString(){

	return Integer.toString(numero) ;

    }

}
