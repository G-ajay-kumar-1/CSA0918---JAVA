import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConverterLambda extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");
    public ConverterLambda() {
        super("Converter – Lambda"); setLayout(new FlowLayout());
        JButton convert = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(convert); add(result);
        ActionListener shared = e -> convert();
        // TODO 1 completed: one lambda registered for both triggers.
        convert.addActionListener(shared);
        amount.addActionListener(shared);
        addWindowListener(new WindowAdapter() { @Override public void windowClosing(WindowEvent e) { dispose(); }});
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); pack(); setLocationRelativeTo(null);
    }
    private void convert() {
        // TODO 2 completed: parse, format to two decimals, or show validation message.
        try {
            double value = Double.parseDouble(amount.getText().trim());
            if (!Double.isFinite(value) || value < 0) throw new NumberFormatException();
            double rate = "USD".equals(target.getSelectedItem()) ? 0.012 : 0.011;
            String message = String.format("%.2f %s", value * rate, target.getSelectedItem());
            result.setText(message);
            JOptionPane.showMessageDialog(this, message, "Conversion", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            result.setText("Invalid amount");
            JOptionPane.showMessageDialog(this, "Invalid amount", "Input error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new ConverterLambda().setVisible(true)); }
}
