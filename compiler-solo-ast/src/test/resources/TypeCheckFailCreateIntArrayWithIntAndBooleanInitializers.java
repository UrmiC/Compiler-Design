public class TypeCheckFailCreateIntArrayWithBooleanInitializer {
    public static void main(String[] args) {
    	int[] I;
    	int i;
    	boolean b;
    	
    	i = 0;
    	b = false;
    	I = new int[]{ i, b };
    }
}