package net.sf.anathema.lib.gui.dialog.widgets;

import java.awt.FontMetrics;
import java.util.HashMap;
import java.util.Map;

public enum TextBlockDelimiter {

  SPACE(' ', " ", " ", false, true, false),
  //  
  TAB('\t', "\t", "        ", false, true, false),
  //  
  NEWLINE('\n', "\n", "\n", true, false, false),
  //  
  END_OF_TEXT((char) 0, "", "", true, false, false),
  //      
  HYPHEN('-', "", "-", false, false, true),
  //      
  COMMA(',', "", "", false, false, true),
  //  
  SEMICOLON(';', "", ";", false, false, true);

  private static Map<Character, TextBlockDelimiter> DELIMITER_MAP = new HashMap<>();

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