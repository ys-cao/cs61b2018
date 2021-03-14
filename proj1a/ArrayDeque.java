public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INITIALIZED_CAPACITY = 8;

    /* Create an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[INITIALIZED_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /* Add an element to the first. */
    public void addFirst(T item) {
        checkSize();
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst = nextFirst - 1;
        }
        size += 1;
    }

    /* Add an element to the last. */
    public void addLast(T item) {
        checkSize();
        items[nextLast] = item;
        nextLast = nextLast + 1;
        size += 1;
    }

    /* Check the size of the ArrayDeque to see if needed resize(). */
    public void checkSize() {
        if (size() == items.length) {
            resize_expand();
        }
        double curr_ratio = size() / items.length;
        if (items.length > 16 && curr_ratio < 0.25) {
            resize_shrink();
        }
    }

    /* Expand the array. */
    public void resize_expand() {
        T[] resized_items = (T[]) new Object[size() * 4];
        System.arraycopy(items, 0, resized_items, 0, size());
        nextFirst = resized_items.length - 1;
        nextLast = items.length;
    }

    /* Shrink the array. */
    public void resize_shrink() {
        T[] resized_items = (T[]) new Object[items.length * 2];
        System.arraycopy(items, 0, resized_items, 0, size());
        nextFirst = resized_items.length - 1;
        nextLast = items.length;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
    }

    /* Removes and returns the item at the front of the deque.
    If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0 || items[0] == null) {
            return null;
        }
        T t = items[0];
        items[0] = null;
        size -= 1;
        checkSize();
        return t;
    }

    /* Removes and returns the item at the back of the deque.
    If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0 || items[size() - 1] == null) {
            return null;
        }
        T t = items[size - 1];
        items[size - 1] = null;
        size -= 1;
        checkSize();
        return t;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index >= 0 && index < size) {
            return items[index];
        }
        return null;
    }
}
