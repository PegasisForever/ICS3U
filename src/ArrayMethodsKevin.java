import java.util.Scanner;

/**
 * Kevin JianQing Liu
 * Use and print the result of various array methods
 */
public class ArrayMethodsKevin {
    public static void main(String[] args) {
        int[] array = new int[10];

        ArRandom(array);
        ArPrint(array);
        println(ArMax(array));
        ArReverse(array);
        ArPrint(array);
        println(ArSearch(array, 10));
        println(ArSum(array));
    }

    /* Fill an int array with random integer from 0 to 100
     * pre: the int array to fill
     */
    static void ArRandom(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getRandomIntFromInclusiveRange(0, 100);
        }
    }

    /* Print an int array in comma separated list
     * pre: the int array to print
     */
    static void ArPrint(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i]);
            if (i != arr.length - 1) print(", ");
        }
        print("\n");
    }

    /* Find the max value in an int array
     * pre: the int array
     * post: the max integer
     */
    static int ArMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    /* Reverse the int array
     * pre: the int array to reverse
     */
    static void ArReverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int swapIndex = arr.length - 1 - i;
            int temp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /* Search the target in the int array
     * pre: the int array to search, the int target
     * post: the index of the target in the array, -1 if not found
     */
    static int ArSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    /* Sum the array
     * pre: the int array to sum
     * post: the sum of all the numbers in the array
     */
    static int ArSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
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

    /* Get a random integer from inclusive range
     * pre: a max and a min integer
     * post: the randomly generated integer
     */
    static int getRandomIntFromInclusiveRange(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
}