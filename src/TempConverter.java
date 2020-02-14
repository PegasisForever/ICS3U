import java.util.Scanner;

/*
 * Class to convert Celsius degree to Fahrenheit degree.
 * Kevin JianQing Liu
 */
public class TempConverter {
    public static void main(String[] args) {
        float celsius = inputFloat("Please input a temperature in degrees Celsius:");
        float fahrenheit = 32 + celsius * 9 / 5;
        closeScanner();
        println("The temperature in degrees Fahrenheit is " + fahrenheit + ".");
    }

    static Scanner scan = new Scanner(System.in);

    static float inputFloat(String hint) {
        println(hint);
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
}
