package datastructures;

public class SinglyLinkedList {
    private Node head;   // reference to the first node in the list
    private int size;   // keeps track of how many nodes are in the list

    // inner node class
    private static class Node {  
        int data;   // value stored in this node
        Node next;  // pointer to the next node in the list

        Node(int data) {
            this.data = data;  // set node value
            this.next = null;  // next starts as null
        }
    }

    // add an element to the beginning of the list
    public void addFirst(int value) {
        Node newNode = new Node(value);  // create a new node with the given value
        newNode.next = head;   // link the new node to the current head node
        head = newNode;  // update head to point to this new node
        size++;   // increase size because we added a node
    }

    // add an element to the end of the list
    public void addLast(int value) {
        Node newNode = new Node(value);   // create a new node for the end of the list
        if (head == null) {
            head = newNode;     // if the list is empty, new node becomes the head
        } else {
            Node current = head;
            while (current.next != null) {  // move through the list to find the last node
                current = current.next;
            }
            current.next = newNode;   // attach the new node at the end
        }
        size++;   // update size after adding
    }

    // remove the first occurrence of a given value
    public boolean remove(int value) {
        if (head == null) 
            return false;  // cannot remove from an empty list

        if (head.data == value) {
            head = head.next;     // if the value is in the first node, remove it by shifting the head
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;     // move until you find the node before the target node
        }

        if (current.next == null) 
            return false; // reached the end without finding the value

        current.next = current.next.next;  // skip the node to remove it from the chain
        size--;
        return true;   // removal successful
    }

    // check if a value exists in the list
    public boolean contains(int value) {
        Node current = head;   // start from the first node
        while (current != null) {
            if (current.data == value) // value found in the list
                return true;
            current = current.next;  // move to the next node
        }
        return false;   // value not found anywhere
    }

    // number of elements in the list
    public int getSize() {
        return size;  // return how many nodes are currently in the list
    }

    // is the list empty?
    public boolean isEmpty() {
        return head == null;   // list is empty if head has no node
    }

    // print the list (for debugging)
    public void printList() {
        Node current = head;   // start printing from the head
        while (current != null) {
            System.out.print(current.data + " -> ");  // print value of each node
            current = current.next;   // move to next node
        }
        System.out.println("null");   // end of list indicator
    }
    //For Application
    @Override
    public String toString() {
        if (head == null) {
            return "List is empty (null)";
        }
        
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data); 
            if (current.next != null) {
                sb.append(" -> "); 
            }
            current = current.next;
        }
        return sb.toString();
    }
}
