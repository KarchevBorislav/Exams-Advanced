package Exams.StackAndQueues;

import java.util.*;
import java.util.stream.Collectors;

public class OffroadChallenge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] initialFuel = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] consumptionLevel = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] fuelNeeded = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> fuelQueue = new ArrayDeque<>();
        ArrayDeque<Integer> consumptionLevelQueue = new ArrayDeque<>();
        ArrayDeque<Integer> altitudesMark = new ArrayDeque<>();
        fillQueue(fuelQueue, initialFuel);
        fillStack(consumptionLevelQueue, consumptionLevel);
        fillStack(altitudesMark, fuelNeeded);

        int altitude = 0;

        List<String> altitudeList = new ArrayList<>();
        while (!fuelQueue.isEmpty() && !consumptionLevelQueue.isEmpty() && !altitudesMark.isEmpty()) {

            int tankLevel = fuelQueue.peek();
            int currentConsumption = consumptionLevelQueue.peek();
            int neededFuel = altitudesMark.peek();

            if (tankLevel - currentConsumption >= neededFuel) {
                altitude++;
                System.out.println("John has reached: Altitude " + altitude);
                String altitudeToAdd = "Altitude " + altitude;
                altitudeList.add(altitudeToAdd);
                //remove elements
                fuelQueue.poll();
                consumptionLevelQueue.pop();
                altitudesMark.pop();
            } else  {
                fuelQueue.poll();
                consumptionLevelQueue.pop();


                altitude++;
                System.out.println("John did not reach: Altitude " + altitude);

                break;

            }

        }

        if (altitudesMark.isEmpty()) {
            System.out.println("John has reached all the altitudes and managed to reach the top!");
        }else{
            System.out.println("John failed to reach the top.");
            if ( !altitudeList.isEmpty()) {
                System.out.print("Reached altitudes: ");
            } else {
                System.out.println("John didn't reach any altitude.");
            }
            printList(altitudeList);
        }
    }

    private static void printList(List<String> altitudeList) {

        String output = altitudeList.stream().map(String::valueOf).collect(Collectors.joining(", "));

        System.out.println(output);
    }


    private static void fillQueue(ArrayDeque<Integer> fuelQueue, int[] fuel) {
        Arrays.stream(fuel).forEach(fuelQueue::push);

    }

    private static void fillStack(ArrayDeque<Integer> stack, int[] arr) {
        Arrays.stream(arr).forEach(stack::offer);
    }
}
