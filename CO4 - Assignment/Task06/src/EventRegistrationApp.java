import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EventRegistrationApp extends JFrame {
    private final JTextField studentId = new JTextField(12), studentName = new JTextField(16);
    private final JComboBox<String> event = new JComboBox<>(new String[]{
            "Select event", "Tech Fest", "Cultural Fest", "Sports Meet", "Workshop"});
    private final JComboBox<String> filter = new JComboBox<>(new String[]{
            "All", "Tech Fest", "Cultural Fest", "Sports Meet", "Workshop"});
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Student ID", "Student Name", "Event", "Registered At"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable table = new JTable(model);
    private final JLabel status = new JLabel("Ready");
    private final RegistrationService service = new RegistrationService();
    private final int pageSize = 50;
    private int page = 0;

    public EventRegistrationApp() {
        super("Campus Event Registration");
        setLayout(new BorderLayout(8,8));
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File"), help = new JMenu("Help");
        JMenuItem refresh = new JMenuItem("Refresh"), export = new JMenuItem("Export CSV"), exit = new JMenuItem("Exit"), about = new JMenuItem("About");
        file.add(refresh); file.add(export); file.add(exit); help.add(about); bar.add(file); bar.add(help); setJMenuBar(bar);
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.setBorder(BorderFactory.createTitledBorder("New registration"));
        form.add(new JLabel("Student ID")); form.add(studentId);
        form.add(new JLabel("Name")); form.add(studentName);
        form.add(new JLabel("Event")); form.add(event);
        JButton register = new JButton("Register"); form.add(register);
        JPanel top = new JPanel(new BorderLayout()); top.add(form, BorderLayout.CENTER);
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter event:")); filterPanel.add(filter);
        JButton apply = new JButton("Apply filter"), prev = new JButton("Previous"), next = new JButton("Next");
        filterPanel.add(apply); filterPanel.add(prev); filterPanel.add(next);
        top.add(filterPanel, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH); add(new JScrollPane(table), BorderLayout.CENTER); add(status, BorderLayout.SOUTH);

        register.addActionListener(e -> register());
        apply.addActionListener(e -> { page=0; loadPage(); });
        refresh.addActionListener(e -> loadPage());
        export.addActionListener(e -> exportCsv());
        prev.addActionListener(e -> { if(page>0){page--;loadPage();} });
        next.addActionListener(e -> { page++; loadPage(); });
        exit.addActionListener(e -> dispose());
        about.addActionListener(e -> JOptionPane.showMessageDialog(this, "Campus Event Registration – CSA09 CO4"));
        addWindowListener(new WindowAdapter(){ @Override public void windowClosing(WindowEvent e){ dispose(); }});
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); setSize(950,560); setLocationRelativeTo(null);
        loadPage();
    }

    private void register() {
        String id=studentId.getText(), name=studentName.getText(), selected=(String)event.getSelectedItem();
        setBusy(true);
        new SwingWorker<Void,Void>() {
            private String message;
            @Override protected Void doInBackground() throws Exception {
                service.register(id,name,selected); message="Registration saved."; return null;
            }
            @Override protected void done() {
                setBusy(false);
                try { get(); status.setText(message); studentId.setText(""); studentName.setText(""); page=0; loadPage(); }
                catch(Exception ex) { Throwable cause=ex.getCause()==null?ex:ex.getCause();
                    status.setText(cause.getMessage()); JOptionPane.showMessageDialog(EventRegistrationApp.this,
                            cause.getMessage(),"Registration error",JOptionPane.ERROR_MESSAGE); }
            }
        }.execute();
    }

    private void loadPage() {
        String selected=(String)filter.getSelectedItem();
        status.setText("Loading registrations...");
        new SwingWorker<List<Registration>,Void>() {
            @Override protected List<Registration> doInBackground() throws Exception {
                return service.getPage(selected,pageSize,page*pageSize);
            }
            @Override protected void done() {
                try {
                    List<Registration> rows=get(); model.setRowCount(0);
                    for(Registration r:rows) model.addRow(new Object[]{r.id,r.studentId,r.studentName,r.eventName,r.registeredAt});
                    status.setText("Page " + (page+1) + " – " + rows.size() + " record(s)");
                } catch(Exception ex) { status.setText("Database error: " + ex.getMessage()); }
            }
        }.execute();
    }
    private void exportCsv() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new java.io.File("event-registrations.csv"));
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        java.io.File destination = chooser.getSelectedFile();
        String selectedEvent = (String) filter.getSelectedItem();
        status.setText("Exporting registrations...");
        new SwingWorker<Integer,Void>() {
            @Override protected Integer doInBackground() throws Exception {
                // Export all matching rows, not only the currently visible page.
                List<Registration> rows = service.getPage(selectedEvent, 100000, 0);
                try (java.io.PrintWriter out = new java.io.PrintWriter(destination, java.nio.charset.StandardCharsets.UTF_8)) {
                    out.println("\"ID\",\"Student ID\",\"Student Name\",\"Event\",\"Registered At\"");
                    for (Registration r : rows) {
                        out.println(r.id + "," + csv(r.studentId) + "," + csv(r.studentName) + "," + csv(r.eventName) + "," + csv(r.registeredAt));
                    }
                }
                return rows.size();
            }
            @Override protected void done() {
                try { status.setText("Exported " + get() + " registration(s) to CSV."); }
                catch (Exception ex) { status.setText("Export failed: " + ex.getMessage()); }
            }
        }.execute();
    }
    private String csv(String value) { return "\"" + value.replace("\"", "\"\"") + "\""; }

    private void setBusy(boolean busy) {
        // Disable form controls during a registration request; EDT remains available.
        studentId.setEnabled(!busy); studentName.setEnabled(!busy); event.setEnabled(!busy);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventRegistrationApp().setVisible(true));
    }
}
