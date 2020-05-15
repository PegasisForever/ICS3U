import java.util.Arrays;
import java.util.Scanner;

/**
 * Kevin JianQing Liu
 * A class to demonstrate the usage of built in array methods.
 */
public class ArraysMethodsKevin {
    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandomIntFromInclusiveRange(0, 100);
        }

        println(Arrays.toString(array));

        Arrays.sort(array);
        println(Arrays.toString(array));

        int index = Arrays.binarySearch(array, 50);
        if (index >= 0) {
            println("The index of 50 in the array is " + index + ".");
        } else {
            println("50 is not in the array.");
        }
    }

    //////////////////////////////////////////////////////////////////////

    static Scanner scan = new Scanner(System.in);
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";

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

    /* Print out an object in red and append a new line
     * pre: an object to print
     */
    static void printlnError(Object obj) {
        println(ANSI_RED + obj + ANSI_RESET);
    }

    /* Print out an object
     * pre: an object to print
     */
    static void print(Object obj) {
        System.out.print(obj);
    }

    /* Get a random integer from inclusive range
     * pre: a max and a min integer
     * post: the randomly generated integer
     */
    static int getRandomIntFromInclusiveRange(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /* Find the max value in an int array
     * pre: the int array
     * post: the max integer
     */
    static int arrayMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    /* Find the min value in an int array
     * pre: the int array
     * post: the min integer
     */
    static int arrayMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            if (num < min) min = num;
        }
        return min;
    }

    /* Calculate the average value in an int array
     * pre: the int array
     * post: the average integer
     */
    static int arrayAvg(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum / arr.length;
    }
}