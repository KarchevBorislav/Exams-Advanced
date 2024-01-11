package Exams.Matrix;

import java.util.Scanner;

public class NavyBattle {
    private static int submarineRow = 0;
    private static int submarineCol = 0;
    private static int submarineLIfePoints = 3;
    private static int battleCruisersCount = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int sizeOfBattlefield = Integer.parseInt(scanner.nextLine());
        char[][] fieldMatrix = new char[sizeOfBattlefield][sizeOfBattlefield];

        fillMatrix(fieldMatrix, scanner, sizeOfBattlefield);
        getCharacterCoordinates(fieldMatrix);
        moveFieldsInMatrix(fieldMatrix, scanner);
        printFieldMatrix(fieldMatrix);





    }

    private static void moveFieldsInMatrix(char[][] fieldMatrix,Scanner scanner) {

        while (!isAllShipsDestroyed() || !isDestroyedSubmarine()) {

            if (isAllShipsDestroyed()){
                printVictory();
                return;
            } else if (isDestroyedSubmarine()) {

                printMissionFailed();

                return;
            }
            String inputValues= scanner.nextLine();
            switch (inputValues) {
                case "up":
                    moveUp(fieldMatrix);
                    break;
                case "down":
                    moveDown(fieldMatrix);
                    break;
                case "left":
                    moveLeft(fieldMatrix);
                    break;
                case "right":
                    moveRight(fieldMatrix);
                    break;

            }

        }
    }

    private static void moveRight(char[][] fieldMatrix) {
        int nextRow = submarineRow;
        int nextCol = submarineCol + 1;


        if (fieldMatrix[nextRow][nextCol] == '*') {
            submarineLIfePoints--;
        } else if (fieldMatrix[nextRow][nextCol] == 'C') {
            battleCruisersCount--;

        } else if (fieldMatrix[nextRow][nextCol] == '-') {
            fieldMatrix[nextRow][nextCol] = 'S';

        }
        fieldMatrix[submarineRow][submarineCol] = '-';
        fieldMatrix[nextRow][nextCol] = 'S';
        submarineRow = nextRow;
        submarineCol = nextCol;
    }

    private static void moveLeft(char[][] fieldMatrix) {
        int nextRow = submarineRow;
        int nextCol = submarineCol - 1;


        if (fieldMatrix[nextRow][nextCol] == '*') {
            submarineLIfePoints--;
        } else if (fieldMatrix[nextRow][nextCol] == 'C') {
            battleCruisersCount--;

        } else if (fieldMatrix[nextRow][nextCol] == '-') {
            fieldMatrix[nextRow][nextCol] = 'S';

        }
        fieldMatrix[submarineRow][submarineCol] = '-';
        fieldMatrix[nextRow][nextCol] = 'S';
        submarineRow = nextRow;
        submarineCol = nextCol;
    }


    private static void moveDown(char[][] fieldMatrix) {
        int nextRow = submarineRow + 1;
        int nextCol = submarineCol;


        if (fieldMatrix[nextRow][nextCol] == '*') {
            submarineLIfePoints--;
        } else if (fieldMatrix[nextRow][nextCol] == 'C') {
            battleCruisersCount--;

        } else if (fieldMatrix[nextRow][nextCol] == '-') {
            fieldMatrix[nextRow][nextCol] = 'S';

        }
        fieldMatrix[submarineRow][submarineCol] = '-';
        fieldMatrix[nextRow][nextCol] = 'S';
        submarineRow = nextRow;
        submarineCol = nextCol;
    }
    private static void printFieldMatrix(char [][]fieldMatrix){

        for (char[] row: fieldMatrix) {
            for (char col : row) {
                System.out.print(col);
            }
            System.out.println();

        }
    }

    private static void moveUp(char[][] fieldMatrix) {
        int nextRow = submarineRow - 1;
        int nextCol = submarineCol;


        if (fieldMatrix[nextRow][nextCol] == '*') {
            submarineLIfePoints--;
        } else if (fieldMatrix[nextRow][nextCol] == 'C') {
            battleCruisersCount--;

        } else if (fieldMatrix[nextRow][nextCol] == '-') {
            fieldMatrix[nextRow][nextCol] = 'S';

        }
        fieldMatrix[submarineRow][submarineCol] = '-';
        fieldMatrix[nextRow][nextCol] = 'S';
        submarineRow = nextRow;
        submarineCol = nextCol;
    }


    private static void fillMatrix(char[][] fieldMatrix, Scanner scanner, int size) {
        for (int row = 0; row < size; row++) {
            String input = scanner.nextLine();
            fieldMatrix[row] = input.toCharArray();
        }
    }

    private static void getCharacterCoordinates(char[][] fieldMatrix) {
        for (int row = 0; row < fieldMatrix.length; row++) {
            for (int col = 0; col < fieldMatrix.length; col++) {
                if (fieldMatrix[row][col] == 'S') {
                    submarineRow = row;
                    submarineCol = col;
                } else if (fieldMatrix[row][col] == 'C') {
                    battleCruisersCount++;
                }

            }

        }
    }

    private static boolean isAllShipsDestroyed() {
        return battleCruisersCount == 0;
    }

    private static boolean isDestroyedSubmarine() {
        return submarineLIfePoints == 0;

    }

    private static void printVictory() {
        System.out.printf("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!%n");


    }

    private static void printMissionFailed() {
        System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!%n",submarineRow,submarineCol);
    }

}
