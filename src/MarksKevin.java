import java.util.Scanner;

/**
 * A class to convert between letter grades and percentages.
 * Kevin JianQing Liu
 */
public class MarksKevin {
    public static void main(String[] args) {
        boolean isEnterGrade = inputString("Are you entering a letter grade? (y/n): ").equalsIgnoreCase("y");
        if (isEnterGrade) {
            char grade = inputString("Enter your letter grade: ").charAt(0);
            if (grade == 'A') {
                printMarkRange(80, 100);
            } else if (grade == 'B') {
                printMarkRange(70, 79);
            } else if (grade == 'C') {
                printMarkRange(60, 69);
            } else if (grade == 'D') {
                printMarkRange(50, 59);
            } else if (grade == 'E') {
                printMarkRange(35, 49);
            } else if (grade == 'F') {
                printMarkRange(0, 34);
            } else {
                println(grade + " is not a valid letter grade.");
            }
        } else {
            float percentage = inputFloat("Enter your percentage: ");
            if (percentage >= 80) {
                printGradeLetter('A');
            } else if (percentage >= 70) {
                printGradeLetter('B');
            } else if (percentage >= 60) {
                printGradeLetter('C');
            } else if (percentage >= 50) {
                printGradeLetter('D');
            } else if (percentage >= 35) {
                printGradeLetter('E');
            } else {
                printGradeLetter('F');
            }
        }
        closeScanner();
    }

    static void printMarkRange(int start, int end) {
        println("Your mark range is between " + start + " and " + end + ".");
    }

    static void printGradeLetter(char letter) {
        println("Your letter grade is " + letter + ".");
    }

    static Scanner scan = new Scanner(System.in);

    static String inputString(String hint) {
        print(hint);
        return next();
    }

    static float inputFloat(String hint) {
        print(hint);
        return nextFloat();
    }

    static String next() {
        return scan.next();
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

    static void print(Object obj) {
        System.out.print(obj);
    }
}
