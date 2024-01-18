package Exams.Matrix;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Bomb {
    private static int rowSapper = 0;
    private static int colSapper = 0;
    private static int bombCounter = 0;
    private static int currentRow = 0;
    private static int currentCol = 0;

    private static boolean isEndOfRoad = false;
    private static boolean isZeroBombs = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimension = Integer.parseInt(scanner.nextLine());
        String[] matrixFields = scanner.nextLine().split(",");

        char[][] bombMatrix = new char[dimension][dimension];
        fillMatrix(bombMatrix, scanner, dimension);
        movementInMatrix(matrixFields, bombMatrix);
        printOutput(bombMatrix);

    }

    private static void movementInMatrix(String[] matrixFields, char[][] bombMatrix) {
        for (int i = 0; i < matrixFields.length; i++) {
            String command = matrixFields[i];
            if (isZeroBombs || isEndOfRoad) {
                return;
            }

            switch (command) {
                case "up":
                    if (rowSapper - 1 < 0) {
                        continue;
                    }
                    moveSapper(bombMatrix, rowSapper - 1, colSapper);
                    break;
                case "down":
                    if (rowSapper + 1 > bombMatrix.length - 1) {

                        continue;
                    }
                    moveSapper(bombMatrix, rowSapper + 1, colSapper);
                    break;
                case "right":
                    if (colSapper + 1 > bombMatrix.length - 1) {
                        continue;
                    }
                    moveSapper(bombMatrix, rowSapper, colSapper + 1);
                    break;
                case "left":
                    if (colSapper - 1 < 0) {
                        continue;
                    }
                    moveSapper(bombMatrix, rowSapper, colSapper - 1);
                    break;
            }

        }
    }

    private static void printOutput(char[][] bombMatrix) {
        if (isZeroBombs){
            System.out.println("Congratulations! You found all bombs!");
        }

        if (isEndOfRoad){
            System.out.printf("END! %d bombs left on the field",bombCounter);
        }
        if (!isEndOfRoad && !isZeroBombs){

            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)",bombCounter,rowSapper,colSapper);
        }
    }

    private static void moveSapper(char[][] bombMatrix, int nextRow, int nextCol) {

        if (bombMatrix[nextRow][nextCol] == 'B') {
            System.out.println("You found a bomb!");
            bombCounter--;
            if (bombCounter == 0) {
                isZeroBombs = true;
            }
        } else if (bombMatrix[nextRow][nextCol] == 'e') {
            isEndOfRoad = true;
            return;
        }
        bombMatrix[rowSapper][colSapper] = '+';
        bombMatrix[nextRow][nextCol] = 's';
        rowSapper = nextRow;
        colSapper = nextCol;
    }


    private static void fillMatrix(char[][] bombMatrix, Scanner scanner, int size) {
        for (int row = 0; row < bombMatrix.length; row++) {
            List<Character> charList = Arrays.stream(scanner.nextLine().split(" ")).map(e -> e.charAt(0)).collect(Collectors.toList());
            for (int col = 0; col < charList.size(); col++) {
                char currentChar = charList.get(col);
                bombMatrix[row][col] = currentChar;
                if (bombMatrix[row][col] == 's') {
                    rowSapper = row;
                    colSapper = col;
                } else if (bombMatrix[row][col] == 'B') {
                    bombCounter++;
                }
            }
        }
    }
}
