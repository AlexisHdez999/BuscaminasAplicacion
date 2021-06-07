package aplicacion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
      int col = Integer.parseInt(columnas.getText())   ;
      int fil = Integer.parseInt(filas.getText()) ;
      int mina = Integer.parseInt(minas.getText());
      mostrarVistaBuscaminas(col, fil, mina) ;
    }

    private void mostrarVistaBuscaminas(int col, int fil, int mina) {
        Stage escenario2 = new Stage() ;
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("BuscaminasVista.fxml"));
        GridPane root = null;
        try {
            root = (GridPane) cargador.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        escenario2.setTitle("Buscaminas");
        Scene escena = new Scene(root) ;

        escenario2.setScene(escena);
        BucaminasVistaControlador controladorBuscaminas = cargador.getController() ;
        controladorBuscaminas.modificarVista(col, fil, mina);
        escenario2.show();

    }

}
