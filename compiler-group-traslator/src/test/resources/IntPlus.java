public class IntPlus {
    public static void main(String[] args) {
        StaticJavaLib.assertTrue( +2 ==  2);
        StaticJavaLib.assertTrue(+-2 == -2);

        StaticJavaLib.assertTrue(plus( 2) ==  2);
        StaticJavaLib.assertTrue(plus(-2) == -2);
    }

    static int plus(int i) {
        return +i;
    }
}

