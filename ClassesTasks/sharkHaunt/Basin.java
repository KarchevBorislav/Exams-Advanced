package sharkHaunt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Basin {
    private String name;
    private int capacity;
    private List<Shark> sharks;

    public Basin(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.sharks = new ArrayList<>();
    }

    public void addShark(Shark shark) {
        if (this.capacity > this.sharks.size()) {
            this.sharks.add(shark);
        } else {
            System.out.println("This basin is at full capacity!");
        }
    }
    public boolean removeShark(String kind){
        return this.sharks.removeIf(shark -> shark.getKind().equals(kind));
    }
    public Shark getLargestShark(){
        return this.sharks.stream().max(Comparator.comparing(Shark::getLength)).orElse(null);
    }
    public Shark getShark(String kind){
        return this.sharks.stream().filter(shark -> shark.getKind().equals(kind)).findFirst().orElse(null);
    }
    public int getCount(){
        return this.sharks.size();
    }

    public int getAverageLength(){
        int sum = 0;
        for (Shark shark : this.sharks) {
            sum+=shark.getLength();

        }
        return sum / getCount();

    }
    public String report(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Sharks in %s:",this.name)).append(System.lineSeparator());
        for (Shark shark : this.sharks) {
            builder.append(String.format("The %s is %d centimeters long, eats %s and inhabits %s.",shark.getKind(),shark.getLength(),shark.getFood(),shark.getHabitation())).append(System.lineSeparator());

        }
        return builder.toString();

    }

}
