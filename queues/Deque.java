import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int N;
    private class Node{
        Item item;
        Node prev;
        Node next;
    }
    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the deque
    public int size(){
        return N;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if(oldfirst != null) oldfirst.prev = first;
        if (last == null) last = first;
        N++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null) throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty()) first = last;
        else {
            oldlast.next = last;
        }
        N++;

    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        Node newfirst = first.next;
        first.next = null;
        first = newfirst;
        if(newfirst != null) newfirst.prev = null;
        else {
            last = null;
        }
        N--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        Node newlast = last.prev;
        last.prev = null;
        if(newlast!= null) newlast.next = null;
        else{
            first = null;
        }
        last = newlast;
        N--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new Iterator<Item>() {
            private Node current = first;
            public boolean hasNext() {
                return current != null;
            }
            public void remove(){
                throw new UnsupportedOperationException();
            }
            public Item next() {
                if(current == null) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<String> deq = new Deque<>();
        deq.addFirst("to");
        deq.addFirst("be");
        deq.addLast("not");
        deq.addLast("that");
        deq.addLast("is");
        for(String s: deq){
            StdOut.println(s);
        }
        deq.removeFirst();
        deq.removeLast();
        for(String s: deq){
            StdOut.println(s);
        }
    }

}
