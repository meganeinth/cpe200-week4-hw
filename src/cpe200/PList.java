package cpe200;

public class PList {

    public PList() {
        head = tail = null;
    }

    public void pushToHead(Object i) {
        head = new PNode(i, head, null);
        if (tail == null)
            tail = head;
        else
            head.next.prev = head;
        size++;
    }

    public void pushToTail(Object i) {
        tail = new PNode(i, null, tail);
        if (head == null)
            head = tail;
        else
            tail.prev.next = tail;
        size++;
    }

    public Object popHead() {
        Object data = head.data;
        PNode tmp = head;

        if (head == tail)
            head = tail = null;
        else {
            head = head.next;
            head.prev = null;
            tmp.next = null;
        }

        size--;

        return data;
    }

    public Object popTail() {
        Object data = tail.data;
        PNode tmp = tail;

        if (tail == head)
            tail = head = null;
        else {
            tail = tail.prev;
            tail.next = null;
            tmp.prev = null;
        }

        size--;

        return data;
    }

    public boolean remove(Object data) {
        PNode tmp = head, tmp2;
        while (tmp != null) {
            if (tmp.data.equals(data)) {
                if (tmp.equals(head)) {
                    popHead();
                    return true;
                } else if (tmp.equals(tail)) {
                    popTail();
                    return true;
                } else {
                    tmp2 = tmp.prev;
                    tmp2.next = tmp.next;
                    tmp.next.prev = tmp2;
                    size--;
                    return true;
                }
            }
            tmp = tmp.next;
        }
        return false;
    }

    public Object elementAt(int index) {

        PNode tmp = head;
        if (index > size || index < 0) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }

        return tmp.data;
    }

    public boolean found(Object data) {
        PNode tmp = head;

        while (tmp != null) {
            if (tmp.data.equals(data)) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void printForward() {
        PNode tmp = head;

        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
        System.out.println();
    }

    public void printBackward() {
        PNode tmp = tail;

        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.prev;
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    private PNode head, tail;
    private int size = 0;
}