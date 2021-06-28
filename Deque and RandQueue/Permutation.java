import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation
{
    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            rq.enqueue(item);
        }

        Iterator<String> iterator = rq.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(iterator.next());
        }
    }
}
