package Exams.Matrix;

import java.util.Arrays;
import java.util.Scanner;

public class TheSquirrel {

    private static int hazelnutsCount = 0;
    private static int squirrelRow = 0;
    private static int squirrelCol = 0;
    private static boolean isOutOfBound = false;
    private static boolean isTrap = false;


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(", ");
        char[][] field = new char[size][size];

        fillMatrix(field, scanner);
        getSquirrelPosition(field);


        int nextRow = squirrelRow;
        int nextCol = squirrelCol;


        for (String direction : commands) {
            if (isOutOfBound || isTrap || hazelnutsCount == 3) {
                break;
            }

            switch (direction) {
                case "up":
                    nextRow--;
                    if (nextRow < 0) {
                        isOutOfBound = true;
                        break;
                    }
                    if (field[nextRow][nextCol] == 'h') {
                        hazelnutsCount++;
                    } else if (field[nextRow][nextCol] == 't') {
                        isTrap = true;

                    }
                    field[squirrelRow][squirrelCol] = '*';
                    squirrelRow = nextRow;
                    squirrelCol = nextCol;
                    field[squirrelRow][squirrelCol] = 's';


                    break;
                case "down":
                    nextRow++;
                    if (nextRow > field.length - 1) {
                        isOutOfBound = true;
                        break;
                    }
                    if (field[nextRow][nextCol] == 'h') {
                        hazelnutsCount++;
                    } else if (field[nextRow][nextCol] == 't') {
                        isTrap = true;

                    }
                    field[squirrelRow][squirrelCol] = '*';
                    squirrelRow = nextRow;
                    squirrelCol = nextCol;
                    field[squirrelRow][squirrelCol] = 's';

                    break;
                case "left":
                    nextCol--;
                    if (nextCol < 0) {
                        isOutOfBound = true;
                        break;
                    }
                    if (field[nextRow][nextCol] == 'h') {
                        hazelnutsCount++;
                    } else if (field[nextRow][nextCol] == 't') {
                        isTrap = true;
                    }
                    field[squirrelRow][squirrelCol] = '*';
                    squirrelRow = nextRow;
                    squirrelCol = nextCol;
                    field[squirrelRow][squirrelCol] = 's';

                    break;
                case "right":
                    nextCol++;
                    if (nextCol > field.length - 1) {
                        isOutOfBound = true;
                        break;
                    }
                    if (field[nextRow][nextCol] == 'h') {
                        hazelnutsCount++;
                    } else if (field[nextRow][nextCol] == 't') {
                        isTrap = true;

                    }
                    field[squirrelRow][squirrelCol] = '*';
                    squirrelRow = nextRow;
                    squirrelCol = nextCol;
                    field[squirrelRow][squirrelCol] = 's';

                    break;
            }

        }


        if (isOutOfBound) {
            System.out.println("The squirrel is out of the field.");
        }
        if (isTrap) {
            System.out.println("Unfortunately, the squirrel stepped on a trap...");
        }
        if (!isOutOfBound && !isTrap && hazelnutsCount < 3) {
            System.out.println("There are more hazelnuts to collect.");

        } else if (!isOutOfBound && !isTrap && hazelnutsCount == 3) {
            System.out.println("Good job! You have collected all hazelnuts!");
        }
        System.out.printf("Hazelnuts collected: %d", hazelnutsCount);

    }

    private static void fillMatrix(char[][] field, Scanner scanner) {

        for (int rows = 0; rows < field.length; rows++) {
            String input = scanner.nextLine();
            field[rows] = input.toCharArray();
        }
    }

    private static void getSquirrelPosition(char[][] field) {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field.length; col++) {
                if (field[row][col] == 's') {
                    squirrelRow = row;
                    squirrelCol = col;
                }

            }

        }
    }
}
