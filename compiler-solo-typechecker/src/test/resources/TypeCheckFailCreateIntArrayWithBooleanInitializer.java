public class TypeCheckFailCreateIntArrayWithBooleanInitializer {
    public static void main(String[] args) {
    	int[] I;
    	boolean b;
    	
    	b = false;
    	I = new int[]{ b };
    }
}