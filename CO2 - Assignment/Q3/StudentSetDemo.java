package q3;
import java.util.*;
class Student {
 private final int id; private final String name;
 Student(int id,String name){this.id=id;this.name=name;}
 public boolean equals(Object o){if(this==o)return true;if(!(o instanceof Student s))return false;return id==s.id;}
 public int hashCode(){return Integer.hashCode(id);}
 public String toString(){return id+" - "+name;}
}
public class StudentSetDemo {
 public static void main(String[] args){
  Set<Student> students=new HashSet<>();
  students.add(new Student(101,"Anu")); students.add(new Student(102,"Bala"));
  students.add(new Student(101,"Anu Duplicate"));
  System.out.println("Set size (duplicate ID ignored): "+students.size());
  System.out.println(students);
  System.out.println("Contract: if a.equals(b), a.hashCode() must equal b.hashCode().");
 }
}
