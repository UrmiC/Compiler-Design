
public class ForwardClassTest
{
    public static void main(String[] args)
    {
        Foo a;
        
        a = new Foo();
        a.b = new Bar();
        a.b.x = 5;
    }
}

class Foo
{
    public Bar b;
}

class Bar
{
    public int x;
}
