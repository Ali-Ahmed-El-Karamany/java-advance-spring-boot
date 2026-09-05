package com.pioneers.dsa.datastructures.MyList;

import java.util.Arrays;

public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(final T element) {
        ensureCapacity();
        elements[size] = element;
        size++;
    }

    public void set(final int index, final T element) {
        checkIndex(index);

        elements[index] = element;
    }

    public void remove(final int index) {
        checkIndex(index);

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;
    }

    public boolean remove(final T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    remove(i);
                    return true;
                }
            } else if (element.equals(elements[i])) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        final int oldCapacity = elements.length;
        final int newCapacity = oldCapacity + (oldCapacity / 2);

        if (size == elements.length) {
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void checkIndex(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        String result = "[";

        for (int i = 0; i < size; i++) {
            result += elements[i];

            if (i < size - 1){
                result += ", ";
            }
        }

        result += "]";

        return result;
    }
}
