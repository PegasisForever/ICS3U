import java.util.Scanner;

/**
 * Kevin JianQing Liu
 * Get 3 string from the user and print them in alphabetucal order.
 */

public class Alphabetical3Kevin {
    public static void main(String[] args) {
        String word1, word2, word3;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a word: ");
        word1 = input.nextLine();
        System.out.print("Enter a second word: ");
        word2 = input.nextLine();
        System.out.print("Enter a third word: ");
        word3 = input.nextLine();
        input.close();

        int w1cw2 = word1.compareTo(word2);
        int w1cw3 = word1.compareTo(word3);
        int w2cw1 = -w1cw2;
        int w2cw3 = word2.compareTo(word3);

        if (w1cw2 == 0 && w1cw3 == 0) {
            System.out.println("Words are equal.");
        } else {
            System.out.print("In alphabetical order:");
            if (w1cw2 < 0 && w1cw3 < 0) {
                System.out.print(" " + word1);
                if (w2cw3 < 0) {
                    System.out.println(" " + word2 + " " + word3);
                } else {
                    System.out.println(" " + word3 + " " + word2);
                }
            } else if (w2cw1 < 0 && w2cw3 < 0) {
                System.out.print(" " + word2);
                if (w1cw3 < 0) {
                    System.out.println(" " + word1 + " " + word3);
                } else {
                    System.out.println(" " + word3 + " " + word1);
                }
            } else {
                System.out.print(" " + word3);
                if (w1cw2 < 0) {
                    System.out.println(" " + word1 + " " + word2);
                } else {
                    System.out.println(" " + word2 + " " + word1);
                }
            }
        }
    }
}

