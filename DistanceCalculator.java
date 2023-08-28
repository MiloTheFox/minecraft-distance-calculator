/**
 * @author Sweet_Tooth11
 * @version v1.3, 08/27/2023 - 12:41AM GMT+1
 */

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

public class DistanceCalculator {
    private static final int NUM_COORDINATES = 6;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Distance Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(NUM_COORDINATES + 1, 2));
            frame.setBackground(Color.BLACK);

            DistanceCalculatorUI.initializeUI(frame);

            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

class DistanceCalculatorUI {
    private static final int NUM_COORDINATES = 6;

    public static void initializeUI(JFrame frame) {
        JTextField[] textFields = new JTextField[NUM_COORDINATES];

        for (int i = 0; i < NUM_COORDINATES; i++) {
            textFields[i] = new JTextField(5);
            frame.add(createCoordinateLabel(i));
            frame.add(textFields[i]);
        }

        JButton button1 = new JButton("Euclidean Distance");
        JButton button2 = new JButton("Manhattan Distance");

        button1.addActionListener(e -> calculateAndShowResult(textFields, DistanceType.EUCLIDEAN));
        button2.addActionListener(e -> calculateAndShowResult(textFields, DistanceType.MANHATTAN));

        frame.add(button1);
        frame.add(button2);
    }

    private static JLabel createCoordinateLabel(int i) {
        String label = String.format("Coordinate %d:", i + 1);
        return new JLabel(label);
    }

    private static void calculateAndShowResult(JTextField[] textFields, DistanceType distanceType) {
        try {
            BigDecimal[] coordinates = extractCoordinates(textFields);
            BigDecimal result = calculateDistance(coordinates, distanceType);
            showInfoDialog("The result is: " + result);
        } catch (NumberFormatException ex) {
            showErrorDialog("Please enter valid numbers for all coordinates.");
        }
    }

    private static BigDecimal[] extractCoordinates(JTextField[] textFields) {
        return Stream.of(textFields)
            .map(tf -> new BigDecimal(tf.getText()))
            .toArray(BigDecimal[]::new);
    }

    private static BigDecimal calculateDistance(BigDecimal[] coordinates, DistanceType distanceType) {
        BigDecimal[] differences = new BigDecimal[NUM_COORDINATES / 2];

        for (int i = 0; i < differences.length; i++) {
            int startIndex = i * 2;
            int endIndex = startIndex + 1;
            differences[i] = coordinates[endIndex].subtract(coordinates[startIndex]);
        }

        switch (distanceType) {
            case EUCLIDEAN:
                BigDecimal sumOfSquares = Stream.of(differences)
                    .map(d -> d.pow(2))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                double sqrtValue = Math.sqrt(sumOfSquares.doubleValue());
                return round(BigDecimal.valueOf(sqrtValue), 0);
            case MANHATTAN:
                BigDecimal totalAbsDifference = Stream.of(differences)
                    .map(BigDecimal::abs)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                return totalAbsDifference;
            default:
                throw new IllegalArgumentException("Unknown distance type");
        }
    }

    private static BigDecimal round(BigDecimal value, int scale) {
        return value.setScale(scale, RoundingMode.HALF_UP);
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
}

enum DistanceType {
    EUCLIDEAN,
    MANHATTAN
}
