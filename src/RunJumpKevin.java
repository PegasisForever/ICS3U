import java.util.Scanner;

/**
 * Kevin JianQing Liu
 * A class to query on data record of long jump and run of students.
 */
public class RunJumpKevin {
    static String[] name = new String[11];
    static int[] longjump = new int[11];
    static int[] run = new int[11];

    public static void main(String[] args) {
        for (int i = 0; i < name.length; i++) {
            name[i] = "Name" + i;
        }
        for (int i = 0; i < longjump.length; i++) {
            longjump[i] = getRandomIntFromInclusiveRange(300, 700);
        }
        for (int i = 0; i < run.length; i++) {
            run[i] = getRandomIntFromInclusiveRange(11, 25);
        }

        String inputName = inputString("Enter a name to get his/her long jump distance: ");
        int longJumpDistance = getLongJumpDistance(inputName);
        if (longJumpDistance == -1) {
            printlnError("This name doesn't exist.");
        } else {
            println(inputName + "'s long jump distance is " + longJumpDistance + ".");
        }

        inputName = inputString("Enter a name to get his/her 100m dash time: ");
        int dashTime = getDashTime(inputName);
        if (dashTime == -1) {
            printlnError("This name doesn't exist.");
        } else {
            println(inputName + "'s 100m dash time is " + dashTime + ".");
        }

        printFastestDashNames();

        printLongestJumpNames();

        printDashAverageAndNamesAboveAverage();

        closeScanner();
    }

    /* Get the long jump distance of a student.
     * pre: the student name
     * post: the long jump distance, -1 if the student doesn't exist
     */
    static int getLongJumpDistance(String nameString) {
        int i = nameSearch(nameString);
        if (i == -1) return -1;
        return longjump[i];
    }

    /* Get the dash time of a student.
     * pre: the student name
     * post: the dash time, -1 if the student doesn't exist
     */
    static int getDashTime(String nameString) {
        int i = nameSearch(nameString);
        if (i == -1) return -1;
        return run[i];
    }

    /* Print the student(s) that had the fastest dash time.
     */
    static void printFastestDashNames() {
        int max = arrayMax(run);
        String output = "";
        for (int i = 0; i < name.length; i++) {
            if (run[i] == max) {
                output += name[i] + ", ";
            }
        }
        println(output + "\b\b did the fastest jump.");
    }

    /* Print the student(s) that had the longest jump.
     */
    static void printLongestJumpNames() {
        int max = arrayMax(longjump);
        String output = "";
        for (int i = 0; i < name.length; i++) {
            if (longjump[i] == max) {
                output += name[i] + ", ";
            }
        }
        println(output + "\b\b did the longest jump.");
    }

    /* Print the average time of dash and student(s) that's above average.
     */
    static void printDashAverageAndNamesAboveAverage() {
        int average = arrayAvg(run);
        println("Average time of dash is: " + average + ".");
        String output = "";
        for (int i = 0; i < name.length; i++) {
            if (run[i] > average) {
                output += name[i] + ", ";
            }
        }
        print(output + "\b\b was/were above average on 100m dash.");
    }

    /* Search the name in the name array
     * pre: the name String to search
     * post: the index of the name in the array, -1 if not found
     */
    static int nameSearch(String nameString) {
        for (int i = 0; i < name.length; i++) {
            if (name[i].equals(nameString)) return i;
        }
        return -1;
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