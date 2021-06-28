import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int cnt;

    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }

    public Deque()
    {
        first = null;
        last = null;
        cnt = 0;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public int size()
    {
        return cnt;
    }

    public void addFirst(Item item)
    {
        if(item == null)
            throw new IllegalArgumentException("Illegal Argument");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (oldFirst == null)
            last = first;
        else
            oldFirst.previous = first;
        cnt++;
    }

    public void addLast(Item item)
    {
        if(item == null)
            throw new IllegalArgumentException("Illegal Argument");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(oldLast == null)
            first = last;
        else
            oldLast.next = last;
    }

    public Item removeFirst()
    {
        if(isEmpty())
            throw new NoSuchElementException("There is no element in DS");
        Item item = first.item;
        first = first.next;
        if(first == null)
            last = null;
        else
            first.previous = null;
        cnt--;
        return item;
    }

    public Item removeLast()
    {
        if(isEmpty())
            throw new NoSuchElementException("There is no element in DS");
        Item item = last.item;
        last = last.previous;
        if(last == null)
            first = null;
        else
            last.next = null;
        cnt--;
        return item;
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node cur = first;
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            if(!hasNext())
                throw new NoSuchElementException();
            Item item = cur.item;
            cur = cur.next;
            return item;
        }
    }


    @Override
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    public static void main(String[] args)
    {
        Deque<Character> deque = new Deque<>();
        deque.addFirst('r');    // null ->  r  -> null
        deque.addFirst('o');    // null -> o ->  r  -> null
        deque.addFirst('w');    // null -> w -> o ->  r  -> null
        deque.addLast('l');     // null -> w -> o ->  r  -> l -> null
        deque.addLast('d');     // null -> w -> o ->  r  -> l -> d -> null

        Iterator iterator = deque.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next());    // world

        System.out.println();
        System.out.println(deque.removeFirst());    // returns w
        System.out.println(deque.removeLast());     // returns d

    }
}
