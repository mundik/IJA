// autor: Tereza Buchníčková        //
// login: xbuchn00                  //
//      //

package ija.uml;

import java.io.IOException;

import ija.uml.items.ClassDiagram;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.shape.Line;

public class ClassDiagramUI extends ScrollPane {

    ClassDiagram classDiagram;
    /* List<UMLClass> classes; */
    double x_pos = 10; 
    double y_pos = 5; 
    boolean first_class = true;

    @FXML
    private Pane center_pane;
    
    public ClassDiagramUI(ClassDiagram classDiagram) { 
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("center_pane_ui.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //this.classDiagram = classDiagram; 

        this.addClass(); //pridani tridy do diagramu
    }
    
    @FXML
    public void addClass() {
        UMLClassUI uml_class = new UMLClassUI();
        uml_class.setTitle("Název třídy ");
        uml_class.setLayoutX(x_pos);
        uml_class.setLayoutY(y_pos);
        center_pane.getChildren().add(uml_class);
        if (!first_class) {
            Line line = new Line(x_pos, 80, x_pos - 20, 80);
            this.getChildren().add(line);
        }
       
        x_pos += 120;
        first_class = false;
    } 
}
