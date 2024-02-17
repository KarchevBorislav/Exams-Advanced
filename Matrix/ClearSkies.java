import java.util.Scanner;

public class ClearSkies {
    private static int jetRow = 0;
    private static int jetCol = 0;
    private static int jetFighterArmour = 300;
    private static int enemyAircraftCount = 0;

    private static boolean allEnemyDown = false;
    private static boolean missionFail = false;
    private static int hitsTaken = 0;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        int size = Integer.parseInt(scanner.nextLine());

        char[][] airspace = new char[size][size];
        fillMatrix(airspace, scanner);
        getPosition(airspace);


        while (jetFighterArmour > 0 && !allEnemyDown && !missionFail) {
            String directions = scanner.nextLine();
            airspace[jetRow][jetCol] = '-';
            int nextRow = jetRow;
            int nextCol = jetCol;

            switch (directions) {
                case "up":
                    nextRow--;
                    break;
                case "down":
                    nextRow++;
                    break;
                case "left":
                    nextCol--;
                    break;
                case "right":
                    nextCol++;
                    break;
            }


            if (airspace[nextRow][nextCol] == 'E') {
                enemyAircraftCount--;
                hitsTaken++;
                if (enemyAircraftCount == 0) {
                    allEnemyDown = true;
                    airspace[nextRow][nextCol] = 'J';
                    break;
                } else if (enemyAircraftCount > 1 && hitsTaken == 2 && jetFighterArmour < 200) {
                    hitsTaken++;
                    jetRow = nextRow;
                    jetCol = nextCol;
                    missionFail = true;
                    break;

                } else {
                    jetFighterArmour -= 100;
//                    airspace[jetRow][jetCol] = '-';
//                    airspace[nextRow][nextCol] = '-';
                }

            } else if (airspace[nextRow][nextCol] == 'R') {
                jetFighterArmour = 300;
                airspace[nextRow][nextCol] = 'J';

            }

            airspace[nextRow][nextCol] = 'J';
            jetRow = nextRow;
            jetCol = nextCol;


        }
        if (allEnemyDown && !missionFail) {
            System.out.println("Mission accomplished, you neutralized the aerial threat!");
        }
        if (hitsTaken == 3) {
            System.out.printf("Mission failed, your jetfighter was shot down! Last coordinates [%d, %d]!%n", jetRow, jetCol);

        }
        printMatrix(airspace);


    }

    private static void printMatrix(char[][] airspace) {
        for (char[] chars : airspace) {
            for (int col = 0; col < airspace.length; col++) {
                System.out.print(chars[col]);
            }
            System.out.println();
        }
    }

    private static void getPosition(char[][] airspace) {
        for (int row = 0; row < airspace.length; row++) {
            for (int col = 0; col < airspace.length; col++) {
                if (airspace[row][col] == 'J') {
                    jetRow = row;
                    jetCol = col;

                } else if (airspace[row][col] == 'E') {
                    enemyAircraftCount++;

                }

            }

        }
    }

    private static void fillMatrix(char[][] airspace, Scanner scanner) {
        for (int i = 0; i < airspace.length; i++) {
            String input = scanner.nextLine();
            airspace[i] = input.toCharArray();

        }
    }
}
