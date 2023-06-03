// Import necessary libraries
import org.apache.commons.math4.legacy.linear.ArrayRealVector;
import org.apache.commons.math4.legacy.linear.RealVector;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.BiFunction;

// Class to calculate the distance between two points
class CalculateDistance {
    // Main method
    public static void main(String[] args) {
        // Create a scanner object to read user input
        final Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        // Prompt user to enter coordinates for point A
        System.out.println("Enter the coordinates for point A (x y z):");
        // Read point A from user input
        final RealVector pointA = readPoint(scanner);
        // Validate point A
        validatePoint(pointA, "Point A");
        // Prompt user to enter coordinates for point B
        System.out.println("Enter the coordinates for point B (x y z):");
        // Read point B from user input
        final RealVector pointB = readPoint(scanner);
        // Validate point B
        validatePoint(pointB, "Point B");
        // Prompt user to choose distance calculation method
        System.out.println("Do you want to use the Euclidean or the Manhattan method?: ");
        final String metric = scanner.next().toLowerCase();
        
        // Declare a distance function variable
        BiFunction<RealVector, RealVector, Double> distanceFunction;

        // Use a switch statement to determine which distance function to use based on user input
        switch (metric) {
            case "euclidean":
            case "e":
                // Use the Euclidean distance formula
                distanceFunction = (a, b) -> a.getDistance(b);
                break;
            case "manhattan":
            case "m":
                // Use the Manhattan distance formula
                distanceFunction = (a, b) -> a.getL1Distance(b);
                break;
            default:
                // Throw an exception if the user enters an invalid method
                throw new InputMismatchException("Please put in the method that you want to use!\n\n1) Euclidean\n2) Manhattan");
        }

        // Calculate the distance between point A and point B using the chosen distance function
        final double distance = calculateDistance(pointA, pointB, distanceFunction);
        
        // Format points A and B as strings with specified number of significant figures
        final String pointAString = formatPoint(pointA, 4);
        final String pointBString = formatPoint(pointB,3);
        
        String output = metric.substring(0, 1).toUpperCase() + metric.substring(1);
        if (metric.equalsIgnoreCase("e")) {
            output = "Euclidean";
        } else if (metric.equalsIgnoreCase("m")) {
            output = "Manhattan";
        }

        // Print the result of the calculation with formatted points and chosen metric method
        System.out.format("The Distance between (\033[0;33m%s\033[0m) and (\033[0;33m%s\033[0m) using the %s Method is approximately %d Blocks.", (pointAString), (pointBString), (output), ((int) distance));
    }

    // Method to read a point from user input using a scanner object
    private static RealVector readPoint(Scanner scanner) {
        final double x = readDouble(scanner);
        final double y = readDouble(scanner);
        final double z = readDouble(scanner);
        
        // Create and return a new ArrayRealVector object with the entered coordinates
        return new ArrayRealVector(new double[]{x, y, z});
    }
    
    // Method to read a double value from user input using a scanner object
    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                if (scanner.hasNextDouble()) {
                    break;
                } else {
                    scanner.next();
                }
            } catch (NoSuchElementException e) {
                System.out.println("\nProgram terminated.");
                System.exit(0);
            }
            System.out.print("Invalid input. Please enter a valid number: ");
        }
        // Return the entered double value
        return scanner.nextDouble();
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
        final double d = Math.ceil(Math.log10(value < 0 ? -value : value));
        final int power = numSigFigs - (int) d;
        final double magnitude = Math.pow(10, power);
        
        // Shift the value and round it to the nearest integer
        final long shifted = Math.round(value * magnitude);
        
        // Return the shifted value as a string
        return Double.toString(shifted / magnitude);
    }
    
    // Method to validate a RealVector object representing a point
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
