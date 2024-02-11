package Exams.StackAndQueues;

import java.util.*;

public class ClimbThePeaks {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] quantityOfDalyPortions = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int[] quantityOfDalyStamina = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();

        Stack<Integer> dalyPortionStack = new Stack<>();
        Queue<Integer> staminaQueue = new ArrayDeque<>();
        fillStack(quantityOfDalyPortions, dalyPortionStack);
        fillQueue(quantityOfDalyStamina, staminaQueue);

        Set<String> mountainsConquered = new LinkedHashSet<>();
        Map<String, Integer> mountainMap = new LinkedHashMap<>();
        fillMap(mountainMap);
        while (!dalyPortionStack.isEmpty() && !staminaQueue.isEmpty() && !mountainMap.isEmpty()) {

            for (Map.Entry<String, Integer> entry : mountainMap.entrySet()) {
                int dalyTry = dalyPortionStack.peek() + staminaQueue.peek();
                int value = entry.getValue();
                if (dalyTry >= value) {
                  mountainsConquered.add(entry.getKey());
                    staminaQueue.poll();
                    dalyPortionStack.pop();
                    String keyToRemove = entry.getKey();
                 mountainMap.remove(keyToRemove);

                } else {
                    staminaQueue.poll();
                    dalyPortionStack.pop();

                }
                break;


            }


        }
        if (mountainMap.isEmpty()){
            System.out.println("Alex did it! He climbed all top five Pirin peaks in one week -> @FIVEinAWEEK");
        }else {
            System.out.println("Alex failed! He has to organize his journey better next time -> @PIRINWINS");
        }
        if (mountainsConquered.size()>0){
            System.out.println("Conquered peaks:");
          mountainsConquered.forEach(System.out::println);
        }



    }

    private static void fillQueue(int[] quantityOfDalyStamina, Queue<Integer> staminaQueue) {
        Arrays.stream(quantityOfDalyStamina).forEach(staminaQueue::offer);
    }

    private static void fillStack(int[] quantityOfDalyPortions, Stack<Integer> dalyPortionStack) {
        Arrays.stream(quantityOfDalyPortions).forEach(dalyPortionStack::push);
    }

    private static void fillMap(Map<String, Integer> mountainMap) {
        mountainMap.put("Vihren", 80);
        mountainMap.put("Kutelo", 90);
        mountainMap.put("Banski Suhodol", 100);
        mountainMap.put("Polezhan", 60);
        mountainMap.put("Kamenitza", 70);

    }


}
