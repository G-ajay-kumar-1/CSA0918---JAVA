package q3;
import java.io.*;
public class ResourceDemo {
 public static void main(String[] args){
  File file=new File("sample.txt");
  try(BufferedReader reader=new BufferedReader(new FileReader(file))){
   String line;while((line=reader.readLine())!=null)System.out.println(line);
  } catch(IOException e){System.err.println("Could not read "+file.getAbsolutePath());e.printStackTrace();}
  finally{System.out.println("Resource scope finished.");}
 }
}
