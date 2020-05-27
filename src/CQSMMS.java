import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Kevin JianQing Liu
 * Class for class, quiz, student and mark management system.
 */
public class CQSMMS {
    /* Variable Table
     * Scanner scan - system input scanner
     * ArrayList<String> classes - list of all classes
     * ArrayList<ArrayList<String>> studentLists - list of (list of students) in each class
     * ArrayList<ArrayList<String>> quizLists - list of (list of quizzes) in each class
     * ArrayList<ArrayList<ArrayList<Float>>> quizMarksForStudentsList - list of (list of (list of marks) for each student) in each class
     * String[] COMMANDS - array of all commands
     * String[] COMMAND_HELP_TEXTS -array of help text for all of the commands
     * int selectedClassIndex - index of selected class
     * int selectedQuizIndex - index of selected quiz
     */

    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> classes = new ArrayList<>();
    static ArrayList<ArrayList<String>> studentLists = new ArrayList<>();
    static ArrayList<ArrayList<String>> quizLists = new ArrayList<>();
    static ArrayList<ArrayList<ArrayList<Float>>> quizMarksForStudentsList = new ArrayList<>();
    static final String[] COMMANDS = new String[]{
            "help",
            "exit",
            "selectclass",
            "addclass",
            "lsclass",
            "rmclass",
            "addstudent",
            "lsstudent",
            "rmstudent",
            "selectquiz",
            "addquiz",
            "lsquiz",
            "rmquiz",
            "setmark",
            "getmark",
            "report",
            "save",
            "load",
    };
    static final String[] COMMAND_HELP_TEXTS = new String[]{
            "Usage:\n" +
                    "   help - list all commands\n" +
                    "   help <command> - view help message of this command",
            "Usage:\n" +
                    "   exit - exit this program",
            "Usage:\n" +
                    "   selectclass <name> - selects this class\n" +
                    "   selectclass - deselects the current class",
            "Usage:\n" +
                    "   addclass <class>... - add class(es) with provided name(s)",
            "Usage:\n" +
                    "   lsclass - list all added classes",
            "Usage:\n" +
                    "   rmclass <class>... - remove class(es) with provided name(s)",
            "Usage:\n" +
                    "   addstudent <student>... - add student(s) with provided name(s) in selected class",
            "Usage:\n" +
                    "   lsstudent - list all added students in selected class",
            "Usage:\n" +
                    "   rmstudent <student>... - remove class(es) with provided name(s) in selected class",
            "Usage:\n" +
                    "   selectquiz <quiz> - selects this quiz in selected class\n" +
                    "   selectquiz - deselects the current quiz",
            "Usage:\n" +
                    "   addquiz <quiz>... - add quiz(zes) with provided name(s) in selected class",
            "Usage:\n" +
                    "   lsquiz - list all added quizzes in selected class",
            "Usage:\n" +
                    "   rmquiz <quiz>... - remove quiz(zes) with provided name(s) in selected class",
            "Usage:\n" +
                    "   setmark <name> <mark> - set mark for this student in selected quiz",
            "Usage:\n" +
                    "   getmark <name> - get mark for this student in selected quiz",
            "Usage:\n" +
                    "   report - generate report for all students in selected class\n" +
                    "   report <name> - generate report for this student in selected class",
            "Usage:\n" +
                    "   save <file name> - save all the data to this file",
            "Usage:\n" +
                    "   load <file name> - load all the data from this file",
    };
    static int selectedClassIndex = -1;
    static int selectedQuizIndex = -1;

    public static void main(String[] args) {
        println("Welcome to the class, quiz, student and mark management system by Kevin JianQing Liu, enter \"help\" for a list of available commands and \"exit\" to exit the program.");

        mainLoop:
        while (true) {
            // get & parse the inputted command
            String[] input = parseCommand(inputLine(getPromoText()));
            if (input == null || input.length == 0) continue;

            // call the corresponding method by the first argument (the command name)
            switch (input[0]) {
                case "exit":
                    println("Bye.");
                    closeScanner();
                    break mainLoop;
                case "help":
                    helpCommand(input);
                    break;
                case "selectclass":
                    selectClassCommand(input);
                    break;
                case "addclass":
                    addClassCommand(input);
                    break;
                case "lsclass":
                    listClassCommand(input);
                    break;
                case "rmclass":
                    removeClassCommand(input);
                    break;
                case "addstudent":
                    addStudentCommand(input);
                    break;
                case "lsstudent":
                    listStudentCommand(input);
                    break;
                case "rmstudent":
                    removeStudentCommand(input);
                    break;
                case "addquiz":
                    addQuizCommand(input);
                    break;
                case "lsquiz":
                    listQuizCommand(input);
                    break;
                case "rmquiz":
                    removeQuizCommand(input);
                    break;
                case "selectquiz":
                    selectQuizCommand(input);
                    break;
                case "setmark":
                    setMarkCommand(input);
                    break;
                case "getmark":
                    getMarkCommand(input);
                    break;
                case "report":
                    reportCommand(input);
                    break;
                case "save":
                    saveCommand(input);
                    break;
                case "load":
                    loadCommand(input);
                    break;
                default:
                    printlnError("Unknown command: \"" + input[0] + "\".");
            }
        }
    }

    /* Get enter command promotion text based on selected class and quiz.
     * pre: nothing
     * post: the promotion String
     */
    static String getPromoText() {
        /* Variable Table
         * String prom - the promotion text before the place where you enter the text
         */

        String prom = "> ";

        if (selectedClassIndex != -1 && selectedQuizIndex != -1) {
            prom = "(" + classes.get(selectedClassIndex) + "/" + quizLists.get(selectedClassIndex).get(selectedQuizIndex) + ") " + prom;
        } else if (selectedClassIndex != -1) {
            prom = "(" + classes.get(selectedClassIndex) + ") " + prom;
        }
        return prom;
    }

    /* Parse a command user entered based on quotation marks and escape characters.
     * pre: the String command
     * post: a list of String arguments
     */
    static String[] parseCommand(String cmd) {
        /* Variable Table
         * ArrayList<String> args - parsed arguments from the command
         * boolean literal - is current char in string literal
         * boolean escape - is current char escaped
         * StringBuilder buffer - buffer for current parsing argument
         */

        ArrayList<String> args = new ArrayList<>();
        boolean literal = false;
        boolean escape = false;
        StringBuilder buffer = new StringBuilder();

        for (char c : cmd.toCharArray()) {
            if (escape) {
                buffer.append(c);
                escape = false;
            } else if (c == '\\') {
                // if the current char is \, the following char will be append to buffer without processing
                escape = true;
            } else if (c == '"') {
                literal = !literal;
            } else if (!literal && c == ' ') {
                // a space not in string literal divides arguments
                if (buffer.length() > 0) {
                    // prevents multiple continuous spaces
                    args.add(buffer.toString());
                    buffer.setLength(0);
                }
            } else {
                buffer.append(c);
            }
        }
        // the last argument
        if (buffer.length() > 0) {
            args.add(buffer.toString());
        }

        if (literal || escape) {
            printlnError("Can't parse command.");
            return null;
        } else {
            return args.toArray(new String[0]);
        }
    }

    /* Check if given arguments matches expected format
     * pre: the arguments (array of Strings), does the format include variable arguments (boolean),
     *      expected types for each arguments ('s' for string, 'f' for float)
     * post: does given arguments matches expected format (boolean)
     */
    static boolean argsMatch(String[] args, boolean isVariableArg, char... types) {
        try {
            if (isVariableArg) {
                // check if args (exclude the last one) have expected type
                for (int i = 0; i < types.length - 1; i++) {
                    if (types[i] == 'f') {
                        Float.parseFloat(args[i + 1]);
                    }
                }

                // check if the remaining arguments all have the same expected type
                char variableArgType = types[types.length - 1];
                for (int i = types.length; i < args.length; i++) {
                    if (variableArgType == 'f') {
                        Float.parseFloat(args[i]);
                    }
                }
            } else {
                // check if args have expected amount
                if (args.length - 1 != types.length) return false;

                // check if args have expected type
                for (int i = 0; i < types.length; i++) {
                    if (types[i] == 'f') {
                        Float.parseFloat(args[i + 1]);
                    }
                }
            }

            return true;
        } catch (Exception e) {
            // NumberFormatException and IndexOutOfBoundsException will be catched here
            return false;
        }
    }

    /* Print help text for given command
     * pre: the command name (String)
     * post: nothing
     */
    static void printHelpText(String commandName) {
        /* Variable Table
         * int commandIndex - index of the command in the commands array
         */

        // find the index of the command in parallel array
        int commandIndex = -1;
        for (int i = 0; i < COMMANDS.length; i++) {
            if (COMMANDS[i].equals(commandName)) {
                commandIndex = i;
                break;
            }
        }

        if (commandIndex == -1) {
            printlnError("Unknown command: \"" + commandName + "\".");
        } else {
            println(COMMAND_HELP_TEXTS[commandIndex]);
        }
    }

    /* The help command
     *     help - list all commands
     *     help <command> - view help message of this command
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void helpCommand(String[] args) {
        if (argsMatch(args, false)) {
            // help - list all commands
            print("All commands:");
            for (String command : COMMANDS) {
                print(" " + command);
            }
            print("\n");
            println("Use help <command> to get more information.");
        } else if (argsMatch(args, false, 's')) {
            // help <command> - view help message of this command
            printHelpText(args[1]);
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The select class command
     *     selectclass <name> - selects this class
     *     selectclass - deselects the current class
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void selectClassCommand(String[] args) {
        if (argsMatch(args, false)) {
            // selectclass - deselects the current class
            selectedClassIndex = -1;
            selectedQuizIndex = -1;
        } else if (argsMatch(args, false, 's')) {
            // selectclass <name> - selects this class
            int classIndex = classes.indexOf(args[1]);
            if (classIndex == -1) {
                printlnError("No class named \"" + args[1] + "\".");
            } else {
                selectedClassIndex = classIndex;
                selectedQuizIndex = -1;
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The add class command
     *     addclass <class>... - add class(es) with provided name(s)
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void addClassCommand(String[] args) {
        if (argsMatch(args, true, 's')) {
            // addclass <class>... - add class(es) with provided name(s)
            for (int i = 1; i < args.length; i++) {
                // add the class if it didn't exist, also add corresponding items in other parallel lists
                String className = args[i];
                if (classes.contains(className)) {
                    printlnError("Class \"" + className + "\" already added.");
                } else {
                    classes.add(className);
                    studentLists.add(new ArrayList<>());
                    quizLists.add(new ArrayList<>());
                    quizMarksForStudentsList.add(new ArrayList<>());
                }
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The list class command
     *     lsclass - list all added classes
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void listClassCommand(String[] args) {
        if (argsMatch(args, false)) {
            // lsclass - list all added classes
            for (String className : classes) {
                println(className);
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The remove class command
     *     rmclass <class>... - remove class(es) with provided name(s)
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void removeClassCommand(String[] args) {
        if (argsMatch(args, true, 's')) {
            // rmclass <class>... - remove class(es) with provided name(s)

            /* Variable Table
             * String selectedClassName - save current selected class name because remove classes may change this index
             */

            String selectedClassName = selectedClassIndex == -1 ? null : classes.get(selectedClassIndex);

            for (int i = 1; i < args.length; i++) {
                // remove the class if it did exist, also remove corresponding items in other parallel lists
                String className = args[i];
                int removeIndex = classes.indexOf(className);
                if (removeIndex != -1) {
                    classes.remove(removeIndex);
                    studentLists.remove(removeIndex);
                    quizLists.remove(removeIndex);
                } else {
                    printlnError("Class \"" + className + "\" doesn't exist.");
                }
            }

            selectedClassIndex = classes.indexOf(selectedClassName);
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The add student command
     *     addstudent <student>... - add student(s) with provided name(s) in selected class
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void addStudentCommand(String[] args) {
        if (selectedClassIndex == -1) {
            printlnError("No class selected.");
            return;
        }

        if (argsMatch(args, true, 's')) {
            //addstudent <student>... - add student(s) with provided name(s) in selected class

            /* Variable Table
             * ArrayList<String> students - student list in current selected class
             * ArrayList<ArrayList<Float>> quizMarksForStudents - list of (list of marks) for each student in current selected class
             */
            ArrayList<String> students = studentLists.get(selectedClassIndex);
            ArrayList<ArrayList<Float>> quizMarksForStudents = quizMarksForStudentsList.get(selectedClassIndex);
            for (int i = 1; i < args.length; i++) {
                // add the student if it didn't exist, also add corresponding items in other parallel lists
                String studentName = args[i];
                if (students.contains(studentName)) {
                    printlnError("Student \"" + studentName + "\" already added.");
                } else {
                    students.add(studentName);

                    // create a list of empty mark
                    int quizCount = quizLists.get(selectedClassIndex).size();
                    ArrayList<Float> emptyMarks = new ArrayList<>(quizCount);
                    for (int j = 0; j < quizCount; j++) {
                        emptyMarks.add(null);
                    }

                    quizMarksForStudents.add(emptyMarks);
                }
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The remove student command
     *     rmstudent <student>... - remove class(es) with provided name(s) in selected class
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void removeStudentCommand(String[] args) {
        if (selectedClassIndex == -1) {
            printlnError("No class selected.");
            return;
        }

        if (argsMatch(args, true, 's')) {
            // rmstudent <student>... - remove class(es) with provided name(s) in selected class

            /* Variable Table
             * ArrayList<String> students - student list in current selected class
             * ArrayList<ArrayList<Float>> quizMarksForStudents - list of (list of marks) for each student in current selected class
             */
            ArrayList<String> students = studentLists.get(selectedClassIndex);
            ArrayList<ArrayList<Float>> quizMarksForStudents = quizMarksForStudentsList.get(selectedClassIndex);
            for (int i = 1; i < args.length; i++) {
                // remove the student if it did exist, also remove corresponding items in other parallel lists
                String studentName = args[i];
                int removeIndex = students.indexOf(studentName);
                if (removeIndex != -1) {
                    students.remove(removeIndex);
                    quizMarksForStudents.remove(removeIndex);
                } else {
                    printlnError("Student \"" + studentName + "\" doesn't exist.");
                }
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The list student command
     *     lsstudent - list all added students in selected class
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void listStudentCommand(String[] args) {
        if (selectedClassIndex == -1) {
            printlnError("No class selected.");
            return;
        }

        if (argsMatch(args, false)) {
            //  lsstudent - list all added students in selected class
            for (String studentName : studentLists.get(selectedClassIndex)) {
                println(studentName);
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The add quiz command
     *     addquiz <quiz>... - add quiz(zes) with provided name(s) in selected class
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void addQuizCommand(String[] args) {
        if (selectedClassIndex == -1) {
            printlnError("No class selected.");
            return;
        }

        if (argsMatch(args, true, 's')) {
            // addquiz <quiz>... - add quiz(zes) with provided name(s) in selected class

            /* Variable Table
             * ArrayList<String> quizzes - quiz list in current selected class
             * ArrayList<ArrayList<Float>> quizMarksForStudents - list of (list of marks) for each student in current selected class
             */
            ArrayList<String> quizzes = quizLists.get(selectedClassIndex);
            ArrayList<ArrayList<Float>> quizMarksForStudents = quizMarksForStudentsList.get(selectedClassIndex);
            for (int i = 1; i < args.length; i++) {
                // add the quiz if it didn't exist
                String quizName = args[i];
                if (quizzes.contains(quizName)) {
                    printlnError("Quiz \"" + quizName + "\" already added.");
                } else {
                    quizzes.add(quizName);

                    // add empty marks for every student
                    for (ArrayList<Float> quizMarks : quizMarksForStudents) {
                        quizMarks.add(null);
                    }
                }
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The remove quiz command
     *     rmquiz <quiz>... - remove quiz(zes) with provided name(s) in selected class
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void removeQuizCommand(String[] args) {
        if (selectedClassIndex == -1) {
            printlnError("No class selected.");
            return;
        }

        if (argsMatch(args, true, 's')) {
            // rmquiz <quiz>... - remove quiz(zes) with provided name(s) in selected class

            /* Variable Table
             * ArrayList<String> quizzes - quiz list in current selected class
             * ArrayList<ArrayList<Float>> quizMarksForStudents - list of (list of marks) for each student in current selected class
             * String selectedQuizName - save current selected quiz name because remove quizzes may change this index
             */
            ArrayList<String> quizzes = quizLists.get(selectedClassIndex);
            ArrayList<ArrayList<Float>> quizMarksForStudents = quizMarksForStudentsList.get(selectedClassIndex);
            String selectedQuizName = selectedQuizIndex == -1 ? null : quizzes.get(selectedQuizIndex);
            for (int i = 1; i < args.length; i++) {
                // remove the quiz if it did exist, and remove the corresponding items in other parallel lists
                String quizName = args[i];
                int removeIndex = quizzes.indexOf(quizName);
                if (removeIndex != -1) {
                    quizzes.remove(removeIndex);

                    for (ArrayList<Float> quizMarks : quizMarksForStudents) {
                        quizMarks.remove(removeIndex);
                    }
                } else {
                    printlnError("Quiz \"" + quizName + "\" doesn't exist.");
                }
            }

            selectedQuizIndex = quizzes.indexOf(selectedQuizName);
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The list quiz command
     *     lsquiz - list all added quizzes in selected class
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void listQuizCommand(String[] args) {
        if (selectedClassIndex == -1) {
            printlnError("No class selected.");
            return;
        }

        if (argsMatch(args, false)) {
            // lsquiz - list all added quizzes in selected class

            for (String quizName : quizLists.get(selectedClassIndex)) {
                println(quizName);
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The select quiz command
     *     selectquiz <quiz> - selects this quiz in selected class
     *     selectquiz - deselects the current quiz
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void selectQuizCommand(String[] args) {
        if (selectedClassIndex == -1) {
            printlnError("No class selected.");
            return;
        }

        if (argsMatch(args, false)) {
            // selectquiz - deselects the current quiz
            selectedQuizIndex = -1;
        } else if (argsMatch(args, false, 's')) {
            // selectquiz <quiz> - selects this quiz in selected class
            int quizIndex = quizLists.get(selectedClassIndex).indexOf(args[1]);
            if (quizIndex == -1) {
                printlnError("No quiz named \"" + args[1] + "\".");
            } else {
                selectedQuizIndex = quizIndex;
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The set mark command
     *     setmark <name> <mark> - set mark for this student in selected quiz
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void setMarkCommand(String[] args) {
        if (selectedClassIndex == -1 || selectedQuizIndex == -1) {
            printlnError("No class or quiz selected");
            return;
        }

        if (argsMatch(args, false, 's', 'f')) {
            // setmark <name> <mark> - set mark for this student in selected quiz

            /* Variable Table
             * String studentName - name of the student that set the mark
             * float mark - the mark to be set
             * int studentIndex - the index of the student in the student list in current selected class
             * ArrayList<ArrayList<Float>> quizMarksForStudents - list of (list of marks) for each student in current selected class
             */
            String studentName = args[1];
            float mark = Float.parseFloat(args[2]);

            // find the student's index
            int studentIndex = studentLists.get(selectedClassIndex).indexOf(studentName);
            if (studentIndex == -1) {
                printlnError("Student \"" + studentName + "\" doesn't exist.");
                return;
            }

            // set mark for him
            ArrayList<ArrayList<Float>> quizMarksForStudents = quizMarksForStudentsList.get(selectedClassIndex);
            quizMarksForStudents.get(studentIndex).set(selectedQuizIndex, mark);
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The get mark command
     *     getmark <name> - get mark for this student in selected quiz
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void getMarkCommand(String[] args) {
        if (selectedClassIndex == -1 || selectedQuizIndex == -1) {
            printlnError("No class or quiz selected.");
            return;
        }

        if (argsMatch(args, false, 's')) {
            //getmark <name> - get mark for this student in selected quiz

            /* Variable Table
             * String studentName - name of the student that get the mark
             * int studentIndex - the index of the student in the student list in current selected class
             * ArrayList<ArrayList<Float>> quizMarksForStudents - list of (list of marks) for each student in current selected class
             * Float mark - nullable mark for the student
             */

            String studentName = args[1];

            // find the student's index
            int studentIndex = studentLists.get(selectedClassIndex).lastIndexOf(studentName);
            if (studentIndex == -1) {
                printlnError("Student \"" + studentName + "\" doesn't exist.");
                return;
            }

            // get mark for him, print in human-readable format
            ArrayList<ArrayList<Float>> quizMarksForStudents = quizMarksForStudentsList.get(selectedClassIndex);
            Float mark = quizMarksForStudents.get(studentIndex).get(selectedQuizIndex);
            println(nullableFloatToString(mark));
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The report command
     *     report - generate report for all students in selected class
     *     report <name> - generate report for this student in selected class
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void reportCommand(String[] args) {
        /* Variable Table
         * ArrayList<String> quizzes - quiz list in current selected class
         * ArrayList<String> students - student list in current selected class
         * StringBuilder formatSb - format string for the table body row
         * StringBuilder headerFormatSb - format string for the table header row
         * ArrayList<ArrayList<Float>> quizMarksForStudents - list of (list of marks) for each student in current selected class
         * Object[] formatArgs - object array full of strings that is going to passed into the format method
         */

        if (selectedClassIndex == -1) {
            printlnError("No class selected.");
            return;
        }

        ArrayList<String> quizzes = quizLists.get(selectedClassIndex);
        ArrayList<String> students = studentLists.get(selectedClassIndex);
        if (quizzes.isEmpty() || students.isEmpty()) return;

        // generate base format string for both personal report and class report
        StringBuilder formatSb = new StringBuilder();
        StringBuilder headerFormatSb = new StringBuilder();
        ArrayList<ArrayList<Float>> quizMarksForStudents = quizMarksForStudentsList.get(selectedClassIndex);
        for (String quizName : quizzes) {
            int columnLength = Math.max(7, quizName.length() + 2);
            formatSb.append("%").append(columnLength).append("s");
            headerFormatSb.append("%").append(columnLength).append("s");
        }
        formatSb.append("\n");
        headerFormatSb.append("\n");

        if (argsMatch(args, false, 's')) {
            // report <name> - generate report for this student in selected class

            // find student's index
            String studentName = args[1];
            int studentIndex = students.indexOf(studentName);
            if (studentIndex == -1) {
                printlnError("Student \"" + studentName + "\" doesn't exist.");
                return;
            }

            // print header
            format(headerFormatSb.toString(), quizzes.toArray());

            // convert nullable floats to strings
            Object[] formatArgs = quizMarksForStudents.get(studentIndex).toArray();
            for (int j = 0; j < formatArgs.length; j++) {
                formatArgs[j] = nullableFloatToString((Float) formatArgs[j]);
            }
            // print marks
            format(formatSb.toString(), formatArgs);
        } else if (argsMatch(args, false)) {
            // report - generate report for all students in selected class

            // add name column to the base format string
            int nameColumnLength = 7;
            for (String studentName : students) {
                nameColumnLength = Math.max(nameColumnLength, studentName.length() + 2);
            }
            formatSb.insert(0, "%-" + nameColumnLength + "s");
            headerFormatSb.insert(0, repeatChar(' ', nameColumnLength));

            // print header
            format(headerFormatSb.toString(), quizzes.toArray());

            for (int i = 0; i < students.size(); i++) {
                // convert nullable floats to strings and add student name to the top of the array
                Object[] quizMarks = quizMarksForStudents.get(i).toArray();
                Object[] formatArgs = new Object[quizMarks.length + 1];
                System.arraycopy(quizMarks, 0, formatArgs, 1, quizMarks.length);
                formatArgs[0] = students.get(i);
                for (int j = 1; j < formatArgs.length; j++) {
                    formatArgs[j] = nullableFloatToString((Float) formatArgs[j]);
                }

                //print marks for this student
                format(formatSb.toString(), formatArgs);
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* The save command
     *     save <file name> - save all the data to this file
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void saveCommand(String[] args) {
        if (argsMatch(args, false, 's')) {
            // save <file name> - save all the data to this file

            /* Variable Table
             * File file - the file where the data is going to be saved at
             * PrintWriter fileWriter - print writer for printing string into the file
             */
            try {
                // create file
                File file = new File(args[1]);
                file.createNewFile();
                PrintWriter fileWriter = new PrintWriter(file);

                // write serialized data to this file
                fileWriter.println(serialize(quizMarksForStudentsList));
                fileWriter.println(serialize(classes));
                fileWriter.println(serialize(studentLists));
                fileWriter.println(serialize(quizLists));

                fileWriter.close();
            } catch (IOException e) {
                printlnError("Failed to save file: " + e.getMessage());
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* Serialize arraylist of Strings and Floats, supports nested arraylists
     * pre: arraylist to serialize
     * post: serialized arraylist (StringBuilder)
     */
    static StringBuilder serialize(ArrayList<?> list) {
        /* Variable Table
         * StringBuilder sb - the serialized array list
         * int type - the type pf this array list (0: empty list, 1: list of strings,
         *            2: list of nullable floats, 3: list of lists)
         */

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        // determine the type of the list
        int type;
        if (list.isEmpty()) {
            type = 0; // empty list
        } else if (list.get(0) instanceof String) {
            type = 1; // list of strings
        } else if (list.get(0) instanceof ArrayList) {
            type = 3; // list of lists
        } else {
            type = 2; // list of nullable floats
        }
        sb.append(type);

        switch (type) {
            case 0:
                break;
            case 1:
                for (Object str : list) {
                    sb.append('"');
                    // escape special characters
                    sb.append(((String) str).replace("\\", "\\\\").replace("\"", "\\\""));
                    sb.append("\",");
                }
                break;
            case 2:
                for (Object num : list) {
                    if (num == null) {
                        sb.append("null");
                    } else {
                        sb.append((float) num);
                    }
                    sb.append(',');
                }
                break;
            case 3:
                for (Object l : list) {
                    // recursive call
                    sb.append(serialize((ArrayList<?>) l));
                    sb.append(',');
                }
                break;
        }
        sb.append(']');

        return sb;
    }

    /* The load command
     *     load <file name> - load all the data from this file
     * pre: arguments (array of String)
     * post: Nothing
     */
    static void loadCommand(String[] args) {
        if (argsMatch(args, false, 's')) {
            // load <file name> - load all the data from this file

            /* Variable Table
             * File file - the file where the data is going to be read from
             * Scanner fileScanner - file content scanner
             */
            try {
                // open the file
                File file = new File(args[1]);
                Scanner fileScanner = new Scanner(file);

                // read & parse the file
                quizMarksForStudentsList = parse(fileScanner.nextLine());
                classes = parse(fileScanner.nextLine());
                studentLists = parse(fileScanner.nextLine());
                quizLists = parse(fileScanner.nextLine());

                fileScanner.close();

                selectedClassIndex = -1;
                selectedQuizIndex = -1;
            } catch (IOException e) {
                printlnError("Failed to load file: " + e.getMessage());
            }
        } else {
            printlnError("Arguments don't match.");
            printHelpText(args[0]);
        }
    }

    /* Parse arraylist of Strings and Floats, supports nested arraylists
     * pre: String to parse
     * post: parsed arraylist
     */
    @SuppressWarnings("unchecked")
    static <T> ArrayList<T> parse(String str) {
        /* Variable Table
         * char[] charArray - the char array converted from the input string
         * int type - the type of this array list
         * ArrayList result - the parsed array list
         */

        char[] charArray = str.toCharArray();
        // the second char in the string is the type int, -48 converts number in char to int
        int type = charArray[1] - 48;

        ArrayList<T> result = new ArrayList<>();
        switch (type) {
            case 0: // empty list
                break;
            case 1: // list of strings
                // similar to the command parser method
                boolean literal = false;
                boolean escape = false;
                StringBuilder buffer = new StringBuilder();
                for (int i = 2; i < charArray.length; i++) {
                    char c = charArray[i];
                    if (escape) {
                        buffer.append(c);
                        escape = false;
                    } else if (c == '\\') {
                        // if the current char is \, the following char will be append to buffer without processing
                        escape = true;
                    } else if (c == '"') {
                        literal = !literal;
                    } else if (!literal && c == ',') {
                        // a comma not in string literal divides strings
                        result.add((T) buffer.toString());
                        buffer.setLength(0);
                    } else {
                        buffer.append(c);
                    }
                }
                break;
            case 2: // list of nullable floats
                buffer = new StringBuilder();
                for (int i = 2; i < charArray.length; i++) {
                    char c = charArray[i];
                    if (c == ',') {
                        // add buffer to result list and clear buffer
                        String bufferStr = buffer.toString();
                        if (bufferStr.equals("null")) {
                            result.add(null);
                        } else {
                            result.add((T) Float.valueOf(Float.parseFloat(bufferStr)));
                        }
                        buffer.setLength(0);
                    } else {
                        buffer.append(c);
                    }
                }
                break;
            case 3: // list of lists
                int extraLeftBrackets = 1;
                literal = false;
                escape = false;
                buffer = new StringBuilder();
                for (int i = 2; i < charArray.length; i++) {
                    char c = charArray[i];
                    if (c == ',' && extraLeftBrackets == 1 && !literal && !escape) {
                        // when left-right brackets are balanced, char ',' is not in
                        // string literal or escape, it separates two lists
                        // recursive call
                        result.add((T) parse(buffer.toString()));
                        buffer.setLength(0);
                    } else {
                        buffer.append(c);

                        // count left & right brackets
                        if (c == '[' && !literal && !escape) {
                            extraLeftBrackets++;
                        } else if (c == ']' && !literal && !escape) {
                            extraLeftBrackets--;
                        }

                        // track escape & string literal state
                        if (escape) {
                            escape = false;
                        } else if (c == '\\') {
                            escape = true;
                        } else if (c == '"') {
                            literal = !literal;
                        }
                    }
                }
        }

        return result;
    }

    /* Convert a nullable float to String. If is null, return N/A.
     * pre: the float
     * post: converted String
     */
    static String nullableFloatToString(Float f) {
        if (f == null) {
            return "N/A";
        } else {
            return String.format("%.2f", f);
        }
    }

    /* repeat a char for n times
     * pre: the char to repeat and how many times
     * post: String of repeated char
     */
    static String repeatChar(char c, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(c);
        }
        return sb.toString();
    }


    /* Print out hint and get string input of the whole line from the user
     * pre: a String as hint
     * post: the user input (String)
     */
    static String inputLine(String hint) {
        print(hint);
        return scan.nextLine();
    }

    /* Close the scanner
     * pre: nothing
     * post: nothing
     */
    static void closeScanner() {
        scan.close();
    }

    /* Print out an object and append a new line
     * pre: an object to print
     * post: nothing
     */
    static void println(Object obj) {
        System.out.println(obj);
    }

    /* Print out an object in stderr and append a new line
     * pre: an object to print
     * post: nothing
     */
    static void printlnError(Object obj) {
        System.err.println(obj);
    }

    /* Print out an object
     * pre: an object to print
     * post: nothing
     */
    static void print(Object obj) {
        System.out.print(obj);
    }

    /* Print out string with format
     * pre: format string, arguments
     * post: nothing
     */
    static void format(String format, Object... args) {
        System.out.format(format, args);
    }
}
