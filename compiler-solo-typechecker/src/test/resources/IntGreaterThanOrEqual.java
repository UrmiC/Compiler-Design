public class IntGreaterThanOrEqual {
    public static void main(String[] args) {
        StaticJavaLib.assertTrue(2 >= 3 == false);
        StaticJavaLib.assertTrue(3 >= 2 == true);
        StaticJavaLib.assertTrue(2 >= 2 == true);

        StaticJavaLib.assertTrue(greaterThanOrEqual(2, 3) == false);
        StaticJavaLib.assertTrue(greaterThanOrEqual(3, 2) == true);
        StaticJavaLib.assertTrue(greaterThanOrEqual(2, 2) == true);
    }   
    
    static boolean greaterThanOrEqual(int i, int j) {
        return i >= j;
    }
}
    
