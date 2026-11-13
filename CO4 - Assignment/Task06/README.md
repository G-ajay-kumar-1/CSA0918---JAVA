# Task 6 – Campus Event Registration

## Architecture
- `EventRegistrationApp`: Swing presentation layer; owns components and event handlers.
- `RegistrationService`: validation and application rules.
- `RegistrationDAO`: JDBC persistence with parameterised SQL and pagination.
- `DB`: connection configuration from environment variables.
- `Registration`: row model.

## Database
Run `database/schema.sql` in MySQL. Configure `DB_URL`, `DB_USER`, and `DB_PASSWORD`. Add MySQL Connector/J to the Java classpath.

## Test cases
1. Valid registration: a new student ID + event should insert and appear in the table.
2. Invalid input: blank/invalid ID, short name or unselected event should show a clear validation error.
3. Duplicate registration: same student ID + same event should be rejected by the database unique constraint.
4. Filter: select an event and apply filter.
5. Pagination: Previous/Next should retrieve pages using LIMIT/OFFSET.
6. Responsiveness: trigger a database operation and verify that the window can still repaint/respond; database work runs in SwingWorker.

## Important
Use a test database and do not commit real credentials. The application expects the database schema in `database/schema.sql`.
