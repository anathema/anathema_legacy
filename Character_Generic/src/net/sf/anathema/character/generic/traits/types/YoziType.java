package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.traits.ITraitType;

public enum YoziType implements ITraitType {

  Malfeas, Cecelyne, SheWhoLivesInHerName, Adorjan, EbonDragon, Kimbery;

  private YoziType() {
  }

  public String getId() {
    return name();
  }

  public void accept(ITraitTypeVisitor visitor) {
    visitor.visitYozi(this);
  }

  @Override
  public String toString() {
    return getId();
  }
}