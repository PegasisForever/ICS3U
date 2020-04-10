import java.util.Scanner;

/**
 * A class asks a number between 50-100 and print number
 * from 100 down to that number step 5.
 * Kevin JianQing Liu
 */
public class Count5V2Kevin {
    public static void main(String[] args) {
        int stopNum = inputInt("When do you want to stop? ");
        closeScanner();

        for (int i = 100; i >= stopNum; i -= 5) println(i);
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
