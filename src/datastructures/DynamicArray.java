package datastructures;

public class DynamicArray {
    private int[] data;
    private int size;

    public DynamicArray(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public void add(int value) {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("Index out of bounds");
        return data[index];
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size)
            throw new RuntimeException("Index out of bounds");
        data[index] = value;
    }

    public int remove(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("Index out of bounds");

        int removed = data[index];

        // elemanları sola kaydır
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        return removed;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length * 2;
            int[] newArr = new int[newCapacity];

            for (int i = 0; i < data.length; i++) {
                newArr[i] = data[i];
            }

            data = newArr;
        }
    }
}
