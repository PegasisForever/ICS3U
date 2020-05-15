import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Kevin JianQing Liu
 * Store names and scores of 12 users to two files and
 * print the user with the highest score.
 */
public class PlayerScoresKevin {
    public static void main(String[] args) throws FileNotFoundException {
        String[] names = new String[12];
        PrintWriter nameFileWriter = new PrintWriter("PlayerName.txt");
        for (int i = 0; i < names.length; i++) {
            names[i] = "Name" + i;
            format("%-7s", names[i]);
            nameFileWriter.print(names[i] + " ");
        }
        print("\n");
        nameFileWriter.close();

        int[] score = new int[12];
        PrintWriter scoreFileWriter = new PrintWriter("PlayerScore.txt");
        for (int i = 0; i < score.length; i++) {
            score[i] = getRandomIntFromInclusiveRange(0, 99);
            format("%-7d", score[i]);
            scoreFileWriter.print(score[i] + " ");
        }
        print("\n");
        scoreFileWriter.close();


        scanFromFile("PlayerName.txt");
        for (int i = 0; i < names.length; i++) {
            names[i] = next();
        }
        closeScanner();

        scanFromFile("PlayerScore.txt");
        for (int i = 0; i < score.length; i++) {
            score[i] = nextInt();
        }
        closeScanner();

        int maxScore = Integer.MIN_VALUE;
        int maxScoreI = -1;
        for (int i = 0; i < score.length; i++) {
            if (score[i] > maxScore) {
                maxScore = score[i];
                maxScoreI = i;
            }
        }
        println(names[maxScoreI] + " had the highest score of " + maxScore + ".");
    }

    //////////////////////////////////////////////////////////////////////

    static Scanner scan = null;
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";

    /* Switch scan source to the console
     */
    static void scanFromConsole() {
        scan = new Scanner(System.in);
    }

    /* Switch scan source to a file
     * pre: the path of the file
     */
    static void scanFromFile(String filePath) throws FileNotFoundException {
        scan = new Scanner(new File(filePath));
    }

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

    /* Print out hint and get string input of the whole line from the user
     * pre: a String as hint
     * post: the user input
     */
    static String inputLine(String hint) {
        print(hint);
        return nextLine();
    }

    /* Get the next line String from the scanner
     * post: the next line String from the scanner
     */
    static String nextLine() {
        return scan.nextLine();
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

    /* Print out string with format
     * pre: format string, arguments
     */
    static void format(String format, Object... args) {
        System.out.format(format, args);
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