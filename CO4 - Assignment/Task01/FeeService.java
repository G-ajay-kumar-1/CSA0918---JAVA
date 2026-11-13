public class FeeService {
    public static class PaymentResult {
        private final boolean success;
        private final String message;
        public PaymentResult(boolean success, String message) {
            this.success = success; this.message = message;
        }
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
    }

    // Business validation belongs here, not inside the window's event handler.
    public PaymentResult pay(String regNo, String amountText) {
        if (regNo == null || regNo.trim().isEmpty())
            return new PaymentResult(false, "Enter a registration number.");
        final double amount;
        try { amount = Double.parseDouble(amountText.trim()); }
        catch (RuntimeException ex) {
            return new PaymentResult(false, "Enter a valid amount.");
        }
        if (!Double.isFinite(amount) || amount <= 0)
            return new PaymentResult(false, "Amount must be greater than zero.");
        // A real deployment calls a DAO using PreparedStatement and a transaction.
        return new PaymentResult(true, String.format("Payment of %.2f recorded for %s.", amount, regNo.trim()));
    }
}
