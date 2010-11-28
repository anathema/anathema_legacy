package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.traits.ITraitType;

public enum VirtueType implements ITraitType {
  Compassion, Conviction, Temperance, Valor;

  public String getId() {
    return name();
  }

  public void accept(ITraitTypeVisitor visitor) {
    visitor.visitVirtue(this);
  }

  @Override
  public String toString() {
    return getId();
  }
}