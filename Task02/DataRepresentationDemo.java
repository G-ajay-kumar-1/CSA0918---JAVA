public class DataRepresentationDemo {
    public static void main(String[] args) {
        int[] slotsPerFloor = {2, 3, 1};
        String[][] slots = new String[slotsPerFloor.length][];
        for (int floor = 0; floor < slotsPerFloor.length; floor++) {
            slots[floor] = new String[slotsPerFloor[floor]];
        }
        slots[1][2] = "TN02CD2222";
        int floor = 1, slot = 2;
        int durationMinutes = 95;
        int billableHours = (durationMinutes + 59) / 60;
        double hourlyRate = 30.0;
        double fee = billableHours * hourlyRate;
        System.out.println("Vehicle at floor " + floor + ", slot " + slot + ": " + slots[floor][slot]);
        System.out.println("Billable hours: " + billableHours);
        System.out.println("Fee: Rs " + fee);
        // Incorrect: durationMinutes / 60 performs integer division and returns 1.
        // Correct ceiling calculation: (durationMinutes + 59) / 60 returns 2.
    }
}
