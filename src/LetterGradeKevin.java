import java.util.Scanner;

/**
 * A class that asks the user for a letter grade (A-F) and
 * output the mark range corresponding to that grade.
 * Kevin JianQing Liu
 */
public class LetterGradeKevin {
    public static void main(String[] args) {
        char grade = inputString("Enter your letter grade: ").charAt(0);
        closeScanner();
        switch (grade) {
            case 'A':
                printMarkRange(80, 100);
                break;
            case 'B':
                printMarkRange(70, 79);
                break;
            case 'C':
                printMarkRange(60, 69);
                break;
            case 'D':
                printMarkRange(50, 59);
                break;
            case 'E':
                printMarkRange(35, 49);
                break;
            case 'F':
                printMarkRange(0, 34);
                break;
            default:
                println(grade + " is not a valid letter grade.");
                break;
        }
    }

    static void printMarkRange(int start, int end) {
        println("Your mark range is between " + start + " and " + end + ".");
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
