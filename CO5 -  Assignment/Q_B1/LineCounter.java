package qB1;
import java.io.*;
import java.nio.file.*;
public class LineCounter {
 static long countUnbuffered(Path p)throws IOException{long lines=0;try(InputStream in=Files.newInputStream(p)){int b;while((b=in.read())!=-1)if(b=='\n')lines++;}return lines;}
 static long countBuffered(Path p)throws IOException{long lines=0;byte[] buffer=new byte[8192];try(InputStream in=new BufferedInputStream(Files.newInputStream(p))){int n;while((n=in.read(buffer))!=-1)for(int i=0;i<n;i++)if(buffer[i]=='\n')lines++;}return lines;}
 public static void main(String[] args)throws Exception{
  Path p=Paths.get(args.length>0?args[0]:"attendance.log");
  if(!Files.exists(p)){try(BufferedWriter w=Files.newBufferedWriter(p)){for(int i=0;i<100000;i++)w.write("attendance row "+i+"\n");}}
  long t=System.nanoTime(),a=countUnbuffered(p);long un=System.nanoTime()-t;
  t=System.nanoTime();long b=countBuffered(p);long bu=System.nanoTime()-t;
  System.out.printf("bytes=%d lines=%d unbuffered_ms=%.3f buffered_ms=%.3f%n",Files.size(p),b,un/1e6,bu/1e6);
  if(a!=b)throw new IllegalStateException("Line counts differ");
 }
}
