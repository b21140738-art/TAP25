package clases.equipo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class calculadora extends Application {
    @Override
    public void start(Stage stage) {
        Label title = new Label("Calculadora");
//Definimos nuestros TextField
        TextField num1 = new TextField();
        TextField num2 = new TextField();
//Agregamos leyenda dentro del TextField
        num1.setPromptText("Digita el primer numero");
        num2.setPromptText("Digita el número dos");
//Marcamos una etiqueta de texto para nuestro resultado
        Label resultLabel = new Label();

//RadioButton para cada tipo de operación
        RadioButton suma = new RadioButton("Sumar");
        RadioButton resta = new RadioButton("Restar");
        RadioButton mult = new RadioButton("Multiplicar");
        RadioButton div = new RadioButton("Dividir");
//Hace un grupo de RadioButton para poder usar uno a la vez
        ToggleGroup group = new ToggleGroup();
        suma.setToggleGroup(group);
        resta.setToggleGroup(group);
        mult.setToggleGroup(group);
        div.setToggleGroup(group);

        suma.setSelected(true);

        Button botonCalcular = new Button("Calcular");

        botonCalcular.setOnAction(e -> {
            try {
                int n1 = Integer.parseInt(num1.getText());
                int n2 = Integer.parseInt(num2.getText());
                int res = 0;
                if (suma.isSelected()) {
                    res = n1 + n2;
                }


                resultLabel.setText("Resultado: " + res);
            } catch (Exception ex) {
                resultLabel.setText("Error: Seleccione números válidos");
            }
        });

        VBox panel = new VBox(
                title, num1, num2,
                new Label("Elige operación:"),
                suma, resta, mult, div,
                botonCalcular, resultLabel
        );
//Espacio entre cada nodo
        panel.setSpacing(5);

        Scene scene = new Scene(panel, 300, 250);
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}