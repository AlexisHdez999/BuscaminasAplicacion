package aplicacion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioControlador  {

    @FXML
    TextField columnas ;
    @FXML
    TextField filas ;
    @FXML TextField minas ;
    @FXML Button continuar ;

    private Stage escenario ;

    public void setEscenario(Stage escenario){
        this.escenario = escenario ;

    }
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


        private void mostrarAlerta(String mensaje){
            Alert a = new Alert(Alert.AlertType.WARNING) ;
            a.setContentText(mensaje);
            a.show() ;
        }

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
