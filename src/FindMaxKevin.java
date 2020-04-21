import java.util.Scanner;

/**
 * Ask the user for 4 integers and print the largest one
 * Kevin JianQing Liu
 */
public class FindMaxKevin {
    public static void main(String[] args) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            int input = inputInt("Enter an integer: ");
            max = maxTwo(max, input);
        }
        closeScanner();

        println("The max integer is " + max + ".");
    }

    static int maxTwo(int a, int b) {
        return a > b ? a : b;
    }

    static Scanner scan = new Scanner(System.in);

    static int inputInt(String hint) {
        print(hint);
        return nextInt();
    }

    static int nextInt() {
        return scan.nextInt();
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
