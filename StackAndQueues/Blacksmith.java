package Exams.StackAndQueues;

import java.util.*;
import java.util.stream.Collectors;

public class Blacksmith {
    private static final int GLADIUS = 70;

    private static final int SHAMSHIR = 80;
    private static final int KATANA = 90;
    private static final int Sabre = 110;
    private static final String GLADIUS_SWORD = "Gladius";
    private static final String KATANA_SWORD = "Katana";
    private static final String SHAMSHIR_SWORD = "Shamshir";
    private static final String SABRE_SWORD = "Sabre";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] steelSequence = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] carbonSequence = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Deque<Integer> steelQueue = new ArrayDeque<>();
        fillStack(steelSequence, steelQueue);
        Deque<Integer> carbonStack = new ArrayDeque<>();
        fillQueue(carbonSequence, carbonStack);
        Map<String, Integer> swordMap = new TreeMap<>();
        craftSwords(swordMap, steelQueue, carbonStack);
        printOutput(swordMap, steelQueue, carbonStack);
        printSteelLeftInQueue(steelQueue);
        printCarbonLeftInStack(carbonStack);
        printMap(swordMap);


    }

    private static void printMap(Map<String, Integer> swordMap) {
        swordMap.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));

    }

    private static void printSteelLeftInQueue(Deque<Integer> steelQueue) {
        if (steelQueue.isEmpty()) {
            System.out.println("Steel left: none");
        } else {
            System.out.println("Steel left:");
            Collection<String> steel = Collections.singleton(steelQueue.stream().map(Object::toString).collect(Collectors.joining(", ")));
            steel.forEach(System.out::print);
        }

    }

    private static void printCarbonLeftInStack(Deque<Integer> carbonStack) {
        if (carbonStack.isEmpty()) {
            System.out.println("Carbon left: none");
        } else {
            Collection<String> carbon = Collections.singleton(carbonStack.stream().map(Object::toString).collect(Collectors.joining(", ")));
            System.out.print("Carbon left: ");
            carbon.forEach(System.out::print);

        }
        System.out.println();
    }


    private static void printOutput(Map<String, Integer> swordMap, Deque<Integer> steelQueue, Deque<Integer> carbonStack) {
        if (isBiggerSizeThenZero(swordMap)) {

            int countOfSwords = swordMap.values().stream().mapToInt(value -> value).sum();
            System.out.printf("You have forged %d swords.%n", countOfSwords);
        } else {
            System.out.println("You did not have enough resources to forge a sword.");
        }

    }

    private static boolean isBiggerSizeThenZero(Map<String, Integer> swordMap) {
        return swordMap.size() > 0;
    }


    private static void craftSwords(Map<String, Integer> swordMap, Deque<Integer> steelQueue, Deque<Integer> carbonStack) {
        int gladiusCounter = 0;
        int shamsirCounter = 0;
        int katanaCounter = 0;
        int sabreCounter = 0;
        while (!steelQueue.isEmpty() && !carbonStack.isEmpty()) {
            int steelQuantity = steelQueue.peek();
            int carbonQuantity = carbonStack.peek();
            int swordMix = steelQuantity + carbonQuantity;

            switch (swordMix) {
                case GLADIUS:
                    gladiusCounter++;
                    steelQueue.poll();
                    carbonStack.pop();

                    swordMap.put(GLADIUS_SWORD, gladiusCounter);
                    break;
                case SHAMSHIR:
                    steelQueue.poll();
                    carbonStack.pop();


                    shamsirCounter++;
                    swordMap.put(SHAMSHIR_SWORD, shamsirCounter);
                    break;
                case KATANA:
                    katanaCounter++;
                    steelQueue.poll();
                    carbonStack.pop();


                    swordMap.put(KATANA_SWORD, katanaCounter);
                    break;
                case Sabre:
                    steelQueue.poll();
                    carbonStack.pop();


                    sabreCounter++;
                    swordMap.put(SABRE_SWORD, sabreCounter);
                    break;
                default:
                    steelQueue.poll();
                    int newCarbonValue = carbonStack.pop() + 5;
                    carbonStack.push(newCarbonValue);


            }

        }
    }

    private static void fillQueue(int[] carbonSequence, Deque<Integer> carbonStack) {
        Arrays.stream(carbonSequence).forEach(carbonStack::push);
    }

    private static void fillStack(int[] steelSequence, Deque<Integer> steelQueue) {
        Arrays.stream(steelSequence).forEach(steelQueue::offer);
    }
}
