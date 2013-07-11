package net.sf.anathema.hero.combos.model;

public class ComboIdProvider {

  private int nextId;

  public int createId() {
    return nextId++;
  }
}