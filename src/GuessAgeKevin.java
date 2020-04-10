import java.util.Scanner;

/**
 * A class to let the user guess age.
 * Kevin JianQing Liu
 */
public class GuessAgeKevin {
    final static int AGE = 30;

    public static void main(String[] args) {
        println("Please guess one of my friends' age.");
        int tries = 1;
        int guessInput;
        while ((guessInput = inputInt("Guess: ")) != AGE) {
            tries++;
            if (guessInput > AGE) {
                println("Too high!");
            } else {
                println("Too low!");
            }
        }
        closeScanner();
        println("Correct! You tried "+tries+" times.");
    }

    static Scanner scan = new Scanner(System.in);

    static int inputInt(String hint) {
        print(hint);
        return nextInt();
    }

    static String inputString(String hint) {
        print(hint);
        return next();
    }

    static int nextInt() {
        return scan.nextInt();
    }

    static String next() {
        return scan.next();
    }

    static void closeScanner() {
        scan.close();
    }

    static void print(Object obj) {
        System.out.print(obj);
    }

    static void println(Object obj) {
        System.out.println(obj);
    }
}
