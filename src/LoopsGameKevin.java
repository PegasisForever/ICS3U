import java.util.Scanner;

/**
 * A class to ask the user to enter rock, paper or scissors,
 * then randomly generate a rock, paper or scissors to determine
 * win or lose.
 * There will be 10 rounds, when each round ends, tell the user
 * if they won, lost or tied.
 * At the end of the game, display the number of rounds that the
 * user won, that the computer won, and the total number of
 * points for both the computer and the user.
 * Kevin JianQing Liu
 */
public class LoopsGameKevin {
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";
    static final int TOTAL_ROUNDS = 10;

    public static void main(String[] args) {
        int playerWonRounds = 0;
        int computerWonRounds = 0;

        for (int round = 1; round <= TOTAL_ROUNDS; round++) {
            String input = inputString(getPromoteInputText(round));
            while (!isValidInput(input)) {
                printlnError("\"" + input + "\" is not a valid input!");
                input = inputString(getPromoteInputText(round));
            }

            switch (getRandomIntFromInclusiveRange(0, 2)) {
                case 0:
                    println("You win!");
                    playerWonRounds++;
                    break;
                case 1:
                    println("Tied!");
                    break;
                case 2:
                    println("You lose!");
                    computerWonRounds++;
            }
        }

        closeScanner();

        int tiedRounds = TOTAL_ROUNDS - playerWonRounds - computerWonRounds;
        println(getGameSummaryText("You", playerWonRounds, computerWonRounds));
        println(getGameSummaryText("The computer", computerWonRounds, tiedRounds));
    }

    static boolean isValidInput(String input) {
        return input.equalsIgnoreCase("rock") ||
                input.equalsIgnoreCase("paper") ||
                input.equalsIgnoreCase("scissors");
    }

    static String getPromoteInputText(int currentRound) {
        return "Round " + currentRound + "/" + TOTAL_ROUNDS + ": [rock|paper|scissors] ";
    }

    static String getGameSummaryText(String name, int wonRounds, int tiedRounds) {
        return name + " won " + wonRounds + " rounds, got " + (wonRounds * 2 + tiedRounds) + " points.";
    }

    static int getRandomIntFromInclusiveRange(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    static Scanner scan = new Scanner(System.in);

    static String inputString(String hint) {
        print(hint);
        return next();
    }

    static String next() {
        return scan.next();
    }

    static void closeScanner() {
        scan.close();
    }

    static void println(Object obj) {
        System.out.println(obj);
    }

    static void printlnError(Object obj) {
        System.out.println(ANSI_RED + obj + ANSI_RESET);
    }

    static void print(Object obj) {
        System.out.print(obj);
    }
}
