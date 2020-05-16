import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Kevin JianQing Liu
 * Read a ticket from a file and print if it wina a price.
 */
public class TicketKevin {
    public static void main(String[] args) throws FileNotFoundException {
        String s;
        int digit1, digit2, digit3, digit4, digit5, digit6;
        Scanner input = new Scanner(new File("Digits.txt"));
        s = input.nextLine();
        digit1 = Integer.parseInt(s.substring(0, 1));
        digit2 = Integer.parseInt(s.substring(1, 2));
        digit3 = Integer.parseInt(s.substring(2, 3));
        digit4 = Integer.parseInt(s.substring(3, 4));
        digit5 = Integer.parseInt(s.substring(4, 5));
        digit6 = Integer.parseInt(s.substring(5, 6));
        if (digit1 + digit2 + digit3 == digit4 + digit5 + digit6) {
            System.out.println("This ticket wins a price!");
        } else {
            System.out.println("This ticket doesn't win a price!");
        }
    }
}