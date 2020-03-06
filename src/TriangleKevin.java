import java.util.Scanner;

/**
 * A class that asks for the lengths of 3 sides of a triangle.
 * Output whether the triangle is equilateral (all three sides equal),
 * isosceles (2 sides equal) or scalene (no sides equal).
 * Kevin JianQing Liu
 */
public class TriangleKevin {
    public static void main(String[] args) {
        float s1 = inputFloat("Enter the first side of the triangle: ");
        float s2 = inputFloat("Enter the second side of the triangle: ");
        float s3 = inputFloat("Enter the third side of the triangle: ");
        closeScanner();

        if (s1 == s2 && s2 == s3) {
            println("Equilateral");
        } else if (s1 == s2 && s2 != s3) {
            println("Isosceles");
        } else if (s1 == s3 && s2 != s3) {
            println("Isosceles");
        } else if (s3 == s2 && s1 != s2) {
            println("Isosceles");
        } else {
            println("Scalene");
        }
    }

    static Scanner scan = new Scanner(System.in);

    static float inputFloat(String hint) {
        print(hint);
        return nextFloat();
    }

    static float nextFloat() {
        return scan.nextFloat();
    }

    static void closeScanner() {
        scan.close();
    }

    static void println(Object obj) {
        System.out.println(obj);
    }

    static void print(Object obj) {
        System.out.print(obj);
    }
}
