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

    /* Find the max one between two integers
     * pre: two integers
     * post: the biggest integer
     */
    static int maxTwo(int a, int b) {
        return a > b ? a : b;
    }

    //////////////////////////////////////////////////////////////////////

    static Scanner scan = new Scanner(System.in);

    /* Print out hint and get string input from the user
     * pre: a String as hint
     * post: the user input
     */
    static String inputString(String hint) {
        print(hint);
        return next();
    }

    /* Get the next String from the scanner
     * post: the next String from the scanner
     */
    static String next() {
        return scan.next();
    }

    /* Print out hint and get integer input from the user
     * pre: a String as hint
     * post: the user input
     */
    static int inputInt(String hint) {
        print(hint);
        return nextInt();
    }

    /* Get the next integer from the scanner
     * post: the next integer from the scanner
     */
    static int nextInt() {
        return scan.nextInt();
    }

    /* Close the scanner
     */
    static void closeScanner() {
        scan.close();
    }

    /* Print out an object and append a new line
     * pre: an object to print
     */
    static void println(Object obj) {
        System.out.println(obj);
    }

    /* Print out an object
     * pre: an object to print
     */
    static void print(Object obj) {
        System.out.print(obj);
    }
}