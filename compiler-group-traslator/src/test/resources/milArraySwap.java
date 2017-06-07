public class milArraySwap
{
	public static void main(String[] args)
	{
		Foo6[] a;
		Foo6 c;
		int i;
		int j;
		
		a = new Foo6[1000000];
		
		for(i = 0, j = 99999; i < 100; i++, j--){
			c = a[i];
			a[i] = a[j];
			a[j] = a[i];
		}
		
		
	}
}

class Foo6{
	public int z;
}