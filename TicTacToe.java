/** Command Line Interface TicTacToe game multiplayer offline */

// ---Necessary imports---
import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    // ---Some random class variables---

    public static String ultimateGap = "*****************************************************************************************************";

    public static String[] names = new String[2];
    public static String[] board = new String[9];

    public static boolean checkX;
    public static boolean checkO;

    public static void main(String[] args) {
        
        while (true) {

            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter the command for '/help' for help menu, '/exit' or 'y' to continue the game: ");
            String command = sc.next();

            board = new String[]{"1","2","3","4","5","6","7","8","9"}; // Intial Board

            checkO = false;
            checkX = false;

            switch (command) {

                case "/help": 
                    printHelp();
                    break;

                case "/exit": 
                    System.out.println("\nThanks for playing the game!\n");
                    sc.close();
                    System.exit(0);

                case "y": 
                    playGame(sc);
                    break;

                case "Y": 
                    playGame(sc);
                    break;
            
                default: System.out.println("Wrong command!");
            }
        }
    }

    /* ---Diagram of the board[]---

    |-----------|
    | 0 | 1 | 2 |
    |-----------|
    | 3 | 4 | 5 |
    |-----------|
    | 6 | 7 | 8 |
    |-----------| */

    // ---To print the board---
    public static void printBoard() {

        System.out.println("            |-----------|");
        System.out.println("            | " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("            |-----------|");
        System.out.println("            | " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("            |-----------|");
        System.out.println("            | " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("            |-----------|");

        System.out.println();
    }

    // ---Game Function---
    public static void playGame(Scanner sc) { 

        // To check until the names in names[] are different
        
        do {
            for(int i = 0; i < 2; i++) {
                System.out.print("\nEnter player"+(i+1)+"'s name: ");
                names[i] = sc.next();
            }

            if (names[0].equals(names[1])) {
                System.out.print("\nNo two names can be similar\n");
                continue;
            } else {
                break;
            }

        } while (true);

        //To print the board

        System.out.println("\nFor your convenience the print of the board is: \n");
        printBoard();

        String turnPlayer1 = "", turnPlayer2 = "";

        turnPlayer1 = roll(); // Get random "X" or "O";

        if (turnPlayer1.equals("X")) {
            turnPlayer2 = "O";
        } else {
            turnPlayer2 = "X";
        }
        
        // To print individual players turn
        System.out.println("The first one to put is "+names[0]+" then "+names[1]+"\n");
        System.out.println("For "+names[0]+" is "+turnPlayer1+" and for "+names[1]+" is "+turnPlayer2);

        int flag = 0;
        
        int i = 0;

        String valX = "X", valO = "O", tempX = "", tempO = ""; // some random variables

        // some random loop

        do {

            // Forgot what it does but works fine

            if(flag == 0 && !valX.equals(valO)) {
                System.out.print("\n"+names[0]+"'s turn!\n");
                System.out.print("Enter value(1-9): ");
                valX = sc.next();

                if (valX.equals("/exit")) {
                    System.out.println("\nUhhh.. "+names[0]+" you didn't liked the game? \n");
                    System.exit(0);
                }

                try {
                    int copyOfValX = Integer.parseInt(valX);

                    if (!valX.equals(valO) && !valX.equals(tempX)) {
                        if (copyOfValX >=1 && copyOfValX <= 9) {
                            board[copyOfValX-1] = turnPlayer1;
                        } else {
                            System.out.print("\n"+names[0]+" please enter correct value from (1-9).\n");
                            continue;
                        }
                    } else {
                        System.out.print("\n"+names[0]+" re-enter your turn.. no overlapping is allowed.\n");
                        continue;
                    }
                    System.out.println();
                    printBoard();
                    flag = 1;
                    if (checkWinner() && checkX) {
                        System.out.print(ultimateGap+"\n");
                        System.out.print("The winner is: "+names[0]+"\n");
                        System.out.println(ultimateGap);
                        break;
                    }
                    tempX = valX;
                    i++;
                } catch (NumberFormatException e) {
                    System.out.println("\n"+names[0]+" please enter decimal value from (1-9).");
                    continue;
                }

            } else {

                System.out.print("\n"+names[1]+"'s turn!\n");
                System.out.print("Enter value(1-9): ");
                valO = sc.next();

                if (valO.equals("/exit")) {
                    System.out.println("\nUhhh.. "+names[1]+" you didn't liked the game? \n");
                    System.exit(0);
                }

                try {
                
                    int copyOfValO = Integer.parseInt(valO);
                    if (!valO.equals(valX) && !valO.equals(tempO)) {
                        if (copyOfValO >= 1 && copyOfValO <= 9) {
                            board[copyOfValO-1] = turnPlayer2;
                        } else {
                            System.out.print("\n"+names[1]+" please enter correct value from (1-9).\n");
                            continue;
                        }
                    } else {
                        System.out.print("\n"+names[1]+" re-enter your turn.. no overlapping is allowed.\n");
                        continue;
                    }
                    System.out.println();
                    printBoard();
                    flag = 0;
                    if (checkWinner() && checkO) {
                        System.out.print(ultimateGap+"\n");
                        System.out.print("The winner is: "+names[1]+"\n");
                        System.out.println(ultimateGap);
                        break;
                    }
                    tempO = valO;
                    i++;
                } catch (NumberFormatException e) {
                    System.out.println("\n"+names[1]+" please enter decimal value from (1-9)");
                    continue;
                }
            }

            if (i == 9 && !checkWinner()) {
                System.out.print(ultimateGap);
                System.out.println("\nThe match is a tie.");
                System.out.println(ultimateGap);
            }
        } while (i < 9);
    }
    
    // ---To get random "X"/"O"---
    public static String roll() {

        Random random = new Random();

        int i = ((random.nextInt(0,2)));

        if (i == 1) {
            return "X";
        } else {
            return "O";
        }
    }

    // ---To check the winner and if the board is filled with a draw---
    public static boolean checkWinner() {

        int a = 0;

        for (;a < 9; a++) {

            String line = "";
 
            switch (a) {
            case 0:
                line = board[0] + board[1] + board[2];
                break;
            case 1:
                line = board[3] + board[4] + board[5];
                break;
            case 2:
                line = board[6] + board[7] + board[8];
                break;
            case 3:
                line = board[0] + board[3] + board[6];
                break;
            case 4:
                line = board[1] + board[4] + board[7];
                break;
            case 5:
                line = board[2] + board[5] + board[8];
                break;
            case 6:
                line = board[0] + board[4] + board[8];
                break;
            case 7:
                line = board[2] + board[4] + board[6];
                break;
            }
        
            if (line.equals("XXX")) {
                checkX = true;
                checkO = false;
                return true;
            }

            if (line.equals("OOO")) {
                checkO = true;
                checkX = false;
                return true;
            }
        }

        checkO = false; checkX = false;

        return false;
    }

    // ---To print the help menu---
    public static void printHelp() {

        System.out.println(ultimateGap);
        System.out.println("The help menu: ");
        System.out.println("    - There is a grid of 3x3 matrix and you and the other player will have to put accordance to it.");
        System.out.println("    - The marix is as follows: \n");
        printBoard();
        System.out.println("    - You can't be serious that you don't know how to play tictactoe... better die.");
        System.out.print(ultimateGap+"\n");
    }
}
