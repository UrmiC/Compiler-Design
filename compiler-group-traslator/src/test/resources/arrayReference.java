public class arrayReference
{
	public static void main(String[] args)
	{
		int[] a;
		int[] b;
		int[] c;
		
		a = new int[] {5,4,3,2,1,0};
		b = new int[] {1,2,3};
		
		c = a;
		c = b;
		StaticJavaLib.assertTrue(c[0] == 1);
	}
}
