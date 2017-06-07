class Data
{
    public int i;
}

class LinkedNode
{
    public Data data;

    public LinkedNode next;
}

class LinkedList
{
    public LinkedNode head;

    public LinkedNode last;

    public int size;
}

public class Queue
{
    public static void main(String[] args)
    {
        LinkedList ll;
        Data d;
        int i;

        ll = llNewLinkedList();
        
        for (i = 0; i < 10; i++)
        {
            d = new Data();
            d.i = i + 1;
            llAddLast(ll, d);
        }

        while (!llIsEmpty(ll))
        {
            d = llRemoveFirst(ll);
            StaticJavaLib.println(d.i);
        }
    }

    static LinkedList llNewLinkedList()
    {
        LinkedList result;

        result = new LinkedList();
        result.head = new LinkedNode();
        result.last = result.head;

        return result;
    }

    static boolean llIsEmpty(LinkedList ll)
    {
        StaticJavaLib.assertTrue(ll != null);
        
        return ll.size == 0;
    }

    static void llAddLast(LinkedList ll, Data data)
    {
        StaticJavaLib.assertTrue(ll != null && data != null);
        
        ll.last.next = new LinkedNode();
        ll.last = ll.last.next;
        ll.last.data = data;
        ll.size++;
    }

    static Data llRemoveFirst(LinkedList ll)
    {
        Data result;

        StaticJavaLib.assertTrue(ll.size > 0);
        result = ll.head.next.data;
        ll.head = ll.head.next;
        ll.size--;

        return result;
    }
}
