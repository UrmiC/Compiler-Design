public class Parens {
    public static void main(String[] args) 
    {
        int i;
        int j;
        int[] a;

        i = (2);
        i = 2;
        j = (i);

        i = (i + 0);
        StaticJavaLib.assertTrue(i == 2);

        i = (i * (i + 0));
        StaticJavaLib.assertTrue(i == 4);

        i = (((i) / (2)) - (0));
        StaticJavaLib.assertTrue(i == 2);
            
        a = new int[4];
        
    }
}
