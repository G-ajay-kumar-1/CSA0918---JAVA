import java.util.regex.Pattern;

/**
 * Standalone demonstration of why concatenating untrusted input into SQL is unsafe.
 * It prints the constructed SQL only; it does NOT connect to or attack a database.
 * Use only a local assignment database for the JDBC demonstration.
 */
public class AdversarialInputDemo {
    public static void main(String[] args) {
        String id = "student1";
        String password = "' OR '1'='1";
        String unsafe = "SELECT * FROM users WHERE id='" + id
                + "' AND pwd='" + password + "'";
        System.out.println("Unsafe SQL (demonstration only):");
        System.out.println(unsafe);
        System.out.println();
        System.out.println("Safe design: SELECT * FROM users WHERE id=? AND pwd_hash=?");
        System.out.println("Bind id and password/hash with PreparedStatement.setString().");
        System.out.println("Do not attempt to repair SQL concatenation by escaping quotes or regex.");
        System.out.println("Input contains quote: " + Pattern.compile("'").matcher(password).find());
    }
}
