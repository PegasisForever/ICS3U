import java.util.Arrays;
import java.util.Scanner;

/**
 * Kevin JianQing Liu
 * Create 14 temperatures
 * - print them out in a list
 * - print how many days was warmer than 5 C
 * - print every negative day
 * - print the hottest and the coldest day
 */
public class TemperatureKevin {
    public static void main(String[] args) {
        int[] temps = new int[14];

        for (int i = 0; i < 14; i++) {
            temps[i] = getRandomIntFromInclusiveRange(-10, 10);
        }

        println("Temperatures in 14 days: " + Arrays.toString(temps));

        int daysWarmerThan5C = 0;
        for (int temp : temps) {
            if (temp > 5) daysWarmerThan5C++;
        }
        println(daysWarmerThan5C + " days the temperature was greater than 5 C.");

        for (int i = 0; i < temps.length; i++) {
            int temp = temps[i];
            if (temp < 0) {
                println("Day " + i + " had a temperature of " + temp + " C.");
            }
        }

        int minTemp = Integer.MAX_VALUE;
        int minTempI = -1;
        for (int i = 0; i < temps.length; i++) {
            int temp = temps[i];
            if (temp < minTemp) {
                minTemp = temp;
                minTempI = i;
            }
        }
        println("Day " + minTempI + " had the min temperature of " + minTemp + " C.");

        int maxTemp = Integer.MIN_VALUE;
        int maxTempI = -1;
        for (int i = 0; i < temps.length; i++) {
            int temp = temps[i];
            if (temp > maxTemp) {
                maxTemp = temp;
                maxTempI = i;
            }
        }
        println("Day " + maxTempI + " had the max temperature of " + maxTemp + " C.");
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