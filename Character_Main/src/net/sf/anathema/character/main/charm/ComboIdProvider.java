package net.sf.anathema.character.main.charm;

public class ComboIdProvider {

  private int nextId;

  public int createId() {
    return nextId++;
  }
}