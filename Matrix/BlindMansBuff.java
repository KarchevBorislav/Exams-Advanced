package Exams.Matrix;

import java.util.Scanner;

public class BlindMansBuff {
    private static int rowBlindMan = 0;
    private static int colBlindMan = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] dimensionsInput = scanner.nextLine().split("\\s+");
        int row = Integer.parseInt(dimensionsInput[0]);
        int col = Integer.parseInt(dimensionsInput[1]);

        char[][] field = new char[row][col];

        fillMatrix(field, scanner, row, col);
        getBlindManPosition(field, col);
        int moves = 0;
        int touchedOpponents = 0;

        String commands = scanner.nextLine();
        while (!commands.equals("Finish")) {
            if (touchedOpponents == 3) {
                break;
            }
            int nextRow = rowBlindMan;
            int nextCol = colBlindMan;
            switch (commands) {
                case "up":
                    nextRow--;
                    if (nextRow < 0 || field[nextRow][nextCol] == 'O') {
                        break;
                    }
                    moves++;
                    if (field[nextRow][nextCol] == 'P') {
                        touchedOpponents++;
                    }
                    field[nextRow][nextCol] = 'B';
                    field[rowBlindMan][colBlindMan] = '-';
                    rowBlindMan = nextRow;
                    colBlindMan = nextCol;

                    break;
                case "down":
                    nextRow++;
                    if (nextRow > field.length || field[nextRow][nextCol] == 'O') {
                        break;
                    }
                    moves++;
                    if (field[nextRow][nextCol] == 'P') {
                        touchedOpponents++;
                    }
                    field[nextRow][nextCol] = 'B';
                    field[rowBlindMan][colBlindMan] = '-';
                    rowBlindMan = nextRow;
                    colBlindMan = nextCol;
                    break;
                case "left":
                    nextCol--;
                    if (nextCol < 0 || field[nextRow][nextCol] == 'O') {
                        break;
                    }
                    moves++;
                    if (field[nextRow][nextCol] == 'P') {
                        touchedOpponents++;
                    }
                    field[nextRow][nextCol] = 'B';
                    field[rowBlindMan][colBlindMan] = '-';
                    rowBlindMan = nextRow;
                    colBlindMan = nextCol;
                    break;
                case "right":
                    nextCol++;
                    if (nextCol > field.length-1 || field[nextRow][nextCol] == 'O') {
                        break;
                    }
                    moves++;
                    if (field[nextRow][nextCol] == 'P') {
                        touchedOpponents++;
                    }
                    field[nextRow][nextCol] = 'B';
                    field[rowBlindMan][colBlindMan] = '-';
                    rowBlindMan = nextRow;
                    colBlindMan = nextCol;
                    break;
            }

            commands = scanner.nextLine();
        }

        System.out.println("Game over!");
        System.out.printf("Touched opponents: %d Moves made: %d", touchedOpponents, moves);


    }

    private static void fillMatrix(char[][] field, Scanner scanner, int row, int col) {

        for (int rows = 0; rows < row; rows++) {
            char[] input = scanner.nextLine().replaceAll("\\s+", "").toCharArray();
            field[rows] = input;
        }
    }

    private static void getBlindManPosition(char[][] field, int cols) {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < cols; col++) {
                if (field[row][col] == 'B') {
                    rowBlindMan = row;
                    colBlindMan = col;

                }

            }

        }

    }
}
