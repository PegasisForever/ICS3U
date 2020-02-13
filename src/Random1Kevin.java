import java.util.Random;

/*
 * Class to print the result of two rounds of die rolling.
 * For each round, roll the die twice, print the value of each rolling and the sum.
 * For the second round, also print the larger value.
 * Kevin JianQing Liu
 */
public class Random1Kevin {
    public static void main(String[] args) {
        println("In run 1:");
        int run1First = getRandomDiceValue();
        int run1Second = getRandomDiceValue();
        println("The value of the first die is " + run1First);
        println("The value of the second die is " + run1Second);
        println("The sum of the two dice is " + (run1First + run1Second));

        println("In run 2:");
        int run2First = getRandomDiceValue();
        int run2Second = getRandomDiceValue();
        println("The value of the first die is " + run2First);
        println("The value of the second die is " + run2Second);
        println("The sum of the two dice is " + (run2First + run2Second));
        println("The larger of the two sums is " + Math.max(run2First, run1First));
    }

    static Random random = new Random();

    static int getRandomDiceValue() {
        return random.nextInt(6) + 1;
    }

    static void println(Object obj) {
        System.out.println(obj);
    }
}
