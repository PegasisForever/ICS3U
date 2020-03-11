import java.util.Scanner;

/**
 * A class to display a random math addition question, asks the user for
 * the answer and shows if it is correct or not. If it is not, show the
 * correct answer.
 * Kevin JianQing Liu
 */
public class MathForKidsKevin {
    public static void main(String[] args) {
        int term1 = getRandomIntFromInclusiveRange(10, 99);
        int term2 = getRandomIntFromInclusiveRange(0, 9);
        int correctAnswer = term1 + term2;
        int userAnswer = inputInt(term1 + " + " + term2 + " = ");
        closeScanner();

        if (userAnswer == correctAnswer) {
            switch (getRandomIntFromInclusiveRange(0, 2)) {
                case 0:
                    println("You are correct!");
                    break;
                case 1:
                    println("Good job!");
                    break;
                case 2:
                    println("Excellent!");
            }
        } else {
            switch (getRandomIntFromInclusiveRange(0, 2)) {
                case 0:
                    println("The correct answer is " + correctAnswer + ", nice try!");
                    break;
                case 1:
                    println("The correct answer is " + correctAnswer + ", try harder!");
                    break;
                case 2:
                    println("The correct answer is " + correctAnswer + ", don't give up!");
            }
        }
    }

    static int getRandomIntFromInclusiveRange(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
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
