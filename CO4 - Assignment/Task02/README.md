# Task 2 – Hospital Outpatient Appointment Desk

## 2.1 Event model
| Event source | Event object | Listener | Action |
|---|---|---|---|
| Department JComboBox | ItemEvent | ItemListener | Load doctors for selected department and refresh doctor combo |
| Doctor JComboBox | ItemEvent | ItemListener | Update selected doctor state |
| Time-slot JComboBox | ItemEvent | ItemListener | Store selected slot |
| Book JButton | ActionEvent | ActionListener | Validate patient, department, doctor and slot; book; show confirmation |
| Window close | WindowEvent | WindowListener/WindowAdapter | If booking is in progress, ask before closing |

## 2.2 Component hierarchy
```text
JFrame: AppointmentDesk
└── JPanel (BorderLayout)
    ├── JPanel (GridLayout 4x2) – patient ID, department, doctor, time slot
    ├── JPanel (FlowLayout) – Book and Clear buttons
    └── JLabel – status / confirmation
```
Components: JFrame, JPanel, JLabel, JTextField, JComboBox<String>, JButton, JOptionPane.

## 2.3 Handler pseudocode
```text
ALGORITHM RefreshDoctors
INPUT selectedDepartment
BEGIN
    doctorCombo.removeAllItems()
    doctorList ← doctorMap.get(selectedDepartment)
    FOR EACH doctor IN doctorList
        doctorCombo.addItem(doctor.name)
    END FOR
    IF doctorCombo.getItemCount() = 0 THEN
        doctorCombo.setEnabled(FALSE)
    ELSE
        doctorCombo.setEnabled(TRUE)
    END IF
END

ALGORITHM BookAppointment
BEGIN
    IF patientId is empty THEN show error; RETURN
    IF department, doctor or slot is not selected THEN show error; RETURN
    IF bookingInProgress THEN RETURN
    bookingInProgress ← TRUE
    validate availability and save appointment in service/DAO
    IF saved THEN show confirmation ELSE show error
    bookingInProgress ← FALSE
END
```
For a real GUI, database calls must run in `SwingWorker`, not on the Event Dispatch Thread.

## 2.4 Event trace and applet comparison
Window creation → construct components → register listeners → set visible → receptionist changes department → `itemStateChanged` refreshes doctors → receptionist clicks Book → `actionPerformed` validates and books → closing request invokes `windowClosing` → confirmation dialog if booking is active → `dispose()` after confirmation.

An applet uses `init()`, `start()`, `paint()`, `stop()` and `destroy()` as container-managed lifecycle callbacks. A desktop JFrame instead uses construction/visibility and window events such as `windowOpened`, `windowClosing` and `windowClosed`; it does not use the applet lifecycle.

## Alternative and complexity
With a preloaded `Map<String,List<Doctor>>`, lookup by department is approximately O(1) on average, followed by O(m) to populate m doctors in the combo box. Memory is O(d + total doctors), or O(d·m) when each of d departments has m doctors. Fetching from the database on every change adds network/query latency O(Q + m) per change, where Q is query/round-trip cost, and uses less retained application memory (roughly O(m) for the current result). Caching is preferable when department data changes infrequently; database refresh is useful when data must be current.
