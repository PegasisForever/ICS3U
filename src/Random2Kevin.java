import java.util.Random;

/*
 * Class to randomly generate the report card for 4 courses
 * and print the average of 4 courses.
 * Kevin JianQing Liu
 */
public class Random2Kevin {
    public static void main(String[] args) {
        int mathMark = getRandomValue(91, 100);
        int physicsMark = getRandomValue(95, 100);
        int artMark = getRandomValue(61, 76);
        int computersMark = getRandomValue(88, 96);

        String format = "%-20s%s%n";
        printf(format, "Math", mathMark + "%");
        printf(format, "Physics", physicsMark + "%");
        printf(format, "Art", artMark + "%");
        printf(format, "Computer Studies", computersMark + "%");
        println("─────────────────────────────");
        printf(format, "Average:", getAverage(mathMark, physicsMark, artMark, computersMark) + "%");
    }

    static Random random = new Random();

    //includes from and to value
    static int getRandomValue(int from, int to) {
        return random.nextInt(to - from + 1) + from;
    }

    static float getAverage(int... array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return (float) sum / array.length;
    }

    static void println(Object obj) {
        System.out.println(obj);
    }

    static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
