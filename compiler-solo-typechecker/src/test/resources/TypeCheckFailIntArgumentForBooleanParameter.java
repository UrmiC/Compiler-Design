public class TypeCheckFailIntArgumentForBooleanParameter {
    public static void main(String[] args) {
    	int i;
    	boolean b;
    	
    	i = 0;
    	b = takeBoolean (i);
    }
    
    static boolean takeBoolean (boolean b){
    	return b;
    }
}