/* Double end linked list. */
public class LinkedListDeque<T> {

    /* Create TNode Class. */
    private class TNode {
        private TNode prev;
        private TNode next;
        private T item;

        public TNode(T t, TNode prv, TNode nxt) {
            item = t;
            next = nxt;
            prev = prv;
        }
    }
    /* Create a sentinel node. */
    private TNode sentinel;

    /* Size of the linked list. */
    private int size;

    /* Create an empty linked list. */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /* Add an element to the first. */
    public void addFirst(T item) {
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = sentinel;
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /* Add an element to the last. */
    public void addLast(T item) {
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
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
        TNode n = sentinel;
        while (n.next != sentinel) {
            System.out.print(n.item + " ");
            n = n.next;
        }
    }

    /* Removes and returns the item at the front of the deque.
    If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T t = sentinel.next.item;

        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        size -= 1;

        return t;
    }

    /* Removes and returns the item at the back of the deque.
    If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T t = sentinel.prev.item;

        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        size -= 1;

        return t;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index < 0 || index > size() - 1) {
            return null;
        }

        TNode temp = sentinel;
        while (index >= 0) {
            index -= 1;
            temp = temp.next;
        }

        return temp.item;
    }

    /* Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index > size() - 1) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    /* Helper method for getRecursive. */
    public T getRecursiveHelper(TNode n, int index) {
        if (index == 0) {
            return n.item;
        }
        return getRecursiveHelper(n.next, index - 1);
    }

}
