package Exams.StackAndQueues;

import java.util.*;
import java.util.stream.Collectors;

public class ApocalypsePreparation {
    private static final int PATCH = 30;
    private static final int BANDAGE = 40;
    private static final int MEDIC_KIT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] textiles = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] medicaments = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> textileQueue = new ArrayDeque<>();
        fillQueue(textiles, textileQueue);
        ArrayDeque<Integer> medicamentStack = new ArrayDeque<>();
        fillStack(medicamentStack, medicaments);

        Map<String, Integer> itemsMap = new TreeMap<>();
        int patchCounter = 0;
        int bandageCounter = 0;
        int medicKitCounter = 0;

        while (!medicamentStack.isEmpty() && !textileQueue.isEmpty()) {

            int healingItem = medicamentStack.peek() + textileQueue.peek();
            if (healingItem == PATCH) {
                patchCounter++;

                textileQueue.poll();
                medicamentStack.pop();

            } else if (healingItem == BANDAGE) {
                textileQueue.poll();
                medicamentStack.pop();

                bandageCounter++;


            } else if (healingItem >= MEDIC_KIT) {
                medicKitCounter++;


                if (healingItem > MEDIC_KIT) {
                    textileQueue.poll();
                    medicamentStack.pop();
                    medicamentStack.push(medicamentStack.poll() + (healingItem - MEDIC_KIT));
                } else {
                    textileQueue.poll();
                    medicamentStack.pop();
                }

            } else {
                textileQueue.poll();
                medicamentStack.push(medicamentStack.poll() + 10);

            }

        }
        if (medicamentStack.isEmpty() && !textileQueue.isEmpty()) {
            System.out.println("Medicaments are empty.");
        } else if (textileQueue.isEmpty() && !medicamentStack.isEmpty()) {
            System.out.println("Textiles are empty.");
        } else {
            System.out.println("Textiles and medicaments are both empty.");
        }
        if (patchCounter > 0) {
            itemsMap.put("Patch", patchCounter);
        }
        if (bandageCounter > 0) {
            itemsMap.put("Bandage", bandageCounter);
        }
        if (medicKitCounter > 0) {
            itemsMap.put("MedKit", medicKitCounter);
        }
        itemsMap.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(entry -> {
                    System.out.printf("%s - %d%n", entry.getKey(), entry.getValue());
                });
        if (!textileQueue.isEmpty()) {
            List<String> textileList = Collections.singletonList(textileQueue.stream().map(String::valueOf).collect(Collectors.joining(", ")));
            for (String s : textileList) {
                System.out.print("Textiles left: " + s);
            }

        }

        if (!medicamentStack.isEmpty()) {
            List<String> medicList = Collections.singletonList(medicamentStack.stream().map(String::valueOf).collect(Collectors.joining(", ")));
            for (String s : medicList) {
                System.out.print("Medicaments left: " + s);
            }


        }
    }

    private static void fillQueue(int[] textile, ArrayDeque<Integer> textilQueue) {
        Arrays.stream(textile).forEach(textilQueue::offer);
    }

    private static void fillStack(ArrayDeque<Integer> medicamentStack, int[] medicaments) {
        Arrays.stream(medicaments).forEach(medicamentStack::push);
    }

}
