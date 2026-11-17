package qB1;
import java.io.*;
import java.nio.file.*;
import java.util.*;
public class BusPassApp {
 record Pass(String id,String reg,String name,int route,String stop,String valid,double paid) {}
 static Path file=Paths.get("buspasses.csv");
 static String csv(String s){return "\"" + s.replace("\"", "\"\"") + "\"";}
 static void add(Pass p)throws IOException{
  String row=String.join(",",csv(p.id),csv(p.reg),csv(p.name),Integer.toString(p.route),csv(p.stop),csv(p.valid),Double.toString(p.paid));
  Files.writeString(file,row+System.lineSeparator(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
 }
 static List<String> parse(String line){
  List<String> out=new ArrayList<>();StringBuilder b=new StringBuilder();boolean quote=false;
  for(int i=0;i<line.length();i++){char c=line.charAt(i);
   if(c=='"'){if(quote&&i+1<line.length()&&line.charAt(i+1)=='"'){b.append('"');i++;}else quote=!quote;}
   else if(c==','&&!quote){out.add(b.toString());b.setLength(0);}else b.append(c);
  }out.add(b.toString());return out;
 }
 static void listByRoute(int route)throws IOException{
  if(!Files.exists(file)){System.out.println("No pass file yet.");return;}
  int lineNo=0;for(String line:Files.readAllLines(file)){lineNo++;try{List<String> f=parse(line);if(f.size()!=7)throw new IllegalArgumentException("wrong field count");if(Integer.parseInt(f.get(3))==route)System.out.println(f);}catch(Exception e){System.err.println("Skipping damaged line "+lineNo+": "+e.getMessage());}}
 }
 public static void main(String[] args)throws Exception{
  if(!Files.exists(file)){add(new Pass("P1001","192372300","Ajay, Kumar",12,"Main Gate","2027-03-31",4500));add(new Pass("P1002","192372301","Meena",15,"Library","2027-03-31",4500));}
  System.out.println("Pass records for route 12:");listByRoute(12);
 }
}
