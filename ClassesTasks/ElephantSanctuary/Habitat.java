package ElephantSanctuary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Habitat {
    private int capacity;
    private List<Elephant> data;

    public Habitat(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();

    }

    public void add(Elephant elephant) {
        if (this.capacity > this.data.size()) {
            this.data.add(elephant);
        }
    }

    public boolean remove(String name) {
        return this.data.removeIf(elephant -> elephant.getName().equals(name));
    }

    public Elephant getElephant(String retiredFrom) {
        return this.data.stream().filter(elephant -> elephant.getRetiredFrom().equals(retiredFrom)).findFirst().orElse(null);
    }

    public Elephant getOldestElephant() {
        return this.data.stream().max(Comparator.comparing(Elephant::getAge)).orElse(null);
    }

    public int getAllElephants() {
        return this.data.size();
    }

    public String getReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("Saved elephants in the park:").append(System.lineSeparator());
        for (Elephant datum : this.data) {
            builder.append(String.format("%s came from: %s%n", datum.getName(), datum.getRetiredFrom()));
        }


        return builder.toString().trim();


    }

}
