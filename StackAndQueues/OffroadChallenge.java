package Exams.StackAndQueues;

import java.util.*;

public class OffroadChallenge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // initial fuel
        int[] fuel = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] consumptionLevel = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] fuelNeeded = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> fuelQueue = new ArrayDeque<>();
        ArrayDeque<Integer> consumptionLevelQueue = new ArrayDeque<>();
        ArrayDeque<Integer> altitudesMark = new ArrayDeque<>();
        fillStack(fuelQueue, fuel);
        fillQueue(consumptionLevelQueue, consumptionLevel);
        fillQueue(altitudesMark, fuelNeeded);

        int altitude = 0;
        Map<String, List<Integer>> altitudeList = new TreeMap<>();

        while (!fuelQueue.isEmpty() && !consumptionLevelQueue.isEmpty() && !altitudesMark.isEmpty()) {

            int tankLevel = fuelQueue.peek();
            int currentConsumption = consumptionLevelQueue.peek();
            int neededFuel = altitudesMark.peek();

            if (tankLevel - currentConsumption >= neededFuel) {
                altitude++;
                System.out.println("John has reached: Altitude " + altitude);
                altitudeList.put("Altitude", new ArrayList<>());
                altitudeList.get("Altitude").add(altitude);
                //remove elements
                fuelQueue.poll();
                consumptionLevelQueue.pop();
                altitudesMark.pop();
            } else {
                fuelQueue.poll();
                consumptionLevelQueue.pop();
                altitudesMark.pop();

                altitude++;
                System.out.println("John did not reach: Altitude " + altitude);
                break;

            }

        }
        if (altitudesMark.isEmpty()){
            System.out.println("John has reached all the altitudes and managed to reach the top!");
        }else {
            System.out.println("John failed to reach the top.");
            if (fuelQueue.isEmpty() && altitude < 1) {
                System.out.println("John didn't reach any altitude.");
            }else {
                System.out.print("Reached altitudes: ");
            }
        }




    }

    private static void fillStack(ArrayDeque<Integer> fuelQueue, int[] fuel) {
        Arrays.stream(fuel).forEach(fuelQueue::offer);

    }

    private static void fillQueue(ArrayDeque<Integer> stack, int[] arr) {
        Arrays.stream(arr).forEach(stack::push);
    }
}
