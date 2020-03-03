import java.util.Scanner;

/**
 * A class to ask user the temperature of the monkey and print if its normal or not
 * Kevin JianQing Liu
 */
public class MonkeyKevin {
    public static void main(String[] args) {
        float monkeyTemp = inputFloat("Please enter monkey’s temperature: ");
        closeScanner();
        if (monkeyTemp < 35.5f) {
            println("The temperature is too low. Alarm!");
        } else if (monkeyTemp > 38.1f) {
            println("The temperature is too high. Alarm!");
        } else {
            println("The temperature is normal.");
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
