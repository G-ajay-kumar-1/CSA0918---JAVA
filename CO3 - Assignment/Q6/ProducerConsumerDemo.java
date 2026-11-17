package q6;
import java.util.*;
public class ProducerConsumerDemo {
 static class Buffer {
  private final Queue<Integer> q=new ArrayDeque<>(); private final int capacity=5; private boolean done=false;
  synchronized void put(int value)throws InterruptedException{while(q.size()==capacity)wait();q.add(value);notifyAll();}
  synchronized Integer take()throws InterruptedException{while(q.isEmpty()&&!done)wait();if(q.isEmpty())return null;int v=q.remove();notifyAll();return v;}
  synchronized void finish(){done=true;notifyAll();}
 }
 public static void main(String[] args)throws InterruptedException{
  Buffer b=new Buffer();
  Thread producer=new Thread(()->{try{for(int i=1;i<=20;i++)b.put(i);b.finish();}catch(InterruptedException e){Thread.currentThread().interrupt();}});
  producer.start();int sum=0,count=0;Integer v;
  while((v=b.take())!=null){sum+=v;count++;}
  producer.join();System.out.println("Items consumed="+count+", sum="+sum+" (expected sum=210)");
 }
}
