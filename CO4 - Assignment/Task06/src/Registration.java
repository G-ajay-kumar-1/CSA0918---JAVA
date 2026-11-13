public class Registration {
    public final int id;
    public final String studentId, studentName, eventName, registeredAt;
    public Registration(int id, String studentId, String studentName, String eventName, String registeredAt) {
        this.id=id; this.studentId=studentId; this.studentName=studentName;
        this.eventName=eventName; this.registeredAt=registeredAt;
    }
}
