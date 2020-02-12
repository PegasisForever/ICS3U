/*
 * Class to print numbers from 1 to 6 and their squares
 * Kevin JianQing Liu
 */
public class SquaresKevin {
    public static void main(String[] args) {
        println("Num   Square");
        println("***   ******");
        String format = "%-5d%5d%n";
        for (int i = 1; i <= 6; i++) {
            printf(format, i, i * i);
        }

        format = "%d*%d=%d%n";
        for (int i = 1; i <= 6; i++) {
            printf(format, i, i, i * i);
        }
    }

    static void println(Object obj) {
        System.out.println(obj);
    }

    static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
