package aplicacion;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BucaminasVistaControlador {

    @FXML
    GridPane gridPane ;

    public void agregarAccion(){
        ObservableList<Node> hijos = gridPane.getChildren();
        for(Node n: hijos){
            Button b = (Button) n ;
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    b.setVisible(false);
                }
            });
        }
    }

    public void modificarVista(int col, int fil, int minas){
        Button boton = null ;
        for(int i=0 ; i < col ; i++){
            for(int j=0 ; j < fil ; j++){
                boton = new Button() ;
                gridPane.add(boton, i, j);
                boton.setText("");
            }
        }
        agregarAccion();
    }

}