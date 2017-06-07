public class ReturnArrayFromStaticMethodIntArrayMethod {
    public static void main(String[] args) {
        int[] A1;
        A1=null;
        
        A1 = returnArray(A1);
    }
    
    static int[] returnArray(int[] A2) {
        A2 = new int[6];
        A2[0] = 1;
        return A2;
    }
}