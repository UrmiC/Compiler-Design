public class IntLessThanOrEqual {
    public static void main(String[] args) {
        StaticJavaLib.assertTrue(2 <= 3 == true);
        StaticJavaLib.assertTrue(3 <= 2 == false);
        StaticJavaLib.assertTrue(2 <= 2 == true);

        StaticJavaLib.assertTrue(lessThanOrEqual(2, 3) == true);
        StaticJavaLib.assertTrue(lessThanOrEqual(3, 2) == false);
        StaticJavaLib.assertTrue(lessThanOrEqual(2, 2) == true);
    }

    static boolean lessThanOrEqual(int i, int j) {
        return i <= j;
    }
}
