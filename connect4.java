import java.util.*;

public class connect4 {
    public static void main(String[] args) {
        char[][] emptyBoard = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        System.out.println("Your empty board:");
        displayBoard(emptyBoard);
        input(emptyBoard);
    }

    public static void displayBoard(char[][] emptyBoard) {
        for (int row = 0; row <= 5; row++) {
            for (int col = 0; col <= 6; col++) {
                System.out.print(emptyBoard[row][col] + " | ");
            }
            System.out.println();
        }
    }

    public static void input(char[][] emptyBoard) {
        Scanner in = new Scanner(System.in);
        boolean redCorrect = true, redInput = true;
        boolean blueCorrect = true, blueInput = true;

        for (int i = 5; i >= 0; i--) {
            while ((emptyBoard[i][0] == ' ' || emptyBoard[i][1] == ' ' || emptyBoard[i][2] == ' ' ||
                    emptyBoard[i][3] == ' ' || emptyBoard[i][4] == ' ' || emptyBoard[i][5] == ' ' ||
                    emptyBoard[i][6] == ' ')) {
                System.out.println("Red Player, choose your column: ");
                int redInputColumn = getUserInput(in);

                int x = i;
                while (redCorrect) {
                    if (emptyBoard[x][redInputColumn - 1] != ' ') {
                        x--;
                    } else {
                        redCorrect = false;
                    }

                    if (x < 0) {
                        System.out.println("Column is full. Choose another column: ");
                        redInputColumn = getUserInput(in);
                        x = i;
                    }
                }
                emptyBoard[x][redInputColumn - 1] = 'R';
                displayBoard(emptyBoard);
                RedCheck(emptyBoard);
                draw(emptyBoard);
                redCorrect = true;

                System.out.println("Blue Player, choose your column: ");
                int blueInputColumn = getUserInput(in);

                int y = i;
                while (blueCorrect) {
                    if (emptyBoard[y][blueInputColumn - 1] != ' ') {
                        y--;
                    } else {
                        blueCorrect = false;
                    }

                    if (y < 0) {
                        System.out.println("Column is full. Choose another column: ");
                        blueInputColumn = getUserInput(in);
                        y = i;
                    }
                }
                emptyBoard[y][blueInputColumn - 1] = 'B';
                displayBoard(emptyBoard);
                BlueCheck(emptyBoard);
                draw(emptyBoard);
                blueCorrect = true;
            }
        }
    }

    public static int getUserInput(Scanner in) {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = in.nextInt();
                if (input < 1 || input > 7) {
                    System.out.println("Choose a number between 1-7");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please only choose through an integer number");
                in.nextLine();
            }
        }
        return input;
    }

    public static void RedCheck(char[][] emptyBoard) {
        checkWin(emptyBoard, 'R', "Red Player has won the Game!");
    }

    public static void BlueCheck(char[][] emptyBoard) {
        checkWin(emptyBoard, 'B', "Blue Player has won the Game!");
    }

    public static void checkWin(char[][] emptyBoard, char player, String message) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (j + 3 < 7 && emptyBoard[i][j] == player && emptyBoard[i][j + 1] == player &&
                    emptyBoard[i][j + 2] == player && emptyBoard[i][j + 3] == player) {
                    System.out.println(message);
                    System.exit(0);
                }
                if (i + 3 < 6 && emptyBoard[i][j] == player && emptyBoard[i + 1][j] == player &&
                    emptyBoard[i + 2][j] == player && emptyBoard[i + 3][j] == player) {
                    System.out.println(message);
                    System.exit(0);
                }
                if (i + 3 < 6 && j + 3 < 7 && emptyBoard[i][j] == player && emptyBoard[i + 1][j + 1] == player &&
                    emptyBoard[i + 2][j + 2] == player && emptyBoard[i + 3][j + 3] == player) {
                    System.out.println(message);
                    System.exit(0);
                }
                if (i + 3 < 6 && j - 3 >= 0 && emptyBoard[i][j] == player && emptyBoard[i + 1][j - 1] == player &&
                    emptyBoard[i + 2][j - 2] == player && emptyBoard[i + 3][j - 3] == player) {
                    System.out.println(message);
                    System.exit(0);
                }
            }
        }
    }

    public static void draw(char[][] emptyBoard) {
        boolean isDraw = true;
        for (int j = 0; j < 7; j++) {
            if (emptyBoard[0][j] == ' ') {
                isDraw = false;
                break;
            }
        }
        if (isDraw) {
            System.out.println("The match has ended in a draw.");
            System.exit(0);
        }
    }
}
