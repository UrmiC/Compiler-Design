public class IntEqual {
    public static void main(String[] args) {
        StaticJavaLib.assertTrue(2 == 3 == false);
        StaticJavaLib.assertTrue(3 == 2 == false);
        StaticJavaLib.assertTrue(2 == 2 == true);

        StaticJavaLib.assertTrue(equal(2, 3) == false);
        StaticJavaLib.assertTrue(equal(3, 2) == false);
        StaticJavaLib.assertTrue(equal(2, 2) == true);
    }
    
    static boolean equal(int i, int j) {
        return i == j;
    }
}    
    
