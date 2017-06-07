public class UnaryOps {
	public static void main(String[] args) {
		StaticJavaLib.assertTrue(unaryPlus(2) == 2);
		StaticJavaLib.assertTrue(unaryPlus(-2) == -2);

		StaticJavaLib.assertTrue(unaryMinus(2) == -2);
		StaticJavaLib.assertTrue(unaryMinus(-2) == 2);
		
		StaticJavaLib.assertTrue(true);
		StaticJavaLib.assertTrue(!false);
		
		StaticJavaLib.assertTrue(not(true) == false);
		StaticJavaLib.assertTrue(not(false) == true);
		
		StaticJavaLib.assertTrue(complement(-5) == 4);
		
	}

	static int unaryPlus(int i) {
		return +i;
	}

	static int unaryMinus(int i) {
		return -i;
	}

	static boolean not(boolean b) {
		return !b;
	}
	
	static int complement (int i){
		return ~i;
	}
}
