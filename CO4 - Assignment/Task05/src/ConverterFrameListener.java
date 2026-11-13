import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConverterFrameListener extends JFrame implements ActionListener {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");
    public ConverterFrameListener() {
        super("Converter – Frame Listener");
        setLayout(new FlowLayout());
        JButton convert = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(convert); add(result);
        convert.addActionListener(this); amount.addActionListener(this);
        addWindowListener(new WindowAdapter() { @Override public void windowClosing(WindowEvent e) { dispose(); }});
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); pack(); setLocationRelativeTo(null);
    }
    private void convert() {
        try { double inr = Double.parseDouble(amount.getText().trim());
            if (!Double.isFinite(inr) || inr < 0) throw new NumberFormatException();
            double rate = "USD".equals(target.getSelectedItem()) ? 0.012 : 0.011;
            String message = String.format("%.2f %s", inr * rate, target.getSelectedItem());
            result.setText(message); JOptionPane.showMessageDialog(this, message, "Conversion", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) { result.setText("Invalid amount"); JOptionPane.showMessageDialog(this, "Invalid amount", "Input error", JOptionPane.ERROR_MESSAGE); }
    }
    @Override public void actionPerformed(ActionEvent e) { convert(); }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new ConverterFrameListener().setVisible(true)); }
}
