package Exams.Matrix;

import java.util.Scanner;

public class TheGambler {
    private static int sum = 100;
    private static boolean jackpot = false;
    private static boolean end = false;
    private static boolean isOutOfBond = false;


    private static int gamblerRow = 0;
    private static int gamblerCol = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        char[][] board = new char[size][size];
        fillBoard(scanner, size, board);
        getPositions(board);
        String input = scanner.nextLine();
        while (!input.equals("end") && !end && !jackpot) {
            moveGambler(input, board);
            input = scanner.nextLine();
        }
        if (isOutOfBond || end) {
            System.out.println("Game over! You lost everything!");
        }
        if (jackpot && !isOutOfBond && !end) {
            System.out.println("You win the Jackpot!");
        }
        if (!isOutOfBond && sum > 0) {
            System.out.printf("End of the game. Total amount: %d$%n", sum);
            printMatrix(board);
        }


    }

    private static void printMatrix(char[][] board) {
        for (char[] rows : board) {
            for (char cols : rows) {
                System.out.print(cols);
            }
            System.out.println();

        }
    }


    private static void moveGambler(String command, char[][] board) {
        int row = gamblerRow;
        int col = gamblerCol;


        switch (command) {
            case "up":
                row--;
                if (gamblerRow - 1 < 0) {
                    isOutOfBond = true;
                    return;
                }
                break;
            case "down":
                row++;
                if (gamblerRow + 1 > board.length) {
                    isOutOfBond = true;
                    return;
                }
                break;
            case "left":
                col--;
                if (gamblerCol - 1 < 0) {
                    isOutOfBond = true;
                    return;
                }
                break;
            case "right":
                col++;
                if (gamblerCol + 1 > board.length) {
                    isOutOfBond = true;
                    return;

                }
                break;
        }


        board[gamblerRow][gamblerCol] = '-';
        handlePoints(board, row, col);


    }


    private static void handlePoints(char[][] board, int row, int col) {
        if (board[row][col] == 'W') {
            sum += 100;
        } else if (board[row][col] == 'P') {
            sum -= 200;

            if (sum <= 0) {
                sum = 0;
                end = true;


            }
        } else if (board[row][col] == 'J') {
            sum += 100000;
            jackpot = true;
        }
        board[row][col] = 'G';
        gamblerRow = row;
        gamblerCol = col;
    }


    private static void fillBoard(Scanner scanner, int size, char[][] board) {
        for (int i = 0; i < size; i++) {
            board[i] = scanner.nextLine().toCharArray();
        }
    }

    private static void getPositions(char[][] matrix) {
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix.length; cols++) {
                if (matrix[rows][cols] == 'G') {
                    gamblerRow = rows;
                    gamblerCol = cols;
                }

            }

        }
    }


}