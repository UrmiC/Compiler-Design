public class MillionObjectsAndFieldInStaticMethod {
    public static void main(String[] args) {
    	MillionObjectsClass1[] classMillionObjectsClass1 ;
    	classMillionObjectsClass1 = new MillionObjectsClass1[]{new MillionObjectsClass1()};
    	process();
            }
    
    static void process(){
        int i;
        MillionObjectsClass2[] millionObjects ;
        millionObjects = new MillionObjectsClass2[1000000];
        
        for (i = 0; i < 1000000; i++) {
            millionObjects[i] = new MillionObjectsClass2();
            millionObjects[i].b = 0;
        }
    }
}

class MillionObjectsClass1{
    public int a;

}

class MillionObjectsClass2{
    public int b;
    
}