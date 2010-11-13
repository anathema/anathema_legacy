package net.sf.anathema.character.impl.model.charm;

public class ComboIdProvider {

  private int nextId;

  public int createId() {
    return nextId++;
  }
}