public class TypeCheckFailTooFewMethodArguments {
    public static void main(String[] args) {
    	takeTwoArgs (true);
    }
    
    static void takeTwoArgs (boolean b, int i){
    }
}