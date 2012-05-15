package net.sf.anathema.lib.gui.dialog.widgets;

import java.awt.FontMetrics;
import java.util.HashMap;
import java.util.Map;

public enum TextBlockDelimiter {

  SPACE(' ', " ", " ", false, true, false), //$NON-NLS-1$ //$NON-NLS-2$
  //  
  TAB('\t', "\t", "        ", false, true, false), //$NON-NLS-1$//$NON-NLS-2$
  //  
  NEWLINE('\n', "\n", "\n", true, false, false), //$NON-NLS-1$ //$NON-NLS-2$
  //  
  END_OF_TEXT((char) 0, "", "", true, false, false), //$NON-NLS-1$ //$NON-NLS-2$
  //      
  HYPHEN('-', "", "-", false, false, true), //$NON-NLS-1$//$NON-NLS-2$
  //      
  COMMA(',', "", "", false, false, true), //$NON-NLS-1$ //$NON-NLS-2$
  //  
  SEMICOLON(';', "", ";", false, false, true); //$NON-NLS-1$ //$NON-NLS-2$

  private static Map<Character, TextBlockDelimiter> DELIMITER_MAP = new HashMap<Character, TextBlockDelimiter>();

  static {
    for (TextBlockDelimiter tag : values()) {
      char key = tag.getCharacter();
      if (key != 0) {
        DELIMITER_MAP.put(key, tag);
      }
    }
  }

  private final char character;
  private final String string;
  private final String replacement;
  private final boolean newLine;
  private final boolean whitespace;
  boolean additionalCharacterIncluded;

  TextBlockDelimiter(
      char character,
      String string,
      String replacement,
      boolean newLine,
      boolean whitespace,
      boolean additionalCharacterIncluded) {
    this.character = character;
    this.string = string;
    this.replacement = replacement;
    this.newLine = newLine;
    this.whitespace = whitespace;
    this.additionalCharacterIncluded = additionalCharacterIncluded;
  }

  public char getCharacter() {
    return character;
  }

  public String getString() {
    return string;
  }

  public int calculateWidth(FontMetrics metrics) {
    return metrics.stringWidth(replacement);
  }

  public boolean isNewLine() {
    return newLine;
  }

  public boolean isWhitespace() {
    return whitespace;
  }

  public static TextBlockDelimiter forCharacterOrNull(char character) {
    return DELIMITER_MAP.get(character);
  }

  public boolean isAdditionalCharacterIncluded() {
    return additionalCharacterIncluded;
  }
}