public class ForBooleanMemberAccessConditional {
	public static void main(String[] args) {
		FBMAC o;
		boolean b;
		
		b= false;
		o = new FBMAC();
		o.b = false;
		for (; o.b;) {
			b = true;
		}
		StaticJavaLib.assertTrue(b == false);
	}
}

class FBMAC {
	public boolean b;
}