package net.sf.anathema.character.main.magic.charms;

public class ComboIdProvider {

  private int nextId;

  public int createId() {
    return nextId++;
  }
}