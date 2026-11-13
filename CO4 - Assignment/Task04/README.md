# Task 4 – Event Handling Forensics

- The callback is misspelled `mouseclicked`; Java requires the exact case-sensitive method name `mouseClicked`. It therefore never overrides the adapter method.
- The label remains unchanged because no valid callback executes and the code does not update the label.
- `getGraphics()` draws directly to the current surface. Minimising/restoring or resizing causes repainting, and those temporary pixels are lost.
- Repair: use `@Override public void mouseClicked(MouseEvent e)`, store each point, update the label, call `repaint()`, and redraw all points in `paint(Graphics)`.
- `@Override` causes a compile-time error when the method name/signature does not match a superclass/interface method.

## Required failure demonstration
Temporarily remove `@Override` and change `mouseClicked` to `mouseclicked`. Compile and record the result: compilation succeeds, but the adapter's callback is not overridden, so clicks are ignored. Restore the correct method before final submission.

## Alternative: BufferedImage
With a point list, repaint time is O(n) for n stored points and point storage is O(n). With an off-screen BufferedImage, drawing each click updates the image once and repaint copies the image, approximately O(width × height) pixel transfer (implementation-dependent), while image storage is O(width × height) pixels. The image approach can make repeated repainting independent of the number of clicks but consumes more fixed memory.
