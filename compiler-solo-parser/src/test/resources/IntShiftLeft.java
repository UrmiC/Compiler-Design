public class IntShiftLeft {
    public static void main(String[] args) {
        StaticJavaLib.assertTrue( 2 << 1 ==  4);
        StaticJavaLib.assertTrue(-2 << 1 == -4);

        StaticJavaLib.assertTrue(shiftLeft( 2, 1) ==  4);
        StaticJavaLib.assertTrue(shiftLeft(-2, 1) == -4);
    }

    static int shiftLeft(int i, int j){
        return i << j;
    }
}    
