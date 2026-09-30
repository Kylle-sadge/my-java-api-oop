package problemsolver;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        System.out.println("=== SET (no duplicates) ===");
        Set<String> nameSet = new HashSet<>();
        nameSet.add("Kylle");
        nameSet.add("Andrei");
        nameSet.add("Kylle"); // duplicate, ignored

        System.out.println("Set: " + nameSet);
        System.out.println("Contains 'Kylle'? " + nameSet.contains("Kylle"));
        System.out.println("Size: " + nameSet.size());

        nameSet.remove("Andrei");
        System.out.println("After removing 'Andrei': " + nameSet);
    }
}