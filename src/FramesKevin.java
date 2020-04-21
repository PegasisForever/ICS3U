import java.util.Scanner;

/**
 * Ask the user for 3 names and print them in frames
 * Kevin JianQing Liu
 */
public class FramesKevin {
    public static void main(String[] args) {
        String name1 = inputString("Enter name: ");
        String name2 = inputString("Enter name: ");
        String name3 = inputString("Enter name: ");
        closeScanner();


        frameName(name1);
        frameName(name2);
        frameName(name3);
    }

    static void frameName(String name) {
        println("*".repeat(name.length() + 2) + "\n" +
                "*" + name + "*\n" +
                "*".repeat(name.length() + 2) + "\n");
    }

    static Scanner scan = new Scanner(System.in);

    static String inputString(String hint) {
        print(hint);
        return next();
    }

    static String next() {
        return scan.next();
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
