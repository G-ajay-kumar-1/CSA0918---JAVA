# Evidence Checklist – Capture from your own execution

Do not fabricate or reuse another person's screenshots. Capture the actual program running on your computer. Keep the application window and relevant result visible; add a short caption naming the requirement/test.

| Task | Screenshot / evidence to capture |
|---|---|
| 1 | Fee window initial state; valid payment result; invalid/empty amount message; receipt action; clear action |
| 2 | Appointment component hierarchy diagram; event table and event trace in report (no executable screenshot required by brief) |
| 3 | Library search at small, medium and large window sizes; show that centre results area expands |
| 4 | DrawPad with several dots and click count; minimise and restore with dots still visible; compiler output after deliberate misspelling test |
| 5 | Each of four converter styles running; valid conversion; invalid amount; Enter-key conversion; dialog result in lambda version |
| 6 | Registration form and table; successful registration; invalid input error; duplicate registration rejection; event filter; next/previous page; responsive window during database work |

Suggested filenames:
- `Task01/screenshots/01_initial.png`, `02_valid_payment.png`, `03_invalid_input.png`
- `Task03/screenshots/01_small.png`, `02_medium.png`, `03_large.png`
- `Task04/screenshots/01_drawn_points.png`, `02_after_restore.png`, `03_override_compile_error.png`
- `Task05/screenshots/01_frame_listener.png`, `02_named_inner.png`, `03_anonymous.png`, `04_lambda_valid.png`, `05_lambda_invalid.png`
- `Task06/screenshots/01_main_screen.png`, `02_valid_registration.png`, `03_invalid_input.png`, `04_duplicate_blocked.png`, `05_filter_page.png`

Every screenshot must be linked to the requirement or test it demonstrates. A window screenshot alone is not sufficient.
