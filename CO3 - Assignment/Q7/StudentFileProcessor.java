package q7;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.IOException;
public class StudentFileProcessor {
 static Callable<String> task(Path p){return ()->{
  if(!Files.exists(p))throw new IOException("Missing file: "+p);
  List<String> lines=Files.readAllLines(p);
  if(lines.size()<2)throw new IOException("Invalid student file: "+p);
  double total=0;for(int i=1;i<lines.size();i++)total+=Double.parseDouble(lines.get(i).trim());
  return p.getFileName()+": average="+(total/(lines.size()-1));
 };}
 public static void main(String[] args)throws Exception{
  Path dir=Paths.get("student-files");Files.createDirectories(dir);
  List<Path> files=new ArrayList<>();
  for(int i=1;i<=5;i++){Path p=dir.resolve("student"+i+".txt");if(!Files.exists(p))Files.writeString(p,"Student"+i+"\n80\n90\n");files.add(p);}
  files.add(dir.resolve("missing.txt"));
  for(int poolSize:new int[]{1,2,4,8}){
   long start=System.nanoTime();ExecutorService pool=Executors.newFixedThreadPool(poolSize);
   List<Future<String>> futures=new ArrayList<>();for(Path p:files)futures.add(pool.submit(task(p)));
   int ok=0,failed=0;for(Future<String> f:futures)try{System.out.println(f.get());ok++;}catch(ExecutionException e){System.out.println("FAILED: "+e.getCause().getMessage());failed++;}
   pool.shutdown();pool.awaitTermination(1,TimeUnit.MINUTES);
   System.out.printf("Pool=%d success=%d failure=%d total=%d elapsed_ms=%.3f%n",poolSize,ok,failed,files.size(),(System.nanoTime()-start)/1e6);
  }
 }
}
