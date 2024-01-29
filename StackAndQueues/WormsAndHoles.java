package Exams.StackAndQueues;

import java.util.*;
import java.util.stream.Collectors;

public class WormsAndHoles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] worms = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] holes = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Deque<Integer> wormsStack = new ArrayDeque<>();
        Deque<Integer> holesQueue = new ArrayDeque<>();
        fillStack(wormsStack, worms);
        fillQueue(holesQueue, holes);
        int matches = 0;

        int wormsSize = wormsStack.size();

        while (!wormsStack.isEmpty() && !holesQueue.isEmpty()) {

            int wormValue = wormsStack.peek();
            int holesValue = holesQueue.peek();
            if (wormValue == holesValue) {
                wormsStack.pop();
                holesQueue.poll();
                matches++;
            } else {
                wormsStack.push(wormsStack.pop() - 3);
                if (wormsStack.peek() <= 0) {
                    wormsStack.pop();
                }
                holesQueue.poll();

            }

        }
        List<String> reverseList;
        if (matches > 0) {
            System.out.printf("Matches: %d%n", matches);
        } else {
            System.out.println("There are no matches.");
        }
        if (matches != wormsSize) {
            if (!wormsStack.isEmpty()) {
                reverseList = wormsStack.stream().map(String::valueOf).collect(Collectors.toList());
                Collections.reverse(reverseList);
                System.out.println("Worms left: " + String.join(", ", reverseList));

            } else {
                System.out.println("Worms left: none");
            }
        } else {
            System.out.println("Every worm found a suitable hole!");
        }

        if (holesQueue.isEmpty()) {
            System.out.println("Holes left: none");
        } else {
            System.out.println("Holes left: " + holesQueue.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        }

    }


    private static void fillQueue(Deque<Integer> holesQueue, int[] holes) {
        Arrays.stream(holes).forEach(holesQueue::offer);
    }

    private static void fillStack(Deque<Integer> wormsStack, int[] worms) {
        Arrays.stream(worms).forEach(wormsStack::push);

    }
}