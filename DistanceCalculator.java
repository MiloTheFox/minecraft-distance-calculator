/**
 * @author Sweet_Tooth11
 * @version v1.3, 08/20/2023 - 04:51 PM GMT+1
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.*;

public class Main {
    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final int SCALE = 10;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Distance Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(9, 2));
        frame.setBackground(Color.BLACK);

        JTextField[] textFields = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            textFields[i] = new JTextField(5);
            frame.add(createCoordinateLabel(i));
            frame.add(textFields[i]);
        }

        JButton button1 = new JButton("Euclidean Distance");
        JButton button2 = new JButton("Manhattan Distance");

        button1.addActionListener(e -> calculateDistanceAndShowResult(e, textFields, "Euclidean"));
        button2.addActionListener(e -> calculateDistanceAndShowResult(e, textFields, "Manhattan"));

        frame.add(button1);
        frame.add(button2);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private static JLabel createCoordinateLabel(int i) {
        String label = i % 3 == 0 ? "x" + (i / 3 + 1) + ":"
                : i % 3 == 1 ? "y" + (i / 3 + 1) + ":" : "z" + (i / 3 + 1) + ":";
        return new JLabel(label);
    }

    private static void calculateDistanceAndShowResult(ActionEvent event, JTextField[] textFields,
            String distanceType) {
        try {
            BigDecimal[] coordinates = new BigDecimal[6];
            for (int i = 0; i < 6; i++) {
                String input = textFields[i].getText();
                if (!isValidNumber(input)) {
                    showErrorDialog("Please enter valid numbers for all coordinates.");
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
            showInfoDialog("The result is: " + result);
        } catch (NumberFormatException ex) {
            showErrorDialog("Please enter valid numbers for all coordinates.");
        }
    }

    private static boolean isValidNumber(String input) {
        try {
            new BigDecimal(input);
            return true;
        } catch (NumberFormatException | NullPointerException Exception) {
            return false;
        }
    }

    private static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void showInfoDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private static BigDecimal calculateEuclideanDistance(BigDecimal x1, BigDecimal y1, BigDecimal z1,
            BigDecimal x2, BigDecimal y2, BigDecimal z2) {
        BigDecimal dx = x2.subtract(x1);
        BigDecimal dy = y2.subtract(y1);
        BigDecimal dz = z2.subtract(z1);

        return round(sqrt(dx.pow(2).add(dy.pow(2)).add(dz.pow(2))), 0);
    }

    private static BigDecimal calculateManhattanDistance(BigDecimal x1, BigDecimal y1, BigDecimal z1,
            BigDecimal x2, BigDecimal y2, BigDecimal z2) {
        BigDecimal dx = x2.subtract(x1).abs();
        BigDecimal dy = y2.subtract(y1).abs();
        BigDecimal dz = z2.subtract(z1).abs();

        return dx.add(dy).add(dz);
    }

    private static BigDecimal round(BigDecimal value, int scale) {
        return value.setScale(scale, RoundingMode.HALF_UP);
    }

    private static BigDecimal sqrt(BigDecimal x) {
        BigDecimal approx = BigDecimal.ONE;
        BigDecimal sqrt = x.divide(TWO, SCALE, RoundingMode.DOWN);
        while (!approx.equals(sqrt)) {
            approx = sqrt;
            sqrt = x.divide(approx, SCALE, RoundingMode.HALF_UP).add(approx).divide(TWO, SCALE, RoundingMode.HALF_UP);
        }
        return sqrt;
    }
}
