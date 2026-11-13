# Task 5 – Four Event-Handling Styles

Run all four classes in `src/`. Each version also converts when Enter is pressed in the amount field, displays a result dialog for valid input and an error dialog for invalid input, and uses `WindowAdapter` for window closing.

| Style | Main advantage | Trade-off |
|---|---|---|
| Frame implements ActionListener | Simple for a tiny frame | Couples frame and handler; one handler method can become crowded |
| Named inner class | Reusable and clearly named | More lines of code |
| Anonymous inner class | Local handler with access to outer fields | Verbose and not independently reusable |
| Lambda | Concise for a single-method listener | Best for short handlers; complex logic should be moved to a method/service |

A method reference `convertButton.addActionListener(e -> convert())` can be shortened to `convertButton.addActionListener(e -> convert())` because `convert()` takes no event parameter; a direct `this::convert` is not type-compatible with `ActionListener` unless the method accepts an `ActionEvent`. A shared `Action` object is preferable when the same command must be used by buttons, menu items and keyboard shortcuts.
