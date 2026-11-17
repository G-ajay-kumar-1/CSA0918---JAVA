package q6;
import java.util.*;
record Patient(String name,int severity,long arrival) {}
public class HospitalTriage {
 public static void main(String[] args){
  PriorityQueue<Patient> queue=new PriorityQueue<>(
   Comparator.comparingInt(Patient::severity).thenComparingLong(Patient::arrival));
  String[] names={"Ravi","Meena","Asha","Kumar","Lata","John"};
  int[] severity={3,1,5,1,2,3};
  for(int i=0;i<names.length;i++) queue.add(new Patient(names[i],severity[i],i));
  while(!queue.isEmpty()){Patient p=queue.poll();System.out.printf("%s (severity %d)%n",p.name(),p.severity());}
  System.out.println("Lower severity number is treated first; arrival order breaks ties.");
 }
}
