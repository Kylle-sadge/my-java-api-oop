package problemsolver; // package declaration, must be the first line

import java.util.LinkedList;  // LinkedList is the class that will implement our Queue
import java.util.Queue;       // Queue is the interface we program against

public class QueueDemo {                              // public class name matches file name: QueueDemo.java
    public static void main(String[] args) {           // program entry point

        System.out.println("=== QUEUE (FIFO) ===");     // FIFO = First In, First Out

        Queue<Integer> queue = new LinkedList<>();       // declare a Queue of Integers, backed by a LinkedList

        queue.offer(1);                                  // offer() enqueues (adds) an element to the back of the queue
        queue.offer(2);                                  // second element added to the back
        queue.offer(3);                                  // third element added to the back; queue is now [1, 2, 3]

        System.out.println("Queue: " + queue);            // print the current queue, front to back

        System.out.println("Dequeue: " + queue.poll());
        // poll() removes and returns the element at the FRONT of the queue (which is 1)
        // if the queue were empty, poll() would return null instead of throwing an error

        System.out.println("Front (peek): " + queue.peek());
        // peek() looks at the front element WITHOUT removing it (now 2, since 1 was already removed)

        System.out.println("Queue after dequeue: " + queue);  // confirm queue is now [2, 3]
    }
}