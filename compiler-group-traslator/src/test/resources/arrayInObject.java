public class arrayInObject
{
	public static void main(String[] args)
	{
		Foo13 b;

		b = new Foo13();
		
		b.a = new int[10];
		
		b.a[0] = 0;
		
		StaticJavaLib.assertTrue(b.a[0] == 0);
	}
}

class Foo13{
	public int[] a;
}
