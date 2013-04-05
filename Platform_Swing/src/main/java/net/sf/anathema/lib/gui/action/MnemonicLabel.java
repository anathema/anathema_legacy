package net.sf.anathema.lib.gui.action;

public class MnemonicLabel {

  private final String plainText;
  private final Character mnemonicCharacter;

  public MnemonicLabel(String plainText, Character mnemonicCharacter) {
    this.plainText = plainText;
    this.mnemonicCharacter = mnemonicCharacter;
  }

  public String getPlainText() {
    return plainText;
  }

  public Character getMnemonicCharacter() {
    return mnemonicCharacter;
  }
}