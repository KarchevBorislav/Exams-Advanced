package Exams.StackAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Bouquets {
    public static final int Bouquet_Of_Flowers = 15;//count from flowers for one bouquet

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] tulipsInput = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int[] daffodilsInput = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> tulipsStack = new ArrayDeque<>();
        fillStack(tulipsInput, tulipsStack);

        ArrayDeque<Integer> daffodilQueue = new ArrayDeque<>();
        fillQueue(daffodilsInput, daffodilQueue);

        int bouquetCounter = 0;
        int reserveFlowers = 0;
        while (!tulipsStack.isEmpty() && !daffodilQueue.isEmpty()) {
            int daffodilFlowerCount = daffodilQueue.poll();
            int tulipFlowerCount = tulipsStack.pop();

            int sum = daffodilFlowerCount + tulipFlowerCount;
            if (sum == Bouquet_Of_Flowers) {
                bouquetCounter++;
            } else if (sum > Bouquet_Of_Flowers) {

                while (sum > 15) {
                    tulipFlowerCount -= 2;
                    sum = tulipFlowerCount + daffodilFlowerCount;
                    if (sum < 15) {
                        reserveFlowers += sum;
                    } else if (sum == 15) {
                        bouquetCounter++;

                    }


                }

            }else {
               reserveFlowers+=sum;
            }


        }
        if (reserveFlowers >= Bouquet_Of_Flowers) {
          int extraBouquets= reserveFlowers / Bouquet_Of_Flowers;
          bouquetCounter+=extraBouquets;
        }
        if (bouquetCounter >= 5) {
            System.out.printf("You made it! You go to the competition with %d bouquets!", bouquetCounter);
        } else {
            System.out.printf("You failed... You need more %d bouquets.", 5 - bouquetCounter);
        }
        


    }

    private static void fillStack(int[] tulipsInput, ArrayDeque<Integer> tulipsStack) {
        Arrays.stream(tulipsInput).forEach(tulipsStack::push);
    }

    private static void fillQueue(int[] daffodilsInput, ArrayDeque<Integer> daffodilQueue) {
        Arrays.stream(daffodilsInput).forEach(daffodilQueue::offer);
    }

}
