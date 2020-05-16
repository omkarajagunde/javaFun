
import java.io.*;

import javax.lang.model.util.ElementScanner6;

/**
 * turingMachine
 */
class TuringMachine {

    private char[] readInput = new char[100];
    String inputString;
    int numX = 0;
    int numY = 0;
    int i = 0, x = 0, y = 0;

    class Colors {

        public static final String RESET = "\033[0m";

        public static final String BLACK_BOLD = "\033[1;30m"; // BLACK
        public static final String RED_BOLD = "\033[1;31m"; // RED
        public static final String GREEN_BOLD = "\033[1;32m"; // GREEN
        public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
        public static final String BLUE_BOLD = "\033[1;34m"; // BLUE
        public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        public static final String CYAN_BOLD = "\033[1;36m"; // CYAN
        public static final String WHITE_BOLD = "\033[1;37m"; // WHITE
    }

    public void loadingCursor() {

        int j = 0;
        while (j < 3) {
            for (int i = 0; i < 3; i++) {

                try {

                    Thread.sleep(150);

                    if (i == 0)
                        System.out.print("\r" + Colors.PURPLE_BOLD + "-");
                    else if (i == 1)
                        System.out.print("\r" + Colors.PURPLE_BOLD + "\\");
                    else if (i == 2)
                        System.out.print("\r" + Colors.PURPLE_BOLD + "|");
                    else
                        System.out.print("\r" + Colors.PURPLE_BOLD + "/");

                } catch (InterruptedException e) {

                    System.out.println("Caught Interrupted Exception In Function loadingCursor()");
                }

            }
            j++;
        }

        System.out.print(Colors.RESET);
        stringDisplay();
        System.out.println();
    }

    public void aEqualsTob() throws InterruptedException {
        try {
            calTape();
        } catch (Exception e) {
            System.out.println("Wrong Input No. of a's not equal to No. of b's");
	    x=0;
	    y=0;
        }
    }

    public void calTape() throws InterruptedException {

        int c, k;
        for (int i = 0; i < inputString.length(); i++) {

            if (readInput[i] == 'a') {
                readInput[i] = 'X';
                x++;

                c = i;
                while (readInput[c] != 'b') {

                    c++;
                }

                readInput[c] = 'Y';
                y++;

                k = c;
                while (readInput[k] != 'X') {
                    k--;
                }

                i = k;
                loadingCursor();

            }

            if (readInput[0] == 'b')
                System.out.println("Wrong Starting Of String ....");

        }
        System.out.println(Colors.CYAN_BOLD + "No. of X :" + x + " \nNo. of Y :" + y + Colors.RESET);
        x = 0;
        y = 0;
    }

    public void stringDisplay() {

        for (int k = 0; k < readInput.length; k++) {

            System.out.print(readInput[k]);
        }
    }

    public void displayMenu() throws IOException, InterruptedException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String option = null;
        int choice = 0;
        boolean wrongRightInput = true;

        do {

            System.out.println("\n\n**********************************************************************");
            System.out.printf("** \t\t%-51s **\n", "WELCOME TO TURING MACHINE SIMULATION");
            System.out.println("**********************************************************************");

            System.out.printf("** \t\t%-51s **\n", "1} accept a Language L = { a^n b^n | n>=1 } :");
            System.out.printf("** \t\t%-51s **\n", "2} accept a Language L = { W C W | W = (a + b)^* } :");
            System.out.printf("** \t\t%-51s **\n", "3} accept a Language L = { W C W^R | W = (a + b)^* } :");

            while (wrongRightInput) {
                try {
                    choice = Integer.parseInt(reader.readLine());
                    wrongRightInput = false;
                } catch (NumberFormatException e) {
                    System.out.print(
                            "\n\nString Or Character cannnot be converted to Integer Please enter a Choice Num? :");

                }
            }

            switch (choice) {
            case 1:
                System.out.print("Enter a Input String of Language L = { a^n b^n | n>=1 } :");
                inputString = reader.readLine();
                parseToArray(inputString);
                aEqualsTob();

                System.out.print(Colors.RED_BOLD + "\n\n You Want To continue or Exit? [Yes]/[NO] :" + Colors.RESET);
                option = reader.readLine();

                break;

            case 2:
                System.out.print("Enter a Input String of Language L = { W C W | W = (a + b)^* } :");
                inputString = reader.readLine();
                parseToArray(inputString);
                // aEqualsTob();

                System.out.print(Colors.RED_BOLD + "\n\n You Want To continue or Exit? [Yes]/[NO] :" + Colors.RESET);
                option = reader.readLine();

                break;

            case 3:
                System.out.print("Enter a Input String of Language L = { W C W^R | W = (a + b)^* } :");
                inputString = reader.readLine();
                parseToArray(inputString);
                // aEqualsTob();

                System.out.print(Colors.RED_BOLD + "\n\n You Want To continue or Exit? [Yes]/[NO] :" + Colors.RESET);
                option = reader.readLine();

                break;

            default:
                break;
            }

        } while (!option.equalsIgnoreCase("yes"));

    }

    public void parseToArray(String inputString) {

        int c = 0;
        int last = inputString.length();
        while (c < last) {

            readInput[c] = inputString.charAt(c);
            c++;
        }

        System.out.print("Entered String For Turing Machine Is :" + Colors.YELLOW_BOLD + ".. BB ... ");
        for (int i = 0; i < inputString.length(); i++)
            System.out.print(Colors.RED_BOLD + "" + inputString.charAt(i));

        System.out.print(Colors.YELLOW_BOLD + " ... BB .. \n" + Colors.RESET);

    }
}

class RunTM {

    public static void main(String[] args) throws IOException, InterruptedException {

        TuringMachine TM = new TuringMachine();
        TM.displayMenu();

    }
}
