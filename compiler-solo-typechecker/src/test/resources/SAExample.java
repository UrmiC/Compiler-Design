
public class SAExample
{
    public static void main(String[] args)
    {
    }
    
    static void ae(int a, int b)
    {
        int x;
        int y;
        x = a + b;
        y = a * b;
        while (y > a + b)
        {
            a = a + 1;
            x = a + b;
        }
    }

    static void vbe(int a, int b)
    {
        int x;
        int y;
        if (a > b) {
            x = b - a;
            y = a - b;
        } else {
            y = b - a;
            x = a - b;
        }
    }
    
    static void lv()
    {
        int x;
        int y;
        int z;
        x = 2;
        y = 4;
        x = 1;
        if (y > x) {
          z = y;
        } else {
          z = y * y;  
        }
        x = z;
    }
}
