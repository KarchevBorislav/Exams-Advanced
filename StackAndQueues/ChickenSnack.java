import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class ChickenSnack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] amountOfMoney = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] priceForFood = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer> moneyStack = new ArrayDeque<>();
        ArrayDeque<Integer> priceQueue = new ArrayDeque<>();
        fillStack(amountOfMoney, moneyStack);
        fillQueue(priceForFood, priceQueue);

        int foodAte = 0;

        while (!moneyStack.isEmpty() && !priceQueue.isEmpty()) {
            int money = moneyStack.peek();
            int foodPrice = priceQueue.peek();

            if (money == foodPrice) {
                foodAte++;
                moneyStack.pop();
                priceQueue.poll();
            } else if (money > foodPrice) {
                foodAte++;
                int differences = money - foodPrice;
                priceQueue.poll();
                if (priceQueue.isEmpty()) {
                    break;
                }
                moneyStack.pop();
                moneyStack.push(moneyStack.pop() + differences);


            } else {
                moneyStack.pop();
                priceQueue.poll();

            }


        }
        if (foodAte >= 4) {
            System.out.printf("Gluttony of the day! Henry ate %d foods.%n", foodAte);
        }
        if (foodAte > 1 && foodAte < 4) {
            System.out.printf("Henry ate: %d foods.%n", foodAte);

        }
        if (foodAte == 1) {
            System.out.printf("Henry ate: %d food.%n", foodAte);
        }
        if (foodAte == 0) {
            System.out.println("Henry remained hungry. He will try next weekend again.");
        }


    }

    private static void fillQueue(int[] priceForFood, ArrayDeque<Integer> priceQueue) {
        Arrays.stream(priceForFood).forEach(priceQueue::offer);
    }

    private static void fillStack(int[] amountOfMoney, ArrayDeque<Integer> moneyStack) {
        Arrays.stream(amountOfMoney).forEach(moneyStack::push);
    }
}
