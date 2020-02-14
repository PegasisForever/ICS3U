import java.util.Scanner;

/*
 * Class to calculate and print bike purchase receipt for given price
 * Kevin JianQing Liu
 */
public class BikeReceiptKevin {
    public static void main(String[] args) {
        float price = inputFloat("Please enter the basic purchase price of the motorcycle:", "$");
        closeScanner();
        price = addDiscount(price, "Factory Discount", 0.21f);
        price = addDiscount(price, "Employee Discount", 0.15f);
        price = addDiscount(price, "Racer's Discount", 0.05f);
        price = addDiscount(price, "Taxes", -0.13f);
        println("────────────────────────────────");
        printSubtotal(price);
    }

    //discountPercentage: scale from -1 to 1, 1 means 100% discount, -1 means double the price
    static float addDiscount(float originalPrice, String discountName, float discountPercentage) {
        float discountedPrice = originalPrice * (1 - discountPercentage);
        float discount = discountedPrice - originalPrice;
        printEntry(discountName, discount);
        return discountedPrice;
    }

    static void printSubtotal(float price) {
        printEntry("Subtotal", price);
    }

    static void printEntry(String title, float value) {
        printf("%-20s%9.2f%n", title, value);
    }

    static Scanner scan = new Scanner(System.in);

    static float inputFloat(String hint, String unit) {
        println(hint);
        print(unit);
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

    static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
