public class WhileInit
{
    public static void main(String[] args)
    {
        int[] x;
        x = new int[1];
        x[0] = 1;
        while (x[0] == 1)
        {
            x[0] = 2;
        }
    }
}
