import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConverterAnonymous extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");
    public ConverterAnonymous() {
        super("Converter – Anonymous Inner Class"); setLayout(new FlowLayout());
        JButton convert = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(convert); add(result);
        ActionListener handler = new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                try { double v = Double.parseDouble(amount.getText().trim());
                    if (!Double.isFinite(v) || v < 0) throw new NumberFormatException();
                    String message = String.format("%.2f %s", v * ("USD".equals(target.getSelectedItem()) ? .012 : .011), target.getSelectedItem());
                    result.setText(message); JOptionPane.showMessageDialog(ConverterAnonymous.this, message, "Conversion", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) { result.setText("Invalid amount"); JOptionPane.showMessageDialog(ConverterAnonymous.this, "Invalid amount", "Input error", JOptionPane.ERROR_MESSAGE); }
            }
        };
        convert.addActionListener(handler); amount.addActionListener(handler);
        addWindowListener(new WindowAdapter() { @Override public void windowClosing(WindowEvent e) { dispose(); }});
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); pack(); setLocationRelativeTo(null);
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new ConverterAnonymous().setVisible(true)); }
}
