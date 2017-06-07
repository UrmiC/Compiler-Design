public class TestMillionObjectsAndFieldAndChangeValue {
    public static void main(String[] args) {
        A4[] classA4 ;
        classA4 = new A4[]{new A4()};
        manipulate();
    }
    
    static void manipulate(){
        int i;
        B3[] millionObjects ;
        millionObjects = new B3[1000000];
        
        for (i = 0; i < 1000000; i++) {
            millionObjects[i] = new B3();
            millionObjects[i].c = 0;
        }
        
        changeValue(millionObjects);
    }
    
    static void changeValue(B3[] millionObjects){
        int i;
        for(i=0;i<1000000;i++){
            millionObjects[i].c = i;
        }
    }
}

class A4{
    public int d;
    
}

class B3{
    public int c;
    
}