public class BooleanOr {
    public static void main(String[] args) {
        StaticJavaLib.assertTrue((true  || true)  == true);
        StaticJavaLib.assertTrue((true  || false) == true);
        StaticJavaLib.assertTrue((false || true)  == true);
        StaticJavaLib.assertTrue((false || false) == false);
 
        StaticJavaLib.assertTrue(or(true,  true)  == true);
        StaticJavaLib.assertTrue(or(true,  false) == true);
        StaticJavaLib.assertTrue(or(false, true)  == true);
        StaticJavaLib.assertTrue(or(false, false) == false);
    }

    static boolean or(boolean b, boolean c) {
        return b || c;
    }
}
    
