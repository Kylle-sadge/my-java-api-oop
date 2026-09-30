package problemsolver; // package declaration

import java.util.LinkedList;  // LinkedList: a doubly-linked list implementation (like your C struct Node with next/prev pointers)

public class OrderedList {                          // public class name matches file name: OrderedListDemo.java
    public static void main(String[] args) {             // program entry point

        System.out.println("=== ORDERED LIST (LINKED LIST) ===");

        LinkedList<String> list = new LinkedList<>();      // declare a LinkedList of Strings

        list.add("B");                                     // add() appends to the end by default; list is now [B]
        list.add("C");                                      // list is now [B, C]

        list.addFirst("A");                                 // addFirst() inserts at the very beginning (the head)
                                                              // list is now [A, B, C]

        list.addLast("D");                                  // addLast() inserts at the very end (the tail)
                                                              // list is now [A, B, C, D]

        list.add(2, "Inserted");                            // add(index, value) inserts at a specific position (0-based)
                                                              // list is now [A, B, Inserted, C, D]

        System.out.println("List: " + list);                 // print the full list in order

        System.out.println("Element at index 3: " + list.get(3));
        // get(index) retrieves the element at that position (index 3 = "C" here)

        list.remove("C");                                    // remove(Object) deletes the FIRST occurrence matching that value

        System.out.println("List after removing 'C': " + list);  // confirm list is now [A, B, Inserted, D]
    }
}