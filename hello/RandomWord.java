import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int cnt = 1;
        String s, prev = "";
        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / cnt)) {
                prev = s;
            }
            cnt++;
        }
        StdOut.println(prev);

    }
}
