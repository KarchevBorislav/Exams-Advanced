package fishingPond;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Pond {
    private int capacity;
    private List<Fish> fishes;

    public Pond(int capacity) {
        this.capacity = capacity;
        this.fishes = new ArrayList<>();
    }

    public void addFish(Fish fish) {
        if (this.capacity > this.fishes.size()) {
            this.fishes.add(fish);
        }
    }

    public boolean removeFish(String species) {
        return this.fishes.removeIf(fish -> fish.getSpecies().equals(species));
    }

    public Fish getOldestFish() {
        return this.fishes.stream().max(Comparator.comparing(Fish::getAge)).orElse(null);

    }

    public Fish getFish(String species) {
        return this.fishes.stream().filter(fish -> fish.getSpecies().equals(species)).findFirst().orElse(null);
    }

    public int getCount() {
        return this.fishes.size();
    }

    public int getVacancies() {
        return this.capacity - getCount();
    }

    public String report() {
        StringBuilder build = new StringBuilder();
        build.append("Fishes in the pond:");
        build.append(System.lineSeparator());
        fishes.forEach(fish -> build.append(fish.toString()).append(String.format(System.lineSeparator())));

        return build.toString().trim();
    }


}
