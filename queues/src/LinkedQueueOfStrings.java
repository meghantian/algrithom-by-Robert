public class LinkedQueueOfStrings {
    private Node first = null, last = null;
    private class Node{
        String item;
        Node next;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public void enqueue(String item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
    }
    public String dequeue(){
        if(isEmpty()) throw new NullPointerException();
        String item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }

}
