public class arrayInObjectAssign
{
	public static void main(String[] args)
	{
		Bar1 b;
		int z;
		int[] y;
		
		
		y = new int[]{7,8,9,10};
		b = new Bar1();
		
		b.f = y;
	}
}

class Bar1{
	public int[] f;
}