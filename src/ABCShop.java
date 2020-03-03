import java.util.Scanner;

public class ABCShop {
    public static void main(String[] args) {
        float[] coins = new float[]{2, 1, 0.25f, 0.1f, 0.05f, 0.01f};
        float change = inputFloat("Enter change:");
        for (float coin : coins) {
            int count = 0;
            while (change - coin >= 0) {
                change -= coin;
                count++;
            }
            println(count + " x " + coin);
        }
    }

    static Scanner scan = new Scanner(System.in);

    static float inputFloat(String hint) {
        println(hint);
        return nextFloat();
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
