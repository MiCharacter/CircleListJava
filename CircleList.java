import java.util.Iterator;
public class CircleList<T extends Comparable<T>> {
    private static class Link<T> {
        T info;
        Link<T> next;

        Link(T info, Link<T> next) {
            this.info = info;
            this.next = next;
        }
    }

    public Iterator<T> iterator() {
        return new CircleListIterator<T>(head);
    }

    private class CircleListIterator<T> implements Iterator<T>{
        Link<T> current;
        CircleListIterator(Link<T> head){
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current==null){
                throw new IllegalStateException("No elements");
            }
            T result = current.info;
            current = current.next;
            return result;
        }
    }

    private int size = 0;
    private Link<T> head = null;
    private Link<T> tail = null;

    public void addHead(T s) {
        size++;
        if(head == null){ /* Если нет головы */
            if(tail==null){ /* и нет хвоста */
                Link<T> newLink = new Link<>(s,null);
                head = newLink;
            }
            else{ /* или есть только хвост */
                Link<T> newLink = new Link<>(s,tail);
                head = newLink;
                tail.next = head;
            }
        }
        else { /* Есть голова */
            Link<T> newLink = new Link<>(s,head);
            if(tail!=null) {
                tail.next = newLink;
                head = newLink;
            }
            else { /* Есть только голова */
                tail = head;
                head = newLink;
                tail.next = head;
                head.next = tail;
            }
        }
    }

    public void addTail(T s) {
        size++;
        if(tail == null){ /* Если нет хвоста */
            Link<T> newLink = new Link<>(s,null);
            if(head==null){ /* и нет головы */
                tail = newLink;
            }
            else{ /* или есть только голова */
                tail = newLink;
                tail.next=head;
                head.next = tail;
            }
        }
        else {  /* Есть хвост */
            if(head!=null) { /* Есть и хвост и голова */
                Link<T> newLink = new Link<>(s,head);
                tail.next = newLink;
                tail = newLink;
            }
            else { /* Если уже есть 1 хвост но нет головы */
                Link<T> newLink = new Link<>(s,tail);
                tail.next = newLink;
                head = tail;
                tail = newLink;
                tail.next = head;
            }
        }
    }

    public T removeHead() {
        if(head == null) return null;
        else{
            size--;
            tail.next = head.next;
            T buffinfo = head.info; /* Буферное значение */
            head = tail.next;
            return buffinfo;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }

    public boolean contains(T elem) {
        if(elem.compareTo(head.info) == 0){
            return true;
        }
        else {
            Link<T> current = head.next;
            while (current != head && elem.compareTo(current.info) > 0) {
                current = current.next;
            }
            if(elem.compareTo(tail.info) == 0){
                return true;
            }
            else {
                return current != head && elem.equals(current.info);
            }
        }

    }

    public T get ( int i){
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds [0, " + (size - 1) + "]");
        }
        Link<T> current = head;
        while (i-- > 0) current = current.next;
        return current.info;
    }

    public T set ( int i, T elem){
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds [0, " + (size - 1) + "]");
        }
        Link<T> current = head;
        while (i-- > 0) current = current.next;
        T buffinfo = current.info;
        current.info = elem;
        return buffinfo;
    }


}


