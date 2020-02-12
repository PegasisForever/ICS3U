/*
 * Class to print a recipe
 * Kevin JianQing Liu
 */
public class RecipeKevin {
    public static void main(String[] args) {
        printRow("Ingredients", "2 servings", "4 servings", "6 servings");
        printRow("Dry Pancake Mix (cups)", "0.75", "1.5", "2.25");
        printRow("Flax Seed Meal (cups)", "0.25", "0.50", "0.75");
        printRow("Skim Milk (cups)", "0.5", "1.0", "1.5");
        printRow("Eggs", "1", "2", "3");
        printRow("Blueberries (cups)", "0.5", "1.0", "1.5");

    }

    static void printRow(String a, String b, String c, String d) {
        format("%-25s%15s%15s%15s%n", a, b, c, d);
    }

    static void format(String format, Object... args) {
        System.out.format(format, args);
    }
}
