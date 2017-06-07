public class indexOutOfBounds
{
	public static void main(String[] args)
	{
		int[] a;

		a = new int[10];
		
		a[0] = a[10];
	}
}