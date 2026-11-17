import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/** Creates deliberately malformed CSV-like rows for testing a defensive parser. */
public class DamagedPassFileDemo {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("damaged-passes.csv");
        List<String> rows = List.of(
            "P1001,192311001,Arun,12,4500.0",
            "P1002,DAMAGED LINE",
            "P1003,192311003,Divya,7,not-a-number",
            "",
            "P1004,192311004,Meena,4,3800.0"
        );
        Files.write(file, rows);
        System.out.println("Created test file: " + file.toAbsolutePath());
        int valid = 0, invalid = 0;
        for (int i = 0; i < rows.size(); i++) {
            String[] f = rows.get(i).split(",", -1);
            boolean ok = f.length == 5 && !f[0].isBlank() && !f[1].isBlank()
                    && !f[2].isBlank() && f[3].matches("\\d+");
            if (ok) {
                try { Double.parseDouble(f[4]); }
                catch (NumberFormatException ex) { ok = false; }
            }
            if (ok) valid++;
            else {
                invalid++;
                System.out.println("Rejected line " + (i + 1) + ": " + rows.get(i));
            }
        }
        System.out.println("Valid rows=" + valid + ", rejected rows=" + invalid);
    }
}
