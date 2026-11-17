package q1;
import java.util.*;
public class StringBenchmark {
  static String plus(int fields) { String s=""; for(int i=0;i<fields;i++) s += "FIELD"+i+"|"; return s; }
  static String builder(int fields) { StringBuilder b=new StringBuilder(); for(int i=0;i<fields;i++) b.append("FIELD").append(i).append('|'); return b.toString(); }
  static String buffer(int fields) { StringBuffer b=new StringBuffer(); for(int i=0;i<fields;i++) b.append("FIELD").append(i).append('|'); return b.toString(); }
  static long measure(java.util.function.IntFunction<String> f,int fields,int rounds) {
    long start=System.nanoTime(); for(int i=0;i<rounds;i++) f.apply(fields); return (System.nanoTime()-start)/rounds;
  }
  public static void main(String[] args) {
    int[][] workloads={{6,1},{50,10000},{200,5000}};
    System.out.println("fields,tickets,plus_ns,builder_ns,buffer_ns");
    for(int[] w:workloads) {
      for(int warm=0;warm<2;warm++){ plus(w[0]); builder(w[0]); buffer(w[0]); }
      long a=measure(n->plus(n),w[0],w[1]), b=measure(n->builder(n),w[0],w[1]), c=measure(n->buffer(n),w[0],w[1]);
      System.out.printf("%d,%d,%d,%d,%d%n",w[0],w[1],a,b,c);
    }
    System.out.println("Decision: StringBuilder for local ticket construction; StringBuffer only when its synchronized methods are specifically needed.");
  }
}
