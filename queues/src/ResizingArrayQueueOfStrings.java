public class ResizingArrayQueueOfStrings {
    private String[] q =  new String[1];
    private int head = 0, tail = 0, N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(String item){
        if(N == q.length) resize(q.length * 2);
        q[tail] = item;
        tail = (tail + 1) % q.length;
        N++;
    }
    private void resize(int capacity){
        String[] copy = new String[capacity];
        for (int i = 0;i < N ;i++){
            copy[i] = q[(head + i) % q.length];
        }
        q = copy;
        head = 0;
        tail = N;
    }
    public String dequeue(){
        if(isEmpty()) throw new IllegalArgumentException();
        String item = q[head];
        q[head] = null;
        head = (head + 1) % q.length;
        N--;
        if(N > 0 && N == q.length / 4) resize(q.length / 2);
        return item;
    }


}
