import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] items;
    private int cnt;

    public RandomizedQueue()
    {
        items = (Item[]) new Object[1];
        cnt = 0;
    }

    public boolean isEmpty()
    {
        return items.length == 0;
    }

    public int size()
    {
        return cnt;
    }

    public void enqueue(Item item)
    {
        if(item == null)
            throw new IllegalArgumentException();
        if(cnt == items.length)
            resize(2 * items.length);
        items[cnt++] = item;
    }

    public Item dequeue()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        int randIndex = StdRandom.uniform(cnt);
        Item removedItem = items[randIndex];
        if (randIndex != cnt - 1)
            items[randIndex] = items[cnt - 1];
        items[--cnt] = null;

        if (cnt > 0 && cnt == items.length / 4)
            resize(items.length / 2);
        return removedItem;
    }

    public Item sample()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        int randIndex = StdRandom.uniform(cnt);
        while (items[randIndex] == null) {
            randIndex = StdRandom.uniform(cnt);
        }
        return items[randIndex];
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < cnt; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    @Override
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private Item[] arr;
        private int c = cnt;
        public RandomizedQueueIterator()
        {
            arr = (Item[]) new Object[c];
            for (int i = 0; i < c; i++) {
                arr[i] = items[i];
            }
            StdRandom.shuffle(arr);
        }

        @Override
        public boolean hasNext() {
            return c != 0;
        }

        @Override
        public Item next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return arr[--c];
        }
    }

    public static void main(String[] args)
    {
        RandomizedQueue<Character> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue('a');   //  [a];
        randomizedQueue.enqueue('b');   //  [a, b]
        randomizedQueue.enqueue('c');   //  [a,b,c, ]
        randomizedQueue.enqueue('d');   //  [a,b,c,d]

        System.out.println(randomizedQueue.dequeue()); // removes and prints a or b or c or d

        Iterator iterator1 = randomizedQueue.iterator();

        while(iterator1.hasNext()){
            System.out.print(iterator1.next() + " ");
        }
        System.out.println();
        Iterator iterator2 = randomizedQueue.iterator();

        while(iterator2.hasNext()){
            System.out.print(iterator2.next() + " ");
        }

        // Two iterators above work independently
        System.out.println();
        System.out.println(randomizedQueue.sample());   // gets and prints random element from queue
    }
}
