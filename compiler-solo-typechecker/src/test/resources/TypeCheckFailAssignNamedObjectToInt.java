class Object {
	
}

public class TypeCheckFailAssignNamedObjectToInt {
    public static void main(String[] args) {
    	int i;
    	Object o;
    	
    	o = new Object();
    	i = o;
    }
}