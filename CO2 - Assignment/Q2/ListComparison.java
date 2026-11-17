package q2;
import java.util.*;
public class ListComparison {
 public static void main(String[] args) {
  int n=200000; List<Integer> a=new ArrayList<>(), l=new LinkedList<>(); Deque<Integer> d=new ArrayDeque<>();
  long t=System.nanoTime(); for(int i=0;i<n;i++) a.add(i); long arrayAdd=System.nanoTime()-t;
  t=System.nanoTime(); for(int i=0;i<n;i++) l.add(i); long linkedAdd=System.nanoTime()-t;
  t=System.nanoTime(); for(int i=0;i<n;i++) d.addLast(i); long dequeAdd=System.nanoTime()-t;
  t=System.nanoTime(); long sum=0; for(int i=0;i<n;i+=100) sum+=a.get(i); long arrayGet=System.nanoTime()-t;
  t=System.nanoTime(); sum=0; for(int i=0;i<n;i+=100) sum+=l.get(i); long linkedGet=System.nanoTime()-t;
  System.out.printf("n=%d ArrayList add=%d ns, LinkedList add=%d ns, ArrayDeque addLast=%d ns%n",n,arrayAdd,linkedAdd,dequeAdd);
  System.out.printf("sample indexed reads: ArrayList=%d ns, LinkedList=%d ns (sum=%d)%n",arrayGet,linkedGet,sum);
  System.out.println("ArrayList: O(1) indexed access; LinkedList: O(n) indexed access; ArrayDeque: amortised O(1) at ends.");
 }
}
