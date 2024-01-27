package halloween;

import java.util.ArrayList;
import java.util.List;

public class House {
    private int capacity;
    private List<Kid> data;

    public House(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }
    public int getAllKids(){
        return this.data.size();
    }

    public void addKid(Kid kid) {
        if (this.capacity > this. data.size()) {
            this.data.add(kid);
        }

    }

    public boolean removeKid(String name) {
        for (Kid kid : this.data) {
            if (kid.getName().equals(name)) {
                this.data.remove(kid);
                return true;
            }
        }
        return false;
    }
    public Kid getKid(String street){
        for (Kid kid : this.data) {
            if (kid.getStreet().equals(street)){
                return kid;
            }

        }
        return null;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Kid> getData() {
        return data;
    }

    public void setData(List<Kid> data) {
        this.data = data;
    }


    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        //" Children who visited a house for candy:
        //		 {name} from {street} street
        //          {name} from {street} street
        builder.append("Children who visited a house for candy:").append(System.lineSeparator());
        for (Kid kid : this.data) {
            builder.append(String.format("%s from %s street",kid.getName(),kid.getStreet())).append(System.lineSeparator());

        }
        return builder.toString();
    }
}
