import java.util.Scanner;

/**
 * A class which accepts the number of hours that a car has parked in a parking lot
 * and then calculates the charge based on the schedule.
 * Kevin JianQing Liu
 */
public class CarParkingCostKevin {
    public static void main(String[] args) {
        int hour = inputInt("How many hours has the car parked in the parking lot? ");
        closeScanner();
        int cost = 0;

        cost += Math.max(0, Math.min(1, hour)) * 3;
        hour -= 1;

        cost += Math.max(0, Math.min(4, hour)) * 2;
        hour -= 4;

        cost += Math.max(0, hour);

        cost = Math.min(cost, 16);

        println("The cost is $" + cost + ".");
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
