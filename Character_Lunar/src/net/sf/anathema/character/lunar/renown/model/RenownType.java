package net.sf.anathema.character.lunar.renown.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;

public enum RenownType implements ITraitType {

  Succor, Glory, Mettle, Cunning;

  public String getId() {
    return name();
  }

  public void accept(ITraitTypeVisitor visitor) {
    visitor.visitCustomTraitType(this);
  }
}