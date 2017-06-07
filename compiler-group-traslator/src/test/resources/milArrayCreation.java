class Foo4{
	
}

public class milArrayCreation
{
	public static void main(String[] args)
	{
		Foo4[] a;
		Foo4 b;
		int i;
		
		b = null;
		a = new Foo4[1000000];
		
		
		for(i = 0; i < 1000000; i++){
			a[i] = b;
		}
	}
}

