	public class BinaryOps {
	public static void main(String[] args) {
		StaticJavaLib.assertTrue(add(2, 3) == 5);
		StaticJavaLib.assertTrue(add(2, -3) == -1);
		StaticJavaLib.assertTrue(add(-2, 3) == 1);
		StaticJavaLib.assertTrue(add(-2, -3) == -5);

		StaticJavaLib.assertTrue(subtract(2, 3) == -1);
		StaticJavaLib.assertTrue(subtract(2, -3) == 5);
		StaticJavaLib.assertTrue(subtract(-2, 3) == -5);
		StaticJavaLib.assertTrue(subtract(-2, -3) == 1);

		StaticJavaLib.assertTrue(multiply(2, 3) == 6);
		StaticJavaLib.assertTrue(multiply(2, -3) == -6);
		StaticJavaLib.assertTrue(multiply(-2, 3) == -6);
		StaticJavaLib.assertTrue(multiply(-2, -3) == 6);

		StaticJavaLib.assertTrue(divide(2, 3) == 0);
		StaticJavaLib.assertTrue(divide(2, -3) == 0);
		StaticJavaLib.assertTrue(divide(-2, 3) == 0);
		StaticJavaLib.assertTrue(divide(-2, -3) == 0);

		StaticJavaLib.assertTrue(divide(3, 2) == 1);
		StaticJavaLib.assertTrue(divide(3, -2) == -1);
		StaticJavaLib.assertTrue(divide(-3, 2) == -1);
		StaticJavaLib.assertTrue(divide(-3, -2) == 1);

		StaticJavaLib.assertTrue(mod(2, 3) == 2);
		StaticJavaLib.assertTrue(mod(2, -3) == 2);
		StaticJavaLib.assertTrue(mod(-2, 3) == -2);
		StaticJavaLib.assertTrue(mod(-2, -3) == -2);

		StaticJavaLib.assertTrue(mod(3, 2) == 1);
		StaticJavaLib.assertTrue(mod(3, -2) == 1);
		StaticJavaLib.assertTrue(mod(-3, 2) == -1);
		StaticJavaLib.assertTrue(mod(-3, -2) == -1);

		StaticJavaLib.assertTrue(true);
		StaticJavaLib.assertTrue(!false);
		
		StaticJavaLib.assertTrue(greaterThan(2, 3) == false);
		StaticJavaLib.assertTrue(greaterThan(3, 2) == true);
		StaticJavaLib.assertTrue(greaterThan(2, 2) == false);
		
		StaticJavaLib.assertTrue(greaterThanOrEqual(2, 3) == false);
		StaticJavaLib.assertTrue(greaterThanOrEqual(3, 2) == true);
		StaticJavaLib.assertTrue(greaterThanOrEqual(2, 2) == true);
		
		StaticJavaLib.assertTrue(equal(2, 3) == false);
		StaticJavaLib.assertTrue(equal(3, 2) == false);
		StaticJavaLib.assertTrue(equal(2, 2) == true);
		
		StaticJavaLib.assertTrue(lessThan(2, 3) == true);
		StaticJavaLib.assertTrue(lessThan(3, 2) == false);
		StaticJavaLib.assertTrue(lessThan(2, 2) == false);
		
		StaticJavaLib.assertTrue(lessThanOrEqual(2, 3) == true);
		StaticJavaLib.assertTrue(lessThanOrEqual(3, 2) == false);
		StaticJavaLib.assertTrue(lessThanOrEqual(2, 2) == true);
		
		StaticJavaLib.assertTrue(notEqual(2, 3) == true);
		StaticJavaLib.assertTrue(notEqual(3, 2) == true);
		StaticJavaLib.assertTrue(notEqual(2, 2) == false);
		
		StaticJavaLib.assertTrue(boolAnd(true, true) == true);
		StaticJavaLib.assertTrue(boolAnd(true, false) == false);
		StaticJavaLib.assertTrue(boolAnd(false, true) == false);
		StaticJavaLib.assertTrue(boolAnd(false, false) == false);
		
		StaticJavaLib.assertTrue(boolOr(true, true) == true);
		StaticJavaLib.assertTrue(boolOr(true, false) == true);
		StaticJavaLib.assertTrue(boolOr(false, true) == true);
		StaticJavaLib.assertTrue(boolOr(false, false) == false);
		
		StaticJavaLib.assertTrue(shiftLeft(2, 1) == 4);
		StaticJavaLib.assertTrue(shiftLeft(-2, 1) == -4);
		
		StaticJavaLib.assertTrue(shiftRight(2, 1) == 1);
		StaticJavaLib.assertTrue(shiftRight(-2, 1) == -1);
		
		StaticJavaLib.assertTrue(unsignedShiftRight(2, 1) == 1);
		StaticJavaLib.assertTrue(unsignedShiftRight(-2, 1) == 2147483647);
	}

	static int add(int a, int b) {
		return a + b;
	}

	static int subtract(int a, int b) {
		return a - b;
	}

	static int multiply(int a, int b) {
		return a * b;
	}

	static int divide(int a, int b) {
		return a / b;
	}

	static int mod(int a, int b) {
		return a % b;
	}

	static boolean greaterThan(int a, int b) {
		return a > b;
	}

	static boolean greaterThanOrEqual(int a, int b) {
		return a >= b;
	}

	static boolean equal(int a, int b) {
		return a == b;
	}

	static boolean lessThan(int a, int b) {
		return a < b;
	}

	static boolean lessThanOrEqual(int a, int b) {
		return a <= b;
	}

	static boolean notEqual(int a, int b) {
		return a != b;
	}

	static boolean boolAnd(boolean a, boolean b) {
		return a && b;
	}

	static boolean boolOr(boolean a, boolean b) {
		return a || b;
	}
	
	static int shiftLeft(int i, int j){
		return i << j;
	}
	
	static int shiftRight(int i, int j){
		return i >> j;
	}
	
	static int unsignedShiftRight(int i, int j){
		return i >>> j;
	}
}
