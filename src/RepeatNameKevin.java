import java.util.Scanner;

/**
 * A class prints user's name for a certain times.
 * Kevin JianQing Liu
 */
public class RepeatNameKevin {
    public static void main(String[] args) {
        String name = inputString("Enter your name: ");
        int repeatTimes = inputInt("How many times you want your name to be repeated? ");
        closeScanner();

        for (int i = 0; i < repeatTimes; i++) {
            print(name + "*");
        }
    }

    static Scanner scan = new Scanner(System.in);

    static int inputInt(String hint) {
        print(hint);
        return nextInt();
    }

    static String inputString(String hint) {
        print(hint);
        return next();
    }

    static int nextInt() {
        return scan.nextInt();
    }

    static String next() {
        return scan.next();
    }

    static void closeScanner() {
        scan.close();
    }

    static void print(Object obj) {
        System.out.print(obj);
    }
}
