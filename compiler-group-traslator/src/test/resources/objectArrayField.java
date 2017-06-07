public class objectArrayField
{
	public static void main(String[] args)
	{
		Foo7 b;

		b = new Foo7();
		
		b.a = new int[10];
		
		b.a[0] = 5;
		
		StaticJavaLib.assertTrue(b.a[0] == 5);
	}
}

class Foo7{
	public int[] a;
}