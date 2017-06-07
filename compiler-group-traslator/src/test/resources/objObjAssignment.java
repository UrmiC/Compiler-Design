public class objObjAssignment
{
	public static void main(String[] args)
	{
		Foo12 a;
		Foo12 b;
		
		a = new Foo12();
		
		a.z = 5;
		b = a;
		
		StaticJavaLib.assertTrue(b.z == 5);
	}
}

class Foo12{
	public int z;
}