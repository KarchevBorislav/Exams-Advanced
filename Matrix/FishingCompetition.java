package Exams.Matrix;

import java.util.Scanner;

public class FishingCompetition {
    private static int startRowFisher = 0;
    private static int startColFisher = 0;
    private static int amount = 0;
    private static boolean feltInWhirpool = false;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeFishingArea = Integer.parseInt(scanner.nextLine());
        char[][] fishingAreaMatrix = new char[sizeFishingArea][sizeFishingArea];

        fillMatrix(fishingAreaMatrix, scanner, sizeFishingArea);
        getMatrixCoordinates(fishingAreaMatrix);
        moveFieldsInFishingArea(scanner, fishingAreaMatrix, startRowFisher, startColFisher);
        if (feltInWhirpool) {
            System.out.printf("You fell into a whirlpool! The ship sank and you lost the fish you caught. Last coordinates of the ship: [%d,%d]", startRowFisher, startColFisher);

        }
        if (amount >= 20) {
            System.out.println("Success! You managed to reach the quota!");
        } else if (!feltInWhirpool) {
            System.out.printf("You didn't catch enough fish and didn't reach the quota! You need %d tons of fish more.%n", 20 - amount);
        }
        if (amount > 0){
            System.out.printf("Amount of fish caught: %d tons.%n", amount);
        }
        if (!feltInWhirpool){
            printMatrix(fishingAreaMatrix);
        }
    }


    private static void printMatrix(char[][] fishingAreaMatrix) {
        for (char[] areaMatrix : fishingAreaMatrix) {
            for (char matrix : areaMatrix) {
                System.out.print(matrix);
            }
            System.out.println();
        }
    }

    private static void moveFieldsInFishingArea(Scanner scanner, char[][] fishingAreaMatrix, int row, int col) {
        String input = scanner.nextLine();
        while (!input.equals("collect the nets") && !feltInWhirpool) {
            fishingAreaMatrix[row][col] = '-';
            switch (input) {
                case "up":
                    row--;
                    if (row < 0) {
                        row = fishingAreaMatrix.length - 1;
                    }
                    break;
                case "down":
                    row++;
                    if (row > fishingAreaMatrix.length - 1) {
                        row = 0;
                    }

                    break;
                case "right":
                    col++;
                    if (col > fishingAreaMatrix.length - 1) {
                        col = 0;
                    }
                    break;
                case "left":
                    col--;
                    if (col < 0) {
                        col = fishingAreaMatrix.length - 1;
                    }

                    break;
            }
            char symbol = fishingAreaMatrix[row][col];
            if (symbol == 'W') {
                amount = 0;
                startRowFisher = row;
                startColFisher = col;
                feltInWhirpool = true;
              return;
            } else if (Character.isDigit(symbol)) {
                amount += Character.getNumericValue(symbol);

            }
            fishingAreaMatrix[row][col] = 'S';

            input = scanner.nextLine();
        }
    }

    private static void getMatrixCoordinates(char[][] fishingAreaMatrix) {
        for (int row = 0; row < fishingAreaMatrix.length; row++) {
            for (int col = 0; col < fishingAreaMatrix.length; col++) {
                if (fishingAreaMatrix[row][col] == 'S') {
                    startRowFisher = row;
                    startColFisher = col;
                }

            }

        }
    }

    private static void fillMatrix(char[][] fishingAreaMatrix, Scanner scanner, int sizeFishingArea) {

        for (int row = 0; row < sizeFishingArea; row++) {
            String input = scanner.nextLine();
            fishingAreaMatrix[row] = input.toCharArray();
        }
    }
}

