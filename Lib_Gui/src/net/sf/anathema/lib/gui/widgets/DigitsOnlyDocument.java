package net.sf.anathema.lib.gui.widgets;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DigitsOnlyDocument extends PlainDocument {

  @Override
  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
    if (str == null) {
      return;
    }
    for (char character : str.toCharArray()) {
      if (character != '-' && (Character.getNumericValue(character) < 0 || Character.getNumericValue(character) > 9)) {
        return;
      }
      // '\u0030' through '\u0039', ISO-LATIN-1 digits ('0' through '9')
    }
    super.insertString(offs, str, a);
  }
}