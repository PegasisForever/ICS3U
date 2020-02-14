import java.util.Scanner;

/*
 * Class to ask a user his/her name, final mark and calculate the average.
 * Kevin JianQing Liu
 */
public class AverageKevin {
    public static void main(String[] args) {
        String name = inputString("Enter your name:");
        float markSum = 0;
        int markCount = 3;
        for (int i = 1; i <= markCount; i++) {
            markSum += inputFloat("Enter your final mark: (" + i + "/" + markCount + ")");
        }
        closeScanner();

        println(name + ", your average is " + (markSum / markCount) + ".");
    }

    static Scanner scan = new Scanner(System.in);

    static String inputString(String hint) {
        println(hint);
        return nextLine();
    }

    static float inputFloat(String hint) {
        println(hint);
        return nextFloat();
    }

    static String nextLine() {
        return scan.nextLine();
    }

    static float nextFloat() {
        return scan.nextFloat();
    }

    static void closeScanner() {
        scan.close();
    }

    static void println(Object obj) {
        System.out.println(obj);
    }
}
