package Exams.StackAndQueues;

import java.util.*;

public class MagicBox {
    private static int sum = 0;
    private static int evenNumbersSum = 0;
    private static int firstNum = 0;
    private static int secondNum = 0;
    private static final int QUALITY_SUM_OF_CLAIMED_ITEMS = 90;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstBoxInput = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] secondBoxBoxInput = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Deque<Integer> firstBox = new ArrayDeque<>();
        Deque<Integer> secondBox = new ArrayDeque<>();
        fillQueue(firstBox, firstBoxInput);
        fillStack(secondBoxBoxInput, secondBox);
        magicBoxSumming(firstBox, secondBox);
        printOutput(firstBox, secondBox);


    }

    private static void printOutput(Deque<Integer> firstBox, Deque<Integer> secondBox) {
        if (isEmptyFirstBox(firstBox)){
            System.out.println("First magic box is empty.");
        }
        if (isEmptySecondBox(secondBox)){
            System.out.println("Second magic box is empty.");
        }
        if (QUALITY_SUM_OF_CLAIMED_ITEMS <=evenNumbersSum){
            System.out.printf("Wow, your prey was epic! Value: %d",evenNumbersSum);
        }else {
            System.out.printf("Poor prey... Value: %d",evenNumbersSum);
        }
    }

    private static void magicBoxSumming(Deque<Integer> firstBox, Deque<Integer> secondBox) {
        while (!firstBox.isEmpty() && !secondBox.isEmpty()) {
            firstNum = firstBox.peek();
            secondNum = secondBox.peek();
            sum = firstNum + secondNum;

            if (isEvenNumber(sum)) {
               evenNumbersSum+=sum;
                firstBox.pop();
                secondBox.poll();
            } else {
                int numberToRemove = secondBox.poll();
                firstBox.offerLast(numberToRemove);
            }


        }
    }

    private static boolean isEmptyFirstBox(Deque<Integer> firstBox){
        return firstBox.isEmpty();
    }
    private static boolean isEmptySecondBox (Deque<Integer>secondBox){
        return secondBox.isEmpty();
    }

    private static boolean isEvenNumber(int sum) {
        return sum % 2 == 0;
    }

    private static void fillStack(int[] secondBoxBoxInput, Deque<Integer> secondBox) {
        Arrays.stream(secondBoxBoxInput).forEach(secondBox::push);
    }

    private static void fillQueue(Queue<Integer> firstBox, int[] firstBoxInput) {
        Arrays.stream(firstBoxInput).forEach(firstBox::offer);

    }
}
