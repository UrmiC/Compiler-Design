class CWN {
	
}

public class ConditionalWithNull {
    public static void main(String[] args) {
        CWN o;
        
        o = new CWN();
        o = true ? null : o;
    }
}
