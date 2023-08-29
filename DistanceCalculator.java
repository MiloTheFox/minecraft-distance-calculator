/**
 * @author Sweet_Tooth11
 * @version v1.3, 08/29/2023 - 02:30AM GMT+1
 */

// Note to myself: Go to sleep

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DistanceCalculatorGUI extends Application {

    public void start(Stage primaryStage) {
        // Set the window icon
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        primaryStage.getIcons().add(icon);

        // Create a grid pane to hold the UI components
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: #d3d3d3;");

        Label pointALabel = createLabel("Point A (x y z):");
        TextField pointATextField = createTextField();
        Label pointBLabel = createLabel("Point B (x y z):");
        TextField pointBTextField = createTextField();

        // Add the labels and text fields to the grid pane
        grid.add(pointALabel, 0, 0);
        grid.add(pointATextField, 1, 0);
        grid.add(pointBLabel, 0, 1);
        grid.add(pointBTextField, 1, 1);

        // Create a label and buttons for choosing the distance calculation method
        Label methodLabel = createLabel("Distance Calculation Method:");
        Button euclideanButton = createButton("Euclidean");
        Button manhattanButton = createButton("Manhattan");

        // Add the label and buttons to the grid pane
        grid.add(methodLabel, 0, 2);
        grid.add(euclideanButton, 1, 2);
        grid.add(manhattanButton, 2, 2);

        // Create a label to display the result
        Label resultLabel = createLabel("");
        grid.add(resultLabel, 0, 3, 3, 1);

        // Set the action for the Euclidean button
        euclideanButton.setOnAction(event -> {
            // Read the coordinates from the text fields and validate them
            String[] pointACoordinates = pointATextField.getText().split(" ");
            String[] pointBCoordinates = pointBTextField.getText().split(" ");

            if (pointACoordinates.length != 3 || pointBCoordinates.length != 3) {
                resultLabel.setText("Invalid coordinates. Enter three numbers for each point!");
                resultLabel.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 14px;"); // Set text color to red
                return;
            }

            try {
                double x1 = Double.parseDouble(pointACoordinates[0]);
                double y1 = Double.parseDouble(pointACoordinates[1]);
                double z1 = Double.parseDouble(pointACoordinates[2]);
                double x2 = Double.parseDouble(pointBCoordinates[0]);
                double y2 = Double.parseDouble(pointBCoordinates[1]);
                double z2 = Double.parseDouble(pointBCoordinates[2]);

                // Calculate the Euclidean distance between points A and B
                double distance = calculateEuclideanDistance(x1, y1, z1, x2, y2, z2);
                String formattedDistance = distance % 1 == 0 ? String.valueOf((int) distance) : String.valueOf(distance);
                // Update the result label with the calculated distance
                resultLabel.setText("Euclidean distance: ~" + formattedDistance + " Blocks");
                resultLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px;");
                return;
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid coordinates. Enter numeric values only!");
                resultLabel.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 14px;");
                return;
            }
        });

        // Set the action for the Manhattan button
        manhattanButton.setOnAction(event -> {
            // Read the coordinates from the text fields and validate them
            String[] pointACoordinates = pointATextField.getText().split(" ");
            String[] pointBCoordinates = pointBTextField.getText().split(" ");

            if (pointACoordinates.length != 3 || pointBCoordinates.length != 3) {
                resultLabel.setText("Invalid coordinates. Enter three numbers for each point!");
                resultLabel.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 14px"); // Set text color to red
                return;
            }

            try {
                double x1 = Double.parseDouble(pointACoordinates[0]);
                double y1 = Double.parseDouble(pointACoordinates[1]);
                double z1 = Double.parseDouble(pointACoordinates[2]);
                double x2 = Double.parseDouble(pointBCoordinates[0]);
                double y2 = Double.parseDouble(pointBCoordinates[1]);
                double z2 = Double.parseDouble(pointBCoordinates[2]);

                // Calculate the Manhattan distance between points A and B
                double distance = calculateManhattanDistance(x1, y1, z1, x2, y2, z2);
                String formattedDistance = distance % 1 == 0 ? String.valueOf((int) distance) : String.valueOf(distance);
                // Update the result label with the calculated distance
                resultLabel.setText("Manhattan distance: ~" + formattedDistance + " Blocks");
                resultLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px;");
                return;
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid coordinates. Enter numeric values only!");
                resultLabel.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 14px;");
                return;
            }
        });

        // Create a scene and add the grid pane to it
        Scene scene = new Scene(grid);

        // Set up the stage and show it
        primaryStage.setTitle("Distance Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        return label;
    }

    private TextField createTextField() {
        TextField textField = new TextField();
        textField.setStyle("-fx-font-size: 14px;");
        return textField;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 14px; -fx-padding: 6px 12px;");
        return button;
    }

    private double calculateEuclideanDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        // Calculate the Euclidean distance using the formula: sqrt((x2 - x1)^2 + (y2 -
        // y1)^2 + (z2 - z1)^2)
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;

        return Math.round(Math.sqrt(dx * dx + dy * dy + dz * dz));
    }

    private double calculateManhattanDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        // Calculate the Manhattan distance using the formula: |x2 - x1| + |y2 - y1| +
        // |z2 - z1|
        double dx = Math.abs(x2 - x1);
        double dy = Math.abs(y2 - y1);
        double dz = Math.abs(z2 - z1);

        return dx + dy + dz;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
