package q2;
class ExamSubmissionException extends Exception { ExamSubmissionException(String m,Throwable cause){super(m,cause);} }
class ExamService {
 void submit(String registered,boolean windowOpen,long sizeMb) throws ExamSubmissionException {
  try {
   if(!windowOpen) throw new IllegalStateException("Exam window is closed");
   if(!"YES".equals(registered)) throw new IllegalArgumentException("Student is not registered");
   if(sizeMb>20) throw new IllegalArgumentException("Answer file exceeds 20 MB");
   System.out.println("Submission accepted.");
  } catch(RuntimeException e){throw new ExamSubmissionException("Submission rejected by examination service",e);}
 }
}
public class ExamPortalDemo {
 public static void main(String[] args){
  try{new ExamService().submit("YES",true,25);}
  catch(ExamSubmissionException e){System.out.println(e.getMessage());System.out.println("Root cause: "+e.getCause());}
 }
}
