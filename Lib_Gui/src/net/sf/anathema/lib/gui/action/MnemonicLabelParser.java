package net.sf.anathema.lib.gui.action;

public class MnemonicLabelParser {

  /**
   * Mnemonics are indicated by an '&' that causes
   * the next character to be the mnemonic. The mnemonic indicator character '&' can be
   * escaped by doubling it in the string, causing a single '&' to be
   * displayed.
   */
  public static MnemonicLabel parse(String label) {
    int index = -1;
    boolean found = false;
    do {
      ++index;
      index = label.indexOf('&', index);
      if (index != -1 && index + 1 < label.length()) {
        if (label.charAt(index + 1) == '&') {
          label = label.substring(0, index) + label.substring(index + 1);
        }
        else {
          found = true;
          break;
        }
      }
    } while (index != -1 && index + 1 < label.length());
    if (found) {
      final char mnemonic = label.charAt(index + 1);
      return new MnemonicLabel(
          label.substring(0, index) + label.substring(index + 1),
          new Character(mnemonic));
    }
    return new MnemonicLabel(label, null);
  }
}