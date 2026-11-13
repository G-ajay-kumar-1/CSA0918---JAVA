import java.sql.*;
import java.util.*;
public class RegistrationDAO {
    public void insert(String studentId, String studentName, String eventName) throws SQLException {
        String sql = "INSERT INTO registrations(student_id, student_name, event_name) VALUES(?,?,?)";
        try (Connection c=DB.getConnection(); PreparedStatement p=c.prepareStatement(sql)) {
            p.setString(1, studentId); p.setString(2, studentName); p.setString(3, eventName);
            p.executeUpdate();
        }
    }
    public List<Registration> page(String event, int limit, int offset) throws SQLException {
        List<Registration> rows = new ArrayList<>();
        boolean filtered = event != null && !event.equals("All");
        String sql = "SELECT id, student_id, student_name, event_name, registered_at FROM registrations " +
                (filtered ? "WHERE event_name=? " : "") +
                "ORDER BY id DESC LIMIT ? OFFSET ?";
        try (Connection c=DB.getConnection(); PreparedStatement p=c.prepareStatement(sql)) {
            int i=1; if (filtered) p.setString(i++, event);
            p.setInt(i++, limit); p.setInt(i, offset);
            try (ResultSet r=p.executeQuery()) {
                while (r.next()) rows.add(new Registration(r.getInt("id"), r.getString("student_id"),
                        r.getString("student_name"), r.getString("event_name"), r.getString("registered_at")));
            }
        }
        return rows;
    }
}
