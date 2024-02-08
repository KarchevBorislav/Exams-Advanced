package Exams.StackAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class RubberDuckDebuggers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] timeForTask = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] numberOfTasks = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer> timeQueue = new ArrayDeque<>();
        ArrayDeque<Integer> taskStack = new ArrayDeque<>();
        fillQueue(timeQueue, timeForTask);
        fillStack(taskStack, numberOfTasks);
        int darthVaderDuckyCounter = 0;
        int thorDuckyCounter = 0;
        int bigBlueRubberDuckyCounter = 0;
        int smallYellowRubberDuckyCounter = 0;

        while (!timeQueue.isEmpty() && !taskStack.isEmpty()) {

            int duckCrafter = timeQueue.peek() * taskStack.peek();

            if (duckCrafter > 0 && duckCrafter < 61) {
                darthVaderDuckyCounter++;
                timeQueue.poll();
                taskStack.pop();
            } else if (duckCrafter > 60 && duckCrafter < 121) {

                thorDuckyCounter++;
                timeQueue.poll();
                taskStack.pop();
            } else if (duckCrafter > 120 && duckCrafter < 181) {
                bigBlueRubberDuckyCounter++;
                timeQueue.poll();
                taskStack.pop();

            } else if (duckCrafter > 180 && duckCrafter <= 240) {
                smallYellowRubberDuckyCounter++;
                timeQueue.poll();
                taskStack.pop();
            } else if (duckCrafter > 240) {
                int decreaseTaskValue = taskStack.pop() - 2;
                timeQueue.offerLast(timeQueue.poll());
                taskStack.push(decreaseTaskValue);
            }
        }
        printOutput(darthVaderDuckyCounter, thorDuckyCounter, bigBlueRubberDuckyCounter, smallYellowRubberDuckyCounter);

    }

    private static void printOutput(int darthVaderDuckyCounter, int thorDuckyCounter, int bigBlueRubberDuckyCounter, int smallYellowRubberDuckyCounter) {
        System.out.println("Congratulations, all tasks have been completed! Rubber ducks rewarded:");

        System.out.printf("Darth Vader Ducky: %d%n", darthVaderDuckyCounter);
        System.out.printf("Thor Ducky: %d%n", thorDuckyCounter);
        System.out.printf("Big Blue Rubber Ducky: %d%n", bigBlueRubberDuckyCounter);
        System.out.printf("Small Yellow Rubber Ducky: %d%n", smallYellowRubberDuckyCounter);
    }

    private static void fillStack(ArrayDeque<Integer> taskStack, int[] numberOfTasks) {
        Arrays.stream(numberOfTasks).forEach(taskStack::push);
    }

    private static void fillQueue(ArrayDeque<Integer> timeQueue, int[] timeForTask) {
        Arrays.stream(timeForTask).forEach(timeQueue::offer);
    }
}
