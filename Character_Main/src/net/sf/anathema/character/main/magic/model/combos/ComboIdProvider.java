package net.sf.anathema.character.main.magic.model.combos;

public class ComboIdProvider {

  private int nextId;

  public int createId() {
    return nextId++;
  }
}