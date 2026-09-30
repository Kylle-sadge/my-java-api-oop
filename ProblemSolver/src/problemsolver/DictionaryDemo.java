package problemsolver; // package declaration

import java.util.HashMap;  // HashMap implements the key-value pair storage (like a C hash table)
import java.util.Map;      // Map is the interface we program against

public class DictionaryDemo {                          // public class name matches file name: DictionaryDemo.java
    public static void main(String[] args) {            // program entry point

        System.out.println("=== DICTIONARY (KEY-VALUE MAP) ===");

        Map<String, Integer> ages = new HashMap<>();      // declare a Map with String keys and Integer values

        ages.put("Kylle", 20);                             // put(key, value) inserts a new key-value pair
        ages.put("Andrei", 22);                             // second pair added
        ages.put("Kylle", 21);                              // same key "Kylle" used again: this OVERWRITES the old value (20 -> 21)

        System.out.println("Ages map: " + ages);             // print all key-value pairs currently stored

        System.out.println("Kylle's age: " + ages.get("Kylle"));
        // get(key) retrieves the value for that key; returns null if the key doesn't exist

        System.out.println("Contains key 'Bob'? " + ages.containsKey("Bob"));
        // containsKey() checks if a key exists, without needing to retrieve its value

        System.out.println("\nIterating entries:");           // header before the loop output

        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            // entrySet() returns all key-value pairs as Map.Entry objects, which we can loop through
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
            // getKey() and getValue() extract each piece from the current Entry
        }
    }
}