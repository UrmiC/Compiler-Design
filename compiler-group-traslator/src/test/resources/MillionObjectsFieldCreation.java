public class MillionObjectsFieldCreation {
	public static void main(String[] args) {
		int i;
		
		Millionobjectclass1[] objects;
		objects = new Millionobjectclass1[1000000];
		
		for (i = 0; i < 1000000; i++) {
        
			objects[i] = new Millionobjectclass1();
			objects[i].a = i;
		}
	}
}

class Millionobjectclass1 {
	public int a;
}
