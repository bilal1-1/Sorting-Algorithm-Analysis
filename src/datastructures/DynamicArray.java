package datastructures;

public class DynamicArray {
    private int[] data; // the internal array
    private int size;  // number of stored elements

    public DynamicArray(int capacity) { 
        data = new int[capacity];  // create array with given capacity
        size = 0;  // start empty
    }

    public void add(int value) {
        ensureCapacity();  // grow array if full
        data[size] = value;  // place value at the end
        size++;  // increase element count
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("Index out of bounds"); // check index validity
        return data[index];  // return the element
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size)
            throw new RuntimeException("Index out of bounds"); // check index validity
        data[index] = value;   // update value
    }

    public int remove(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("Index out of bounds");  // check index validity

        int removed = data[index];  // store removed value

        
        for (int i = index; i < size - 1; i++) {  // shift elements left to fill the gap
            data[i] = data[i + 1];
        }

        size--;  // reduce element count
        return removed;  // return removed value
    }

    public int size() {
        return size;  // return current number of elements
    }

    private void ensureCapacity() {
        if (size == data.length) {    // if array is full
            int newCapacity = data.length * 2;  // double capacity
            int[] newArr = new int[newCapacity];

            for (int i = 0; i < data.length; i++) {
                newArr[i] = data[i];    // copy all elements
            }

            data = newArr;   // replace with new array
        }
    }
}
