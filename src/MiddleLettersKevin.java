import java.util.Scanner;

/**
 * Kevin JianQing Liu
 * Print middle 3 letters if the input is odd, else print middle 2 letters.
 */
public class MiddleLettersKevin {
    public static void main(String[] args) {
        String phrase, threeLetters, twoLetters;
        int phraseLength;
        int mid;
        Scanner input = new Scanner(System.in);
        /* get string from user */
        System.out.print("Enter text that contains at least two characters: ");
        phrase = input.nextLine();
        input.close();
        /* determine middle of phrase */
        phraseLength = phrase.length();
        mid = phraseLength / 2;

        if (phraseLength % 2 == 1) {
            threeLetters = phrase.substring(mid - 1, mid + 2);
            System.out.println("Middle three characters are: " + threeLetters);
        } else {
            twoLetters = phrase.substring(mid - 1, mid + 1);
            System.out.println("Middle two characters are: " + twoLetters);
        }
    }
}