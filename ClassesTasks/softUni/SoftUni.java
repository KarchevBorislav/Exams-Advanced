package softUni;

import java.util.ArrayList;
import java.util.List;

public class SoftUni {
    private int capacity;
    private List<Student> data;

    public SoftUni(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount() {
        return this.data.size();
    }

    public String insert(Student student) {
        if (this.data.size() < this.capacity) {
            if (this.data.contains(student)) {
                return "Student is already in the hall.";
            } else {
                this.data.add(student);
                return String.format("Added student %s %s.", student.getFirstName(), student.getLastName());
            }

        }
        return "The hall is full.";
    }

    public String remove(Student student) {
        if (this.data.contains(student)) {
            this.data.remove(student);
            return String.format("Removed student %s %s.", student.getFirstName(), student.getLastName());
        } else {
            return "Student not found.";
        }

    }

    public Student getStudent(String firstName, String lastName) {
        return this.data.stream().filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)).findFirst().orElse(null);

    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hall size: %d", this.data.size())).append(System.lineSeparator());
        for (Student student : this.data) {
            builder.append(String.format("Student: %s %s, Best Course = %s%n", student.getFirstName(), student.getLastName(), student.getBestCourse()));

        }
        return builder.toString().trim();
    }


}
