package aplicacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase para el controlador de la vista de inicio
 * para definir el tamaño del tablero
 */
public class InicioControlador  {
    /*
    El número de columnas del tablero
     */
    @FXML
    TextField columnas ;
    /*
    El número de filas del tablero
     */
    @FXML
    TextField filas ;
    /*
    el número de minas del tablero
     */
    @FXML TextField minas ;
    /*
    boton para cargar la vista principal
     */
    @FXML Button continuar ;

    /*
    el escenario actual
     */
    private Stage escenario ;

    /**
     * Método para establecer el escenario actual
     * @param escenario el escenario actual
     */
    public void setEscenario(Stage escenario){
        this.escenario = escenario ;

    }

    //método que valida los campos de entrada y carga la vista principal
    @FXML private void continuar(ActionEvent event){
        int col, fil, mina ;
        try {
            col = Integer.parseInt(columnas.getText());
            fil = Integer.parseInt(filas.getText());
            mina = Integer.parseInt(minas.getText());
        } catch(Exception exception){
           mostrarAlerta("Ingrese un número en cada espacio, por favor");
           return ;
        }
        if((col >35) | (col <2)){
            mostrarAlerta("Ingrese un número válido de columnas, mínimo 2, máximo 35");
            return;
        }
        if((fil >16) || (fil < 2)){
            mostrarAlerta("Ingrese un número válido de filas, mínimo 2, máximo 16");
            return ;
        }
        if((mina >=(col*fil)) || (mina < 1)){
            mostrarAlerta("El número de minas debe ser mayor a 0 y menor al número de casillas");
            return ;
        }
      mostrarVistaBuscaminas(col, fil, mina) ;
    }

    //método que muestra una alerta
    private void mostrarAlerta(String mensaje){
        Alert a = new Alert(Alert.AlertType.WARNING) ;
        a.setContentText(mensaje);
        a.setTitle("Buscaminas");
        a.setHeaderText("");
        a.show() ;
    }


    //método que carga y muestra la vista principal
    private void mostrarVistaBuscaminas(int col, int fil, int mina) {
        Stage escenario2 = new Stage() ;
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("BuscaminasVista.fxml"));
        VBox root = null;
        try {
            root = (VBox) cargador.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        escenario2.setTitle("Buscaminas");
        Scene escena = new Scene(root) ;

        escenario2.setScene(escena);
        BucaminasVistaControlador controladorBuscaminas = cargador.getController() ;
        controladorBuscaminas.setEscenario(escenario2);
        controladorBuscaminas.modificarVista(col, fil, mina);
        escenario2.setResizable(false);
        escenario2.show();
        escenario.close();
    }

}
