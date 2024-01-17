package Exams.Matrix;

import java.util.Arrays;
import java.util.Scanner;

public class MouseInTheKitchen {
    private static int mouseStartRow = 0;
    private static int mouseStartCol = 0;
    private static int cheeseCounter = 0;
    private static int mouseLastKnownRow = 0;
    private static int mouseLastKnownCol = 0;
    private static boolean allCheeseAreEaten = false;

    private static boolean isTrapped = false;
    private static boolean isOutside = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int row = dimensions[0];
        int col = dimensions[1];
        char[][] kitchenMatrix = new char[row][col];
        fillMatrix(kitchenMatrix, scanner);
        getCharacterPositions(kitchenMatrix, row, col);
        movementInMatrix(kitchenMatrix, scanner);
        if (!isTrapped && !isOutside && cheeseCounter > 0) {
            System.out.println("Mouse will come back later!");
            printMatrix(kitchenMatrix, row, col);
        }

        if (isOutside) {
            System.out.println("No more cheese for tonight!");
            printMatrix(kitchenMatrix, row, col);
        }

        if (cheeseCounter == 0) {
            System.out.println("Happy mouse! All the cheese is eaten, good night! ");
            printMatrix(kitchenMatrix, row, col);
        }
        if (isTrapped) {
            System.out.println("Mouse is trapped!");
            printMatrix(kitchenMatrix, row, col);

        }


    }

    private static void printMatrix(char[][] kitchenMatrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(kitchenMatrix[i][j]);

            }
            System.out.println();
        }
    }

    private static void getCharacterPositions(char[][] kitchenMatrix, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (kitchenMatrix[row][col] == 'M') {
                    mouseStartRow = row;
                    mouseStartCol = col;
                }
                if (kitchenMatrix[row][col] == 'C') {
                    cheeseCounter++;

                }

            }

        }
    }

    private static void movementInMatrix(char[][] kitchenMatrix, Scanner scanner) {
        String commands = scanner.nextLine();
        while (!commands.equals("danger") && !isOutside && !isTrapped && cheeseCounter != 0) {
            switch (commands) {

                case "up":
                    if (mouseStartRow - 1 < 0) {
                        isOutside = true;
                        return;
                    }

                    if (isValid(kitchenMatrix, mouseStartRow - 1, mouseStartCol) && kitchenMatrix[mouseStartRow - 1][mouseStartCol] == '@') {
                        break;
                    } else {
                        moveMouse(kitchenMatrix, mouseStartRow - 1, mouseStartCol);
                    }

                    break;
                case "down":
                    if (mouseStartRow + 1 > kitchenMatrix.length) {
                        isOutside = true;
                        return;
                    }
                    if (isValid(kitchenMatrix, mouseStartRow + 1, mouseStartCol) && kitchenMatrix[mouseStartRow + 1][mouseStartCol] == '@') {
                        break;

                    } else {
                        moveMouse(kitchenMatrix, mouseStartRow + 1, mouseStartCol);
                    }
                    break;
                case "left":
                    if (mouseStartCol - 1 < 0) {
                        isOutside = true;
                        return;
                    }
                    if (isValid(kitchenMatrix, mouseStartRow, mouseStartCol - 1) && kitchenMatrix[mouseStartRow][mouseStartCol - 1] == '@') {
                        break;

                    } else {

                        moveMouse(kitchenMatrix, mouseStartRow, mouseStartCol - 1);
                    }
                    break;
                case "right":
                    if (mouseStartCol + 1 > kitchenMatrix.length) {
                        isOutside = true;
                        return;
                    }
                    if (isValid(kitchenMatrix, mouseStartRow, mouseStartCol + 1) && kitchenMatrix[mouseStartRow][mouseStartCol + 1] == '@') {

                        break;

                    } else {
                        moveMouse(kitchenMatrix, mouseStartRow, mouseStartCol + 1);
                    }
                    break;


            }
            commands = scanner.nextLine();
        }

    }


    private static void fillMatrix(char[][] kitchenMatrix, Scanner scanner) {
        for (int row = 0; row < kitchenMatrix.length; row++) {
            String input = scanner.nextLine();
            kitchenMatrix[row] = input.toCharArray();

        }
    }

    private static void moveMouse(char[][] kitchenMatrix, int nextRow, int nextCol) {
//            if (kitchenMatrix[nextRow - 1 ][nextCol] < 0 || kitchenMatrix[mouseStartRow + 1] [mouseStartCol] > kitchenMatrix.length)  {
//
//            }
        if (kitchenMatrix[nextRow][nextCol] == 'C') {
            cheeseCounter--;
            kitchenMatrix[mouseStartRow][mouseStartCol] = '*';
            if (cheeseCounter == 0) {
                kitchenMatrix[nextRow][nextCol] = 'M';
                return;
            }


        } else if (kitchenMatrix[nextRow][nextCol] == 'T') {

            kitchenMatrix[mouseStartRow][mouseStartCol] = '*';
            kitchenMatrix[nextRow][nextCol] = 'M';
            isTrapped = true;

        }
        kitchenMatrix[mouseStartRow][mouseStartCol] = '*';
        kitchenMatrix[nextRow][nextCol] = 'M';
        mouseStartRow = nextRow;
        mouseStartCol = nextCol;
    }

    private static boolean isValid(char[][] kitchenMatrix, int row, int col) {

        return row >= 0 && row < kitchenMatrix.length && col >= 0 && col < kitchenMatrix.length;
    }
}

