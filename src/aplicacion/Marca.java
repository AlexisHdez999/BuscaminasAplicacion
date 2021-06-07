package aplicacion ;

/*clase para elaborar objetos que marquen las casillas que no se desean abrir
 *@author Alexis Hernández Magaña
 *
 */ 

public class Marca extends Posicion {

    String marca  ;

 /*Método constructor de una marca
 *@author Alexis Hernández Magaña
 *
 */ 
    public Marca(){
	marca = "X";
    }

    public void aumentarNumero(){
	marca = marca ;
    }


  
    public String toString (){
	return marca ;

    }
    
}
