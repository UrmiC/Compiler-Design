public class ForMissingInit {
    public static void main(String[] args) {
        missingInit();
    }
    
    static void missingInit() {
        int i;
        
        i = 0;
        for (; i < 10; i++) {
        }
        StaticJavaLib.assertTrue(i == 10);
    }
}
