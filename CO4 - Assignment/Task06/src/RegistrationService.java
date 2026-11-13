import java.sql.SQLException;
import java.util.List;
public class RegistrationService {
    private final RegistrationDAO dao = new RegistrationDAO();
    public void register(String id, String name, String event) throws Exception {
        if (id == null || !id.matches("[A-Za-z0-9-]{3,20}"))
            throw new IllegalArgumentException("Student ID must be 3–20 letters, digits or hyphens.");
        if (name == null || name.trim().length() < 2)
            throw new IllegalArgumentException("Enter a valid student name.");
        if (event == null || event.trim().isEmpty() || event.equals("Select event"))
            throw new IllegalArgumentException("Select an event.");
        try { dao.insert(id.trim(), name.trim(), event); }
        catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) throw new IllegalArgumentException("This student is already registered for this event.");
            throw ex;
        }
    }
    public List<Registration> getPage(String event, int limit, int offset) throws SQLException {
        return dao.page(event, limit, offset);
    }
}
