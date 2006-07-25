package net.sf.anathema.lib.gui.widgets;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import net.disy.commons.core.util.ObjectUtilities;

public class DigitsOnlyDocument extends PlainDocument {

  private final boolean beepOnInvalidCharacter;

  public DigitsOnlyDocument(boolean beepOnInvalidCharacter) {
    this.beepOnInvalidCharacter = beepOnInvalidCharacter;
  }

  @Override
  public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
    String currentText = getText(offset, length);
    if (ObjectUtilities.equals(currentText, text)) {
      return;
    }
    String correctedString = getCorrectedString(offset, text);
    super.replace(offset, length, correctedString, attrs);
  }

  @Override
  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
    String correctedString = getCorrectedString(offs, str);
    super.insertString(offs, correctedString, a);
  }

  private String getCorrectedString(int offs, String str) {
    char[] sourceCharacters = str.toCharArray();
    StringBuilder legalStringBuilder = new StringBuilder();

    for (int index = 0; index < sourceCharacters.length; index++) {
      if (isLegalCharacter(offs + index, sourceCharacters[index])) {
        legalStringBuilder.append(sourceCharacters[index]);
      }
    }

    if (legalStringBuilder.length() < str.length()) {
      beep();
    }
    return legalStringBuilder.toString();
  }

  private boolean isLegalCharacter(int index, char character) {
    return Character.isDigit(character) || (index == 0 && character == '-');
    // '\u0030' through '\u0039', ISO-LATIN-1 digits ('0' through '9')
  }

  private void beep() {
    if (beepOnInvalidCharacter) {
      Toolkit.getDefaultToolkit().beep();
    }
  }
}