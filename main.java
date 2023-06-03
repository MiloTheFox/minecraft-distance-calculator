// Import necessary libraries
import org.apache.commons.math4.legacy.linear.ArrayRealVector;
import org.apache.commons.math4.legacy.linear.RealVector;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.BiFunction;

// Define a class named CalculateDistance
class CalculateDistance {
    public static void main(String[] args) {
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        // Prompt the user to enter the coordinates for point A
        System.out.println("Enter the coordinates for point A (x y z):");
        // Read the coordinates for point A and store them in a RealVector object
        RealVector pointA = readPoint(scanner);
        // Validate that point A is not null and has 3 dimensions
        validatePoint(pointA, "Point A");

        // Prompt the user to enter the coordinates for point B
        System.out.println("Enter the coordinates for point B (x y z):");
        // Read the coordinates for point B and store them in a RealVector object
        RealVector pointB = readPoint(scanner);
        // Validate that point B is not null and has 3 dimensions
        validatePoint(pointB, "Point B");

        // Ask the user to choose between the Euclidean or Manhattan method
        System.out.println("Do you want to use the Euclidean or the Manhattan method?: ");
        // Read the user's choice and convert it to lowercase
        String metric = scanner.next().toLowerCase();

        // Declare a distanceFunction variable of type BiFunction that calculates the distance between two points
        BiFunction<RealVector, RealVector, Double> distanceFunction;

        // Choose the distance calculation method based on the user's choice
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
                // Throw an exception if the user's choice is invalid
                throw new InputMismatchException("Please put in the method that you want to use!\n\n1) Euclidean\n2) Manhattan");
        }

        // Calculate the distance between point A and point B using the chosen method
        double distance = calculateDistance(pointA, pointB, distanceFunction);

        // Format the coordinates of point A and point B as strings with a specified number of significant figures
        String pointAString = formatPoint(pointA, 1);
        String pointBString = formatPoint(pointB, 1);

        // Print the calculated distance between point A and point B (Prints the values given)
        System.out.format("The Distance between (\033[0;33m%s\033[0m) and (\033[0;33m%s\033[0m) using the %s Method is approximately %d Blocks.\n", (pointAString), (pointBString), (metric), ((int) distance));
    }

    // Read the x, y, and z coordinates of a point from the user
    private static RealVector readPoint(Scanner scanner) {
        double x = readDouble(scanner);
        double y = readDouble(scanner);
        double z = readDouble(scanner);
        return new ArrayRealVector(new double[]{x, y, z});
    }

    // Read a double value from the user, repeatedly prompting until a valid number is entered
    private static double readDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number:");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    // Format the coordinates of a point as a string with a specified number of significant figures
    private static String formatPoint(RealVector point, int numSigFigs) {
        double[] coordinates = point.toArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coordinates.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(formatSigFigs(coordinates[i], numSigFigs));
        }
        return sb.toString();
    }
    
    // Format a double value with a specified number of significant figures
    private static String formatSigFigs(double value, int numSigFigs) {
        if (value == 0) {
            return "0";
        }
        final double d = Math.ceil(Math.log10(value < 0 ? -value : value));
        final int power = numSigFigs - (int) d;
        final double magnitude = Math.pow(10, power);
        final long shifted = Math.round(value * magnitude);
        return Double.toString(shifted / magnitude);
    }

    // Validate that a point is not null and has 3 dimensions
    private static void validatePoint(RealVector point, String pointName) {
        if (point == null) {
            throw new IllegalArgumentException(pointName + " cannot be null!");
        }
        if (point.getDimension() != 3) {
            throw new IllegalArgumentException(pointName + " must have exactly 3 dimensions!");
        }
    }

    // Calculate the distance between two points using a given distance function
    private static double calculateDistance(RealVector pointA, RealVector pointB, BiFunction<RealVector, RealVector, Double> distanceFunction) {
        return distanceFunction.apply(pointA, pointB);
    }
}
