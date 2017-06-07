public class assignObjToRef
{
	public static void main(String[] args)
	{
		Foo8[] a;
		Foo8 b;
		
		b = new Foo8();
		a = new Foo8[]{b};
	}
}

class Foo8{

}