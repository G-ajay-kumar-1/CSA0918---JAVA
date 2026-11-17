package q7;
public class RedesignBenchmark {
 static String original(int n){String s="";for(int i=0;i<n;i++)s+="record-"+i+";";return s;}
 static String redesigned(int n){StringBuilder b=new StringBuilder(n*10);for(int i=0;i<n;i++)b.append("record-").append(i).append(';');return b.toString();}
 static long time(java.util.function.IntFunction<String> f,int n){long t=System.nanoTime();String result=f.apply(n);if(result.length()==0)throw new AssertionError();return System.nanoTime()-t;}
 public static void main(String[] args){
  int n=30000; for(int i=0;i<3;i++){time(x->original(x),100);time(x->redesigned(x),100);}
  long old=0,now=0;for(int i=0;i<5;i++){old+=time(x->original(x),n);now+=time(x->redesigned(x),n);}
  System.out.printf("Original average: %.3f ms%n",old/5.0/1e6);
  System.out.printf("StringBuilder average: %.3f ms%n",now/5.0/1e6);
  System.out.println("Compare same output and workload; record your own machine's measurements.");
 }
}
