package Exams.StackAndQueues;

import java.util.*;

public class AutumnCocktails {
    public static final int Pear_Sour = 150;
    public static final int The_Harvest = 250;
    public static final int Apple_Hinny = 300;
    public static final int High_Fashion = 400;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] valuesOfBucket = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] freshnessValues = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer> ingredientsQueue = new ArrayDeque<>();
        ArrayDeque<Integer> freshnessStack = new ArrayDeque<>();
        fillQueue(valuesOfBucket, ingredientsQueue);
        fillStack(freshnessValues, freshnessStack);


        Map<String, Integer> cocktailMap = new TreeMap<>();
        int counter = 0;
        while (!ingredientsQueue.isEmpty() && !freshnessStack.isEmpty()) {
            //take first ingredient
            int ingredient = ingredientsQueue.poll();
            if (ingredient == 0) {
                continue;
            }
            // take last freshness
            int freshness = freshnessStack.pop();
            int totalMix = ingredient * freshness;
            // counter ++;

            switch (totalMix) {
                case Pear_Sour:
                    if (cocktailMap.containsKey("Pear Sour")) {
                        counter = cocktailMap.get("Pear Sour") + 1;
                        cocktailMap.put("Pear Sour", counter);
                    }
                    cocktailMap.putIfAbsent("Pear Sour", 1);
                    break;
                case The_Harvest:
                    if (cocktailMap.containsKey("The Harvest")) {
                        counter = cocktailMap.get("The Harvest") + 1;
                        cocktailMap.put("The Harvest", counter);
                    }

                    cocktailMap.putIfAbsent("The Harvest", 1);

                    break;
                case Apple_Hinny:
                    if (cocktailMap.containsKey("Apple Hinny")) {
                        counter = cocktailMap.get("Apple Hinny") + 1;
                        cocktailMap.put("Apple Hinny", counter);
                    }
                    cocktailMap.putIfAbsent("Apple Hinny", 1);
                    break;
                case High_Fashion:
                    if (cocktailMap.containsKey("High Fashion")) {
                        counter = cocktailMap.get("High Fashion") + 1;
                        cocktailMap.put("High Fashion", counter);
                    }
                    cocktailMap.putIfAbsent("High Fashion", 1);

                    break;
                default:
                    // increase ingredient +5 and put it last
                    int newIngredientValue = ingredient + 5;
                    ingredientsQueue.offerLast(newIngredientValue);
                    break;
            }


        }
        if (isPartyTime(cocktailMap)) {
            System.out.println("It's party time! The cocktails are ready!");

            printMap(cocktailMap);

        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
            if (!ingredientsQueue.isEmpty()) {
                int total = 0;
                for (Integer element : ingredientsQueue) {
                    total += element;
                }
                System.out.println("Ingredients left: " + total);
            }
            printMap(cocktailMap);

        }


    }

    private static void printMap(Map<String, Integer> cocktailMap) {
        cocktailMap.forEach((key, value) -> System.out.printf("# %s --> %d%n", key, value));
    }

    private static boolean isPartyTime(Map<String, Integer> cocktailMap) {

        return cocktailMap.keySet().size() == 4;
    }

    private static void fillStack(int[] freshnessValues, ArrayDeque<Integer> freshnessStack) {
        Arrays.stream(freshnessValues).forEach(freshnessStack::push);
    }

    private static void fillQueue(int[] valuesOfBucket, ArrayDeque<Integer> freshnessQueue) {
        Arrays.stream(valuesOfBucket).forEach(freshnessQueue::offer);
    }
}
