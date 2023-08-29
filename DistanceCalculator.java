/**
 * @author Sweet_Tooth11
 * @version v1.3, 08/29/2023 - 02:50AM GMT+1
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

    private Label resultLabel;
    private TextField pointATextField;
    private TextField pointBTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        primaryStage.getIcons().add(icon);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: #d3d3d3;");

        resultLabel = createLabel("");
        grid.add(resultLabel, 0, 3, 3, 1);

        addPointFields(grid);
        addMethodButtons(grid);

        Scene scene = new Scene(grid);
        primaryStage.setTitle("Distance Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addPointFields(GridPane grid) {
        Label pointALabel = createLabel("Point A (x y z):");
        pointATextField = createTextField(); // Assign to instance field
        Label pointBLabel = createLabel("Point B (x y z):");
        pointBTextField = createTextField(); // Assign to instance field

        grid.add(pointALabel, 0, 0);
        grid.add(pointATextField, 1, 0);
        grid.add(pointBLabel, 0, 1);
        grid.add(pointBTextField, 1, 1);
    }

    private void addMethodButtons(GridPane grid) {
        Label methodLabel = createLabel("Distance Calculation Method:");
        Button euclideanButton = createButton("Euclidean");
        Button manhattanButton = createButton("Manhattan");

        grid.add(methodLabel, 0, 2);
        grid.add(euclideanButton, 1, 2);
        grid.add(manhattanButton, 2, 2);

        euclideanButton.setOnAction(event -> calculateDistanceMethod(pointATextField.getText(), pointBTextField.getText(), true));
        manhattanButton.setOnAction(event -> calculateDistanceMethod(pointATextField.getText(), pointBTextField.getText(), false));
    }

    private void calculateDistanceMethod(String pointAText, String pointBText, boolean isEuclidean) {
        String[] pointACoordinates = pointAText.split(" ");
        String[] pointBCoordinates = pointBText.split(" ");

        if (pointACoordinates.length != 3 || pointBCoordinates.length != 3) {
            showError("Invalid coordinates. Enter three numbers for each point!");
            return;
        }

        try {
            double x1 = Double.parseDouble(pointACoordinates[0]);
            double y1 = Double.parseDouble(pointACoordinates[1]);
            double z1 = Double.parseDouble(pointACoordinates[2]);
            double x2 = Double.parseDouble(pointBCoordinates[0]);
            double y2 = Double.parseDouble(pointBCoordinates[1]);
            double z2 = Double.parseDouble(pointBCoordinates[2]);

            double distance = isEuclidean
                    ? calculateEuclideanDistance(x1, y1, z1, x2, y2, z2)
                    : calculateManhattanDistance(x1, y1, z1, x2, y2, z2);

            String formattedDistance = String.format("%.2f", distance);
            showResult((isEuclidean ? "Euclidean" : "Manhattan") + " distance: ~" + formattedDistance + " Blocks");

        } catch (NumberFormatException e) {
            showError("Invalid coordinates. Enter numeric values only!");
        }
    }

    private void calculateDistance(String pointAText, String pointBText) {
        calculateDistanceMethod(pointAText, pointBText, true);
    }

    private void showResult(String message) {
        resultLabel.setText(message);
        resultLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px;");
    }

    private void showError(String message) {
        resultLabel.setText(message);
        resultLabel.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 14px;");
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
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    private double calculateManhattanDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        double dx = Math.abs(x2 - x1);
        double dy = Math.abs(y2 - y1);
        double dz = Math.abs(z2 - z1);

        return dx + dy + dz;
    }
}
