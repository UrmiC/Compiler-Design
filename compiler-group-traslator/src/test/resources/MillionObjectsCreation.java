public class MillionObjectsCreation {
	public static void main(String[] args) {
		int i;
		
		Millionobjectclass[] objects;
		objects = new Millionobjectclass[1000000];
		i=0;
		
		for (i = 0; i < 1000000; i++) {
        
			objects[i] = new Millionobjectclass();
		}
	}
}

class Millionobjectclass {
	
}


