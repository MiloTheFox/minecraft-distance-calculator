/**
 * @author Sweet_Tooth11
 * @version v1.2, 08/06/2023 - 03:41 PM
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
	private static void createAndShowGUI() {
        JFrame frame = new JFrame("Distance Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(9, 2));
        frame.setBackground(Color.BLACK);

        JTextField[] textFields = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            textFields[i] = new JTextField(5);
            frame.add(new JLabel(i % 3 == 0 ? "x"  + (i / 3 + 1) + ":" : i % 3 == 1 ? "y"  + (i / 3 + 1) + ":" : "z" + (i / 3 + 1) + ":"));
            frame.add(textFields[i]);
        }

        JButton button1 = new JButton("Euclidean Distance");
        JButton button2 = new JButton("Manhattan Distance");

        button1.addActionListener(e -> calculateDistanceAndShowResult(textFields, "Euclidean"));
        button2.addActionListener(e -> calculateDistanceAndShowResult(textFields, "Manhattan"));

        frame.add(button1);
        frame.add(button2);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

	private static void calculateDistanceAndShowResult(JTextField[] textFields, String distanceType) {
        try {
            BigDecimal[] coordinates = new BigDecimal[6];
            for (int i = 0; i < 6; i++) {
                // Validate and sanitize user inputs before parsing them as BigDecimal.
                String input = textFields[i].getText();
                if (!isValidNumber(input)) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for all coordinates.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                coordinates[i] = new BigDecimal(input);
            }

            BigDecimal result = BigDecimal.ZERO;
            if (distanceType.equals("Euclidean")) {
                result = calculateEuclideanDistance(coordinates[0], coordinates[1], coordinates[2],
                        coordinates[3], coordinates[4], coordinates[5]);
            } else if (distanceType.equals("Manhattan")) {
                result = calculateManhattanDistance(coordinates[0], coordinates[1], coordinates[2],
                        coordinates[3], coordinates[4], coordinates[5]);
            }
            JOptionPane.showMessageDialog(null, "The Result of the is: " + result, "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for all coordinates.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Add a method for input validation.
    private static boolean isValidNumber(String input) {
        try {
            new BigDecimal(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
 // Helper method to round a BigDecimal to a specified number of decimal places.
    private static BigDecimal round(BigDecimal value, int scale) {
        return value.setScale(scale, RoundingMode.HALF_UP);
    }
    // Use BigDecimal for precise calculations.
    private static BigDecimal calculateEuclideanDistance(BigDecimal x1, BigDecimal y1, BigDecimal z1,
                                                         BigDecimal x2, BigDecimal y2, BigDecimal z2) {
        // Calculate the Euclidean distance using the formula: sqrt((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)
        BigDecimal dx = x2.subtract(x1);
        BigDecimal dy = y2.subtract(y1);
        BigDecimal dz = z2.subtract(z1);

        return round(sqrt(dx.pow(2).add(dy.pow(2)).add(dz.pow(2))), 0);
    }

	private static BigDecimal calculateManhattanDistance(BigDecimal x1, BigDecimal y1, BigDecimal z1,
                                                         BigDecimal x2, BigDecimal y2, BigDecimal z2) {
        // Calculate the Manhattan distance using the formula: |x2 - x1| + |y2 - y1| + |z2 - z1|
        BigDecimal dx = x2.subtract(x1).abs();
        BigDecimal dy = y2.subtract(y1).abs();
        BigDecimal dz = z2.subtract(z1).abs();

        return dx.add(dy).add(dz);
    }

    // Helper method for calculating the square root of a BigDecimal.
    private static BigDecimal sqrt(BigDecimal x) {
        BigDecimal TWO = BigDecimal.valueOf(2);
        int scale = 10; // Set the desired scale for the result.
        BigDecimal approx = BigDecimal.ONE;
        BigDecimal sqrt = x.divide(TWO, scale, RoundingMode.DOWN); // Initial guess.
        while (!approx.equals(sqrt)) {
            approx = sqrt;
            sqrt = x.divide(approx, scale, RoundingMode.HALF_UP).add(approx).divide(TWO, scale, RoundingMode.HALF_UP);
        }
        return sqrt;
    }
}
