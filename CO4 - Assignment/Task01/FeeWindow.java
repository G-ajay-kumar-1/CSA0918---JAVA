import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FeeWindow extends JFrame {
    private final JTextField regNo = new JTextField(14);
    private final JTextField amount = new JTextField(14);
    private final JLabel status = new JLabel("Enter fee details.");
    private final FeeService service = new FeeService();

    public FeeWindow() {
        super("Student Fee Payment");
        setLayout(new BorderLayout(8, 8));
        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.add(new JLabel("Registration number:")); form.add(regNo);
        form.add(new JLabel("Amount:")); form.add(amount);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton pay = new JButton("Pay");
        JButton clear = new JButton("Clear");
        JButton receipt = new JButton("Receipt");
        actions.add(pay); actions.add(clear); actions.add(receipt);
        add(form, BorderLayout.CENTER); add(actions, BorderLayout.SOUTH); add(status, BorderLayout.NORTH);
        pay.addActionListener(e -> doPayment());
        clear.addActionListener(e -> { regNo.setText(""); amount.setText(""); status.setText("Fields cleared."); });
        receipt.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "A receipt is available after a successful payment.", "Receipt", JOptionPane.INFORMATION_MESSAGE));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(FeeWindow.this, "Close the payment window?",
                        "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) dispose();
            }
        });
        setSize(430, 190); setLocationRelativeTo(null);
    }

    private void doPayment() {
        FeeService.PaymentResult result = service.pay(regNo.getText(), amount.getText());
        status.setText(result.getMessage());
        JOptionPane.showMessageDialog(this, result.getMessage(),
                result.isSuccess() ? "Payment" : "Validation", result.isSuccess()
                        ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeeWindow().setVisible(true));
    }
}
