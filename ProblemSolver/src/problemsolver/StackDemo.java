package problemsolver; // package declaration

import java.util.Deque;      // Deque = Double Ended Queue; can act as a stack or a queue
import java.util.ArrayDeque; // ArrayDeque is the recommended modern class for stack behavior in Java

public class StackDemo {                               // public class name matches file name: StackDemo.java
    public static void main(String[] args) {            // program entry point

        System.out.println("=== STACK (LIFO) ===");      // LIFO = Last In, First Out

        Deque<Integer> stack = new ArrayDeque<>();        // declare a Deque of Integers, used here as a Stack

        stack.push(1);                                    // push() adds an element to the TOP of the stack
        stack.push(2);                                    // second element pushed on top of 1
        stack.push(3);                                    // third element pushed on top; stack top-to-bottom is [3, 2, 1]

        System.out.println("Stack: " + stack);             // print current stack, top element shown first

        System.out.println("Pop: " + stack.pop());
        // pop() removes and returns the TOP element (3, the most recently pushed)
        // if the stack were empty, pop() would throw a NoSuchElementException

        System.out.println("Top (peek): " + stack.peek());
        // peek() looks at the top element WITHOUT removing it (now 2, since 3 was already popped)

        System.out.println("Stack after pop: " + stack);   // confirm stack is now [2, 1]
    }
}