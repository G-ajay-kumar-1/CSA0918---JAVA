import java.awt.*;
import javax.swing.*;

public class LibrarySearchUI extends JFrame {
    private final JTextField searchField = new JTextField(24);
    private final JComboBox<String> category = new JComboBox<>(
            new String[]{"All", "Books", "Journals", "Theses"});
    private final DefaultListModel<String> resultsModel = new DefaultListModel<>();
    private final JList<String> results = new JList<>(resultsModel);
    private final JLabel status = new JLabel("Ready");

    public LibrarySearchUI() {
        super("Campus Library Search");
        setLayout(new BorderLayout(8, 8));
        JLabel title = new JLabel("Campus Library Search", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        add(title, BorderLayout.NORTH);

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchRow.add(new JLabel("Search:")); searchRow.add(searchField);
        searchRow.add(category);
        JButton search = new JButton("Search"); searchRow.add(search);
        JPanel center = new JPanel(new BorderLayout());
        center.add(searchRow, BorderLayout.NORTH);
        results.setVisibleRowCount(10);
        center.add(new JScrollPane(results), BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prev = new JButton("Prev"), next = new JButton("Next"), reserve = new JButton("Reserve");
        buttons.add(prev); buttons.add(next); buttons.add(reserve);
        bottom.add(buttons, BorderLayout.CENTER); bottom.add(status, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        search.addActionListener(e -> {
            resultsModel.clear();
            String q = searchField.getText().trim();
            if (q.isEmpty()) { status.setText("Enter a search term."); return; }
            resultsModel.addElement("Sample result for \"" + q + "\" (" + category.getSelectedItem() + ")");
            resultsModel.addElement("Example: Java Programming – Library Catalogue");
            status.setText("2 demonstration results. Connect to catalogue service for live data.");
        });
        prev.addActionListener(e -> status.setText("Previous page selected."));
        next.addActionListener(e -> status.setText("Next page selected."));
        reserve.addActionListener(e -> status.setText(results.getSelectedValue() == null
                ? "Select a result first." : "Reservation request: " + results.getSelectedValue()));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(560, 360)); setSize(760, 500); setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySearchUI().setVisible(true));
    }
}
