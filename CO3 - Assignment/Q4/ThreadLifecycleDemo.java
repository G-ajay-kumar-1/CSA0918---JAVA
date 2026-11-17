package q4;
public class ThreadLifecycleDemo {
 public static void main(String[] args) throws InterruptedException {
  Thread worker=new Thread(()->{try{System.out.println("Worker running");Thread.sleep(300);System.out.println("Worker finishing");}
   catch(InterruptedException e){Thread.currentThread().interrupt();}});
  System.out.println("Before start: "+worker.getState());
  worker.start();System.out.println("After start: "+worker.getState());
  worker.join();System.out.println("After join: "+worker.getState());
 }
}
