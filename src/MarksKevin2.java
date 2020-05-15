import java.util.Scanner;

/**
 * Kevin JianQing Liu
 * print marks of 3 quizs and average for 12 students.
 */
public class MarksKevin2 {
    public static void main(String[] args) {
        String[] names = new String[12];
        for (int i = 0; i < names.length; i++) {
            names[i] = "Name" + i;
        }

        int[] quiz1 = getQuizMarks(names.length);
        int[] quiz2 = getQuizMarks(names.length);
        int[] quiz3 = getQuizMarks(names.length);

        int[] average = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            average[i] = (quiz1[i] + quiz2[i] + quiz3[i]) / 3;
        }

        format("%-10s%-7s%-7s%-7s%-7s\n", "Name", "quiz1", "quiz2", "quiz3", "average");
        for (int i = 0; i < names.length; i++) {
            format("%-10s%-7d%-7d%-7d%-7d\n",names[i],quiz1[i],quiz2[i],quiz3[i],average[i]);
        }
    }
    /* Get marks of one quiz for all students
     * pre: the number of students
     * post: an array of marks
     */
    static int[] getQuizMarks(int length) {
        int[] marks = new int[length];
        for (int i = 0; i < marks.length; i++) {
            marks[i] = getRandomIntFromInclusiveRange(25, 100);
        }
        return marks;
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
}