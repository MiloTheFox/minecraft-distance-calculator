/**
 * @author Sweet_Tooth11
 * @version v1.4, 01/06/2024 - 11:54AM GMT+1
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.stream.Stream;
import org.apache.commons.math3.ml.distance.*;

public class DistanceCalculatorGUI {
    private static final int NUM_COORDINATES = 6;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Distance Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(NUM_COORDINATES + 1, 2));
            DistanceCalculatorUI.initializeUI(frame);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class DistanceCalculatorUI {
    private static final int NUM_COORDINATES = 6;
    private static final EuclideanDistance EUCLIDEAN_DISTANCE = new EuclideanDistance();
    private static final ManhattanDistance MANHATTAN_DISTANCE = new ManhattanDistance();

    public static void initializeUI(JFrame frame) {
        JTextField[] textFields = new JTextField[NUM_COORDINATES];

        for (int i = 0; i < NUM_COORDINATES; i++) {
            textFields[i] = new JTextField(5);
            frame.add(createCoordinateLabel(i));
            frame.add(textFields[i]);
        }
        addButton(frame, "Euclidean Distance", e -> calculateAndShowResult(textFields, EUCLIDEAN_DISTANCE::compute));
        addButton(frame, "Manhattan Distance", e -> calculateAndShowResult(textFields, MANHATTAN_DISTANCE::compute));
    }

    private static JLabel createCoordinateLabel(int i) {
        String[] labels = { "X1", "Y1", "Z1", "X2", "Y2", "Z2" };
        return new JLabel(labels[i]);
    }

    private static void addButton(JFrame frame, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        frame.add(button);
    }

    private static void calculateAndShowResult(JTextField[] textFields, DistanceFunction distanceFunction) {
        try {
            double[] coordinates = extractCoordinates(textFields);
            double result = distanceFunction.compute(coordinates, new double[coordinates.length]);
            showInfoDialog("The result is: " + result);
        } catch (NumberFormatException ex) {
            showErrorDialog("Please enter valid numbers for all coordinates.");
        }
    }

    private static double[] extractCoordinates(JTextField[] textFields) {
        return Stream.of(textFields).mapToDouble(tf -> Double.parseDouble(tf.getText())).toArray();
    }

    private static void showErrorDialog(String message) {
        showDialog(message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void showInfoDialog(String message) {
        showDialog(message, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showDialog(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    @FunctionalInterface
    interface DistanceFunction {
        double compute(double[] a, double[] b);
    }
}
