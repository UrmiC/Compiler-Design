public class ArrayCreation2
{
	public static void main(String[] args)
	{
		int[] a;
		int b;
		int i;
		
		a = new int[5];
		b = 0;
		
		for(i = 0 ; i < 5 ; i++)
		{
			a[i] = i;
			b = b + a[i];
		}
		
		StaticJavaLib.assertTrue(b == 10);
				
		for(i = 0 ; i < 5 ; i++)
		{
			a[i]++;
		}
		
		i = 0;
		
		for(i = 0 ; i < 5 ; i++)
		{
			StaticJavaLib.assertTrue(a[i] == (i+1));
		}
	}
}