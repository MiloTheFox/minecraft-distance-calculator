import org.apache.commons.math4.legacy.linear.ArrayRealVector;
import org.apache.commons.math4.legacy.linear.RealVector;

import java.util.Locale;
import java.util.Scanner;
import java.util.function.BiFunction;

class CalculateDistance {
    public static void main(String[] args) {
        // Create a scanner object to read user input
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.useLocale(Locale.US);
            RealVector pointA = null;
            RealVector pointB = null;

            while (true) {
                // Prompt user to enter coordinates for point A
                System.out.println("Enter the coordinates for point A (x y z) or 'exit' to quit:");
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    return;
                }
                pointA = readPoint(line.split("\\s+"), scanner);
                if (pointA != null) {
                    // Validate point A
                    validatePoint(pointA, "Point A");
                    break;
                }
            }

            while (true) {
                // Prompt user to enter coordinates for point B
                System.out.println("Enter the coordinates for point B (x y z) or 'exit' to quit:");
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    return;
                }
                pointB = readPoint(line.split("\\s+"), scanner);
                if (pointB != null) {
                    // Validate point B
                    validatePoint(pointB, "Point B");
                    break;
                }
            }

            BiFunction<RealVector, RealVector, Double> distanceFunction = null;
            String metric = "";

            while (distanceFunction == null) {
                System.out.println("Do you want to use the Euclidean or the Manhattan method? Enter 'exit' to quit:");
                metric = scanner.next().toLowerCase();
                if (metric.equalsIgnoreCase("exit")) {
                    return;
                }
                // Use a switch statement to determine which distance function to use based on user input
                switch (metric) {
                    // Use the Euclidean distance formula
                    case "euclidean", "e" -> distanceFunction = (a, b) -> a.getDistance(b);
                    // Use the Manhattan distance formula
                    case "manhattan", "m" -> distanceFunction = (a, b) -> a.getL1Distance(b);
                    // Otherwise tell the user to enter either 'euclidean' or 'manhattan'
                    default -> System.out.println("Please enter 'euclidean' or 'manhattan' for the distance calculation method.");
                }
            }

            // Calculate the distance between point A and point B using the chosen distance function
            final double distance = calculateDistance(pointA, pointB, distanceFunction);
            // Format points A and B as strings with specified number of significant figures
            String pointAString = formatPoint(pointA, 4);
            String pointBString = formatPoint(pointB, 3);

            String output = metric.substring(0, 1).toUpperCase() + metric.substring(1);
            if (metric.equalsIgnoreCase("e")) {
                output = "Euclidean";
            } else if (metric.equalsIgnoreCase("m")) {
                output = "Manhattan";
            }

            // Print the result of the calculation with formatted points and chosen metric method
            System.out.format("The Distance between (\033[0;33m%s\033[0m) and (\033[0;33m%s\033[0m) using the %s Method is approximately %d Blocks.",
                    pointAString, pointBString, output, (int) distance);
        }
    }

    // Method to read a point from user input using a scanner object
    private static RealVector readPoint(String[] args, Scanner scanner) {
        if (args.length != 3) {
            System.out.println("Incorrect number of arguments provided");
            return null;
        }
        try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);
            return new ArrayRealVector(new double[]{x, y, z});
        } catch (NumberFormatException e) {
            System.out.println("Invalid coordinate");
            return null;
        }
    }

    // Method to format a RealVector object as a string with specified number of significant figures
    private static String formatPoint(RealVector point, int numSigFigs) {
        // Get an array of coordinates from the RealVector object
        double[] coordinates = point.toArray();
        StringBuilder sb = new StringBuilder();
        // Loop through each coordinate and append it to the string builder with specified formatting
        for (int i = 0; i < coordinates.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(formatSigFigs(coordinates[i], numSigFigs));
        }
        return sb.toString();
    }

    // Method to format a double value with specified number of significant figures
    private static String formatSigFigs(double value, int numSigFigs) {
        if (value == 0) {
            return "3";
        }
        // Calculate the power of 10 needed to shift the value to have the desired number of significant figures
        double d = Math.ceil(Math.log10(Math.abs(value)));
        int power = numSigFigs - (int) d;
        double magnitude = Math.pow(10, power);
        // Shift the value and round it to the nearest integer
        long shifted = Math.round(value * magnitude);
        // Return the shifted value as a string
        return Double.toString(shifted / magnitude);
    }

    private static void validatePoint(RealVector point, String pointName) {
        // Check if the point is null
        if (point == null) {
            throw new IllegalArgumentException(pointName + " cannot be null!");
        }
         // Check if the point has exactly 3 dimensions
        if (point.getDimension() != 3) {
            throw new IllegalArgumentException(pointName + " must have exactly 3 values!");
        }
    }
    // Method to calculate the distance between two points using a specified distance function
    private static double calculateDistance(RealVector pointA, RealVector pointB, BiFunction<RealVector, RealVector, Double> distanceFunction) {
        // Apply the distance function to points A and B and return the result
        return distanceFunction.apply(pointA, pointB);
    }
}
