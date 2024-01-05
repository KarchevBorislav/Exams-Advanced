package Exams.StackAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class MonsterExtermination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] armorOfTheMonster = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] soldierStrikeValue = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        Deque<Integer> monsterPower = new ArrayDeque<>();
        Deque<Integer> soldierPower = new ArrayDeque<>();

        Arrays.stream(armorOfTheMonster).forEach(monsterPower::offer);
        Arrays.stream(soldierStrikeValue).forEach(soldierPower::push);

        int deadMonsterCounter = 0;


        while (!monsterPower.isEmpty() && !soldierPower.isEmpty()) {
            int armour = monsterPower.peek();
            int strike = soldierPower.peek();
            if (strike >= armour) {
                deadMonsterCounter++;
              strike-=armour;


                if (strike == 0) {
                    soldierPower.pop();
                    monsterPower.poll();

                }else {
                    monsterPower.poll();
                    if (soldierPower.size() == 1){
                        soldierPower.pop();
                        soldierPower.push(strike);
                    }else {
                        soldierPower.pop();
                        int tempStrike = strike;
                        int previousStrike = soldierPower.pop();
                        soldierPower.push(previousStrike + tempStrike);
                    }
                }

            } else {

                armour -= strike;
                soldierPower.pop();
                monsterPower.poll();
                monsterPower.offer(armour);




            }

        }
        if (soldierWins(monsterPower)) {
           System.out.println("All monsters have been killed!");

       }
        if (monsterWin(soldierPower)) {
            System.out.println("The soldier has been defeated.");

       }
        System.out.printf("Total monsters killed: %d%n", deadMonsterCounter);

    }


    private static boolean soldierWins(Deque<Integer> monsterPower) {
        return monsterPower.isEmpty();
    }

    private static boolean monsterWin(Deque<Integer> soldierPower) {
        return soldierPower.isEmpty();
    }


}
