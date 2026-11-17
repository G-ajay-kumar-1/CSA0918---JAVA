package q4;
import java.util.*;
public class MapComparison {
 public static void main(String[] args){
  Map<Integer,String> hash=new HashMap<>(), tree=new TreeMap<>(), linked=new LinkedHashMap<>();
  int[] keys={30,10,20}; for(int k:keys){hash.put(k,"Student"+k);tree.put(k,"Student"+k);linked.put(k,"Student"+k);}
  System.out.println("HashMap (no order guarantee): "+hash);
  System.out.println("TreeMap (sorted keys): "+tree);
  System.out.println("LinkedHashMap (insertion order): "+linked);
  System.out.println("Expected average lookup: HashMap/LinkedHashMap O(1); TreeMap O(log n).");
 }
}
