import java.util.Scanner;

/**
 * Ask the user for the height and width and print the shape.
 * Kevin JianQing Liu
 */
public class DrawShapesKevin {
    public static void main(String[] args) throws Exception {
        int width = inputInt("Enter width: ");
        if (width < 2) throw new Exception("Width must be greater than or equal to 2.");
        int height = inputInt("Enter height: ");
        if (height < 0) throw new Exception("Height must not be negative.");
        closeScanner();

        println(repeat(("*" + repeatChar('%', width - 2) + "*"), height));
    }

    static Scanner scan = new Scanner(System.in);

    static String repeatChar(char c, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

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
