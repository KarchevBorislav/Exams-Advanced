package Exams.StackAndQueues;

import java.util.*;
import java.util.stream.Collectors;

public class EnergyDrinks {
    private static final int CAFFEINE_LIMIT_PER_DAY = 300;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] caffeineInput = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int[] energyDrinksInput = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        Stack<Integer> caffeinStack = new Stack<>();
        Queue<Integer> energyDrinkQueue = new ArrayDeque<>();
        fillStack(caffeineInput, caffeinStack);
        fillQueue(energyDrinksInput, energyDrinkQueue);
        int caffeineAmountPerDay = 0;
        while (!caffeinStack.isEmpty() && !energyDrinkQueue.isEmpty()) {
            int calculateCaffeine = caffeinStack.peek() * energyDrinkQueue.peek();
            if (calculateCaffeine + caffeineAmountPerDay <= CAFFEINE_LIMIT_PER_DAY) {
                caffeineAmountPerDay += calculateCaffeine;
                caffeinStack.pop();
                energyDrinkQueue.poll();
            } else if (caffeineAmountPerDay + calculateCaffeine > CAFFEINE_LIMIT_PER_DAY) {
                caffeinStack.pop();
                energyDrinkQueue.offer(energyDrinkQueue.poll());
                caffeineAmountPerDay -= 30;
                if (caffeineAmountPerDay < 0) {
                    caffeineAmountPerDay = 0;
                }
            }
        }
        if (!energyDrinkQueue.isEmpty()) {
            System.out.print("Drinks left: ");
            System.out.println(energyDrinkQueue.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        }else {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        }
        System.out.printf("Stamat is going to sleep with %d mg caffeine.",caffeineAmountPerDay);


    }

    private static void fillQueue(int[] energyDrinksInput, Queue<Integer> energyDrinkQueue) {
        Arrays.stream(energyDrinksInput).forEach(energyDrinkQueue::offer);
    }

    private static void fillStack(int[] caffeineInput, Stack<Integer> caffeinStack) {
        Arrays.stream(caffeineInput).forEach(caffeinStack::push);
    }
}
