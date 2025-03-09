import java.util.*;
/**
 * @author Saad Imam
 */

 public class sudoku{
    public static void main(String [] args){
        Scanner in = new Scanner (System.in);
        System.out.println("Welcome to the Sudoku Game!");
        System.out.println("The rules are the same as always."); 
        System.out.println("Make sure to never enter String input, or else there will be disaster!");
        // the sudoku grid:
        int[][] grid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (grid[x][y] == 0 ){
                    printGrid(grid);
                    indexInput(in, grid);
                }
            }
        }
        boolean rowcheck = rowchecker(grid);
        boolean colcheck = colchecker(grid);
        boolean check3x3 = true;
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!checker3x3(i, j, grid)) {
                    check3x3 = false;
                }
            }
        }


        if (rowcheck && colcheck && check3x3) {
            System.out.println("Congratulations! You have solved it correctly! :) ");
        }
        in.close();
    
    }

    public static void printGrid(int [][] grid){
        // printing the grid for the user:
        System.out.println("A Sudoku grid:");
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }
    
    public static void indexInput(Scanner in, int[][] grid) {
        int i = 0, j = 0;
        boolean flag = false;

        while (!flag) {
            try {
                System.out.println("Enter index at which number to add");
                System.out.print("i: ");
                i = in.nextInt();
                System.out.print("j: ");
                j = in.nextInt();

                if ((i < 1 || i > 9) || (j < 1 || j > 9))
                    System.out.println("Invalid indexing, choose i and j between 1 and 9");
                else if (grid[i - 1][j - 1] != 0)
                    System.out.println("Cannot modify this entry, choose again");
                else {
                    flag = true;
                    modifyGrid(in, grid, i, j);
                    printGrid(grid);
                }
            } catch (InputMismatchException e) {
                System.out.println("DANGER! INVALID DATATYPE ENTERED");
                in.nextLine();
            }
        }
    }

    public static void modifyGrid(Scanner in, int[][] grid, int i, int j) {
        int num;
        boolean flag = false;

        while (!flag) {
            try {
                System.out.println("Enter number to add on (" + i + ", " + j + ") index");
                num = in.nextInt();
                if (num < 1 || num > 9) {
                    System.out.println("NUMBER HAS TO BE BETWEEN 1 AND 9");
                } else {
                    flag = true;
                    grid[i - 1][j - 1] = num;
                }
            } catch (InputMismatchException e) {
                System.out.println("DANGER! INVALID DATATYPE ENTERED");
                in.nextLine();
            }
        }
    }
    public static boolean rowchecker(int[][] grid) {
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
                for (int c = 0; c < 9; c++) {
                    if ((grid[a][b] == grid[a][c] && b != c)) {
                        System.out.println("Invalid entry on {" + a + " , " + b + "}, row check failed.");
                        System.out.println("It's over for you :( ");
                        System.exit(0);

                    }
                }
            }
        }
        return true;
    }

    public static boolean colchecker(int[][] grid) {
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
                for (int c = 0; c < 9; c++) {
                    if ((grid[b][a] == grid[c][a] && b != c)) {
                        System.out.println("Invalid entry on {" + b + " , " + a + "}, col check failed");
                        System.out.println("It's over for you :( ");
                        System.exit(0);
                    }
                }
            }
        }
        return true;
    }

    public static boolean checker3x3(int row, int col, int[][] grid) {
        boolean[] seen = new boolean[10]; // Array to track digits 1-9
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
    
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                int num = grid[i][j];
                if (num != 0) {
                    if (seen[num]) { // Duplicate found
                        System.out.println("Invalid entry in 3×3 grid at {" + i + " , " + j + "}");
                        return false;
                    }
                    seen[num] = true;
                }
            }
        }
        return true;
    }
    


}
