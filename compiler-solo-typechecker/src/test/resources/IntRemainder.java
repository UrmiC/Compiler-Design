public class IntRemainder {
    public static void main(String[] args) {
        StaticJavaLib.assertTrue( 2 %  3 ==  2);
        StaticJavaLib.assertTrue( 2 % -3 ==  2);
        StaticJavaLib.assertTrue(-2 %  3 == -2);
        StaticJavaLib.assertTrue(-2 % -3 == -2);
        StaticJavaLib.assertTrue( 3 %  2 ==  1);
        StaticJavaLib.assertTrue( 3 % -2 ==  1);
        StaticJavaLib.assertTrue(-3 %  2 == -1);
        StaticJavaLib.assertTrue(-3 % -2 == -1);

        StaticJavaLib.assertTrue(remainder( 2,  3) ==  2);
        StaticJavaLib.assertTrue(remainder( 2, -3) ==  2);
        StaticJavaLib.assertTrue(remainder(-2,  3) == -2);
        StaticJavaLib.assertTrue(remainder(-2, -3) == -2);
        StaticJavaLib.assertTrue(remainder( 3,  2) ==  1);
        StaticJavaLib.assertTrue(remainder( 3, -2) ==  1);
        StaticJavaLib.assertTrue(remainder(-3,  2) == -1);
        StaticJavaLib.assertTrue(remainder(-3, -2) == -1);
    }

    static int remainder(int i, int j) {
        return i % j;
    }
}

