import java.util.Scanner;

/**
 * Ask the user for 3 names and print them in frames
 * Kevin JianQing Liu
 */
public class FramesKevin extends ICSBaseKevin {
    public static void main(String[] args) {
        String name1 = inputString("Enter name: ");
        String name2 = inputString("Enter name: ");
        String name3 = inputString("Enter name: ");
        closeScanner();


        frameName(name1);
        frameName(name2);
        frameName(name3);
    }

    /* Print a name in frame
     * pre: the name to print
     */
    static void frameName(String name) {
        println("*".repeat(name.length() + 2) + "\n" +
                "*" + name + "*\n" +
                "*".repeat(name.length() + 2) + "\n");
    }
}


abstract class ICSBaseKevin {
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