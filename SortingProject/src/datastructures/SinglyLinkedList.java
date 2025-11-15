package datastructures;

public class SinglyLinkedList {
    private Node head;
    private int size;

    // İç düğüm sınıfı
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Listenin başına eleman ekleme
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Listenin sonuna eleman ekleme
    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Belirli bir değeri silme (ilk bulduğunu)
    public boolean remove(int value) {
        if (head == null) return false;

        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }

        if (current.next == null) return false; // bulunamadı

        current.next = current.next.next;
        size--;
        return true;
    }

    // Bir değerin listede olup olmadığını kontrol et
    public boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) return true;
            current = current.next;
        }
        return false;
    }

    // Listedeki eleman sayısı
    public int getSize() {
        return size;
    }

    // Liste boş mu?
    public boolean isEmpty() {
        return head == null;
    }

    // Listeyi yazdır (debug için)
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
