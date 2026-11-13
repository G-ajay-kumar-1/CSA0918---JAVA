import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConverterNamedInner extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");
    public ConverterNamedInner() {
        super("Converter – Named Inner Class"); setLayout(new FlowLayout());
        JButton convert = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(convert); add(result);
        ConvertHandler handler = new ConvertHandler();
        convert.addActionListener(handler); amount.addActionListener(handler);
        addWindowListener(new WindowAdapter() { @Override public void windowClosing(WindowEvent e) { dispose(); }});
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); pack(); setLocationRelativeTo(null);
    }
    private void convert() {
        try { double v = Double.parseDouble(amount.getText().trim());
            if (!Double.isFinite(v) || v < 0) throw new NumberFormatException();
            String message = String.format("%.2f %s", v * ("USD".equals(target.getSelectedItem()) ? .012 : .011), target.getSelectedItem());
            result.setText(message); JOptionPane.showMessageDialog(this, message, "Conversion", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) { result.setText("Invalid amount"); JOptionPane.showMessageDialog(this, "Invalid amount", "Input error", JOptionPane.ERROR_MESSAGE); }
    }
    private class ConvertHandler implements ActionListener { public void actionPerformed(ActionEvent e) { convert(); } }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new ConverterNamedInner().setVisible(true)); }
}
