package q5;
import java.util.*;
record Team(String name,int points,int goalDifference) {}
class Leaderboard<T> {
 private final List<T> items=new ArrayList<>();
 void add(T item){items.add(item);}
 void sort(Comparator<? super T> comparator){items.sort(comparator);}
 List<T> getItems(){return List.copyOf(items);}
}
public class LeaderboardDemo {
 public static void main(String[] args){
  Leaderboard<Team> board=new Leaderboard<>();
  board.add(new Team("Panthers",9,4)); board.add(new Team("Eagles",9,6));
  board.add(new Team("Cobras",6,1)); board.add(new Team("Bisons",9,4));
  board.sort(Comparator.comparingInt(Team::points).reversed()
    .thenComparing(Comparator.comparingInt(Team::goalDifference).reversed())
    .thenComparing(Team::name));
  board.getItems().forEach(System.out::println);
 }
}
