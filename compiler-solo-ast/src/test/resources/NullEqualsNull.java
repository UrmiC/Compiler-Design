public class NullEqualsNull {
    public static void main(String[] args) {
    	
    	StaticJavaLib.assertTrue(null == returnNull());
    	StaticJavaLib.assertTrue(returnNull() == null);
    	StaticJavaLib.assertTrue(null == null);
    	StaticJavaLib.assertTrue(!(null != returnNull()));
    	StaticJavaLib.assertTrue(!(returnNull() != null));
    	StaticJavaLib.assertTrue(!(null != null));
    	
    	StaticJavaLib.assertTrue(null == returnNull());
    }
    
    static NEN returnNull() {
    	return null;
    }
}

class NEN {
	
}
