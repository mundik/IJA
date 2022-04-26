// autor: Tereza Buchníčková        //
// login: xbuchn00                  //
// konstruktor UML třídy            //

package ija.uml;

import java.io.IOException;
import java.util.List;

import ija.uml.items.UMLAttribute;
import ija.uml.items.UMLClass;
import ija.uml.items.UMLOperation;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class UMLClassUI extends VBox {

    @FXML
    private Button class_button;
    @FXML
    private ListView<String> attributes, operation;
    UMLClass umlClass;
    private double x_pos, y_pos;

    public UMLClassUI(UMLClass umlClass, double x_pos, double y_pos) {
        this.umlClass = umlClass;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("uml_class_ui.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initClass();
    }

    @FXML
    public void editClass() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("add_edit_ui.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Upravit třídu");
                stage.setScene(new Scene(loader.load(), 400, 400));
                stage.initModality(Modality.APPLICATION_MODAL);
                AddEditUI controller = loader.getController();
                controller.init(null, true, umlClass);
                stage.showAndWait();
                initClass();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void initClass() {
        class_button.setText(umlClass.getName());
        attributes.getItems().clear();
        operation.getItems().clear();
        for (UMLAttribute attr : umlClass.getAttributes()) {
            attributes.getItems().add(attr.toString());
        }
        for (UMLOperation meth : umlClass.getOperation()) {
            operation.getItems().add(meth.toString());
        }
    }
}