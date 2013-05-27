package net.sf.anathema.platform;

public class Hotkey {
  private Character character;

  public Hotkey(Character character) {
    this.character = character;
  }

  public String asString() {
    return character.toString();
  }
}