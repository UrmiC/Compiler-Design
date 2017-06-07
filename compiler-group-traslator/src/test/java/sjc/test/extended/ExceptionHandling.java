package sjc.test.extended;

import java.io.File;
import java.io.PrintStream;
import java.lang.ProcessBuilder.Redirect;
/*
public class ExceptionHandling{
	public static void main(String[] args){
		try{
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "gcc c:\\workspace\\test.c");
			pb.directory(new File("c:\\workspace"));
			File log = new File("log.txt");
			pb.redirectErrorStream(true);
			pb.redirectOutput(Redirect.appendTo(log));
			Process p = pb.start();
			//p.sta
			ProcessBuilder pb1 = new ProcessBuilder("cmd.exe", "c:\\workspace\\a.exe");
			pb1.directory(new File("c:\\workspace"));

			pb1.redirectErrorStream(true);
			pb1.redirectOutput(Redirect.appendTo(log));
			Process p1 = pb1.start();
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
}*/
public class ExceptionHandling {

	   public static void main(String[] args) {
		   Process process = null;
		   int x = -99;
	   try {

	   // print a message
	   System.out.println("Executing a.exe");

	   // create a process and execute notepad.exe
	   process = Runtime.getRuntime().exec("c:\\workspace\\a.exe");
	   
	   // print another message
	   System.out.println("a should now open.");
	   process.waitFor();
	   
	   } catch (Exception ex) {
	   ex.printStackTrace();
	   }
	   if (process != null){
		   
		   System.out.println("exit val: " + process.exitValue() + "\n");
		   PrintStream printStream = new PrintStream(process.getOutputStream());
		   printStream.println();
		   printStream.flush();
		   printStream.close();
		   System.out.println("done");
	   }
	   }
	}