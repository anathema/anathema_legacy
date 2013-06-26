package net.sf.anathema.character.model.charm;

public class ComboIdProvider {

  private int nextId;

  public int createId() {
    return nextId++;
  }
}