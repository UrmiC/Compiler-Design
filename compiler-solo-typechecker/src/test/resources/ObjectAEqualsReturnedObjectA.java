class Object64 {
	
}

public class ObjectAEqualsReturnedObjectA {
    public static void main(String[] args) {
    	Object64 o;
    	
    	o = new Object64();
    	StaticJavaLib.assertTrue(o == returnObject(o));
    }
    
    static Object64 returnObject(Object64 o){
    	return o;
    }
}