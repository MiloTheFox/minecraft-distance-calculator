/**
 * @author Sweet_Tooth11
 * @version v1.3, 08/27/2023 - 12:41AM GMT+1
 */

// Import statements...
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    // Defining the Constant...
    private static final int NUM_COORDINATES = 6;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Set up the JFrame and UI components...
        JFrame frame = new JFrame("Distance Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(NUM_COORDINATES + 1, 2));
        frame.setBackground(Color.BLACK);

        JTextField[] textFields = new JTextField[NUM_COORDINATES];
        JLabel[] coordinateLabels = new JLabel[NUM_COORDINATES];

        for (int i = 0; i < NUM_COORDINATES; i++) {
            textFields[i] = new JTextField(5);
            coordinateLabels[i] = createCoordinateLabel(i);
            frame.add(coordinateLabels[i]);
            frame.add(textFields[i]);
        }

        JButton button1 = new JButton("Euclidean Distance");
        JButton button2 = new JButton("Manhattan Distance");

        button1.addActionListener(e -> calculateAndShowResult(textFields, DistanceType.EUCLIDEAN));
        button2.addActionListener(e -> calculateAndShowResult(textFields, DistanceType.MANHATTAN));

        frame.add(button1);
        frame.add(button2);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private static JLabel createCoordinateLabel(int i) {
        // Create a label for a coordinate input field...
        String label = String.format("Coordinate %d:", i + 1);
        return new JLabel(label);
    }

    private static BigDecimal[] extractCoordinates(JTextField[] textFields) {
        // Extract and parse coordinates from text fields...
        BigDecimal[] coordinates = new BigDecimal[NUM_COORDINATES];
        for (int i = 0; i < NUM_COORDINATES; i++) {
            String input = textFields[i].getText();
            coordinates[i] = new BigDecimal(input);
        }
        return coordinates;
    }

    private static void calculateAndShowResult(JTextField[] textFields, DistanceType distanceType) {
            // Extract coordinates from text fields and calculate distance...
        try {
            BigDecimal[] coordinates = extractCoordinates(textFields);
            BigDecimal result = calculateDistance(coordinates, distanceType);
            showInfoDialog("The result is: " + result);
            // Return an error if any field is neither a number nor holds a value...
        } catch (NumberFormatException ex) {
            showErrorDialog("Please enter valid numbers for all coordinates.");
        }
    }

    private static BigDecimal calculateDistance(BigDecimal[] coordinates, DistanceType distanceType) {
        // Calculate distance based on distance type...
        int xStartIndex = Indices.X_START.getIndex();
        int xEndIndex = Indices.X_END.getIndex();
        int yStartIndex = Indices.Y_START.getIndex();
        int yEndIndex = Indices.Y_END.getIndex();
        int zStartIndex = Indices.Z_START.getIndex();
        int zEndIndex = Indices.Z_END.getIndex();

        BigDecimal xDifference = coordinates[xEndIndex].subtract(coordinates[xStartIndex]);
        BigDecimal yDifference = coordinates[yEndIndex].subtract(coordinates[yStartIndex]);
        BigDecimal zDifference = coordinates[zEndIndex].subtract(coordinates[zStartIndex]);

        switch (distanceType) {
            case EUCLIDEAN:
                BigDecimal sumOfSquares = xDifference.pow(2).add(yDifference.pow(2)).add(zDifference.pow(2));
                double sqrtValue = Math.sqrt(sumOfSquares.doubleValue());
                return round(BigDecimal.valueOf(sqrtValue), 0);
            case MANHATTAN:
                BigDecimal xAbs = xDifference.abs();
                BigDecimal yAbs = yDifference.abs();
                BigDecimal zAbs = zDifference.abs();
                return xAbs.add(yAbs).add(zAbs);
            default:
                throw new IllegalArgumentException("Unknown distance type");
        }
    }

    private static BigDecimal round(BigDecimal value, int scale) {
        // Custom function to round a BigDecimal value...
        return value.setScale(scale, RoundingMode.HALF_UP);
    }

    private static void showErrorDialog(String message) {
	// Show an error dialog
        showDialog(message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void showInfoDialog(String message) {
        // Show an info dialog...
        showDialog(message, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showDialog(String message, String title, int messageType) {
        // Show a generic dialog...
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    private enum Indices {
        // Enum for indices of coordinates...
        X_START(0),
        Y_START(1),
        Z_START(2),
        X_END(3),
        Y_END(4),
        Z_END(5);

        private final int index;

        Indices(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    private enum DistanceType {
        // Enum for the available distance types...
        EUCLIDEAN,
        MANHATTAN
    }
}
