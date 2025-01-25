import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int N;
    // construct an empty randomized queue
    public RandomizedQueue(){
        q = (Item[])new Object[1];
        N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }
    private void resize(int capacity){
        Item[] copy = (Item[])new Object[capacity];
        for (int i = 0; i< N; ++i){
            copy[i] = q[i];
        }
        q = copy;
    }
    // add the item
    public void enqueue(Item item){
        if(item == null) throw new IllegalArgumentException();
        if(N == q.length) resize(2 * q.length);
        q[N++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException();
        int randIndex = StdRandom.uniformInt(N);
        Item item = q[randIndex];
        q[randIndex] = q[N - 1];
        q[--N] = null;
        if(N > 0 && N == q.length / 4) resize(q.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()) throw new NoSuchElementException();
        int randIndex = StdRandom.uniformInt(N);
        return q[randIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new Iterator<Item>() {
            private final Item[] shuffled;
            private int current = 0;
            {
                shuffled = (Item[]) new Object[N];
                for (int i = 0; i < N; ++i){
                    shuffled[i] = q[i];
                }
                shuffle(shuffled);
            }

            private void shuffle(Item[] array) {
                for (int i = array.length - 1; i > 0; i--) {
                    int j = StdRandom.uniformInt(i + 1); // select index randomly
                    Item temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            public boolean hasNext() {
                return current < shuffled.length;
            }
            public Item next() {
                if(!hasNext()) throw new NoSuchElementException();
                return shuffled[current ++];
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args){
//        RandomizedQueue<String> rq = new RandomizedQueue<>();
//        rq.enqueue("A");
//        rq.enqueue("B");
//        rq.enqueue("C");
//        rq.enqueue("D");
//
//        System.out.println("Sample: " + rq.sample());
//
//        for (String s : rq) {
//            System.out.println("Iterate: " + s);
//        }
//
//        System.out.println("Dequeue: " + rq.dequeue());
//        System.out.println("Dequeue: " + rq.dequeue());
//
//        for (String s : rq) {
//            System.out.println("Iterate: " + s);
//        }
//
//        System.out.println("Size: " + rq.size());

    }

}
