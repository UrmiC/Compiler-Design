public class ForMultipleInitAndIncOrDec {
    public static void main(String[] args) {
        multipleInitAndIncOrDec();
    }
    
    static void multipleInitAndIncOrDec() {
        int i;
        int j;
        int k;
        
        for (i = 0, j = 0, k = 0; i < 10; i++, j++, k++) {
        }
        StaticJavaLib.assertTrue(i == 10);
        StaticJavaLib.assertTrue(j == 10);
        StaticJavaLib.assertTrue(k == 10);
    }
}
