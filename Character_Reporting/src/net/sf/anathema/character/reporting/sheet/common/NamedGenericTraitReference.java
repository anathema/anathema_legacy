package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class NamedGenericTraitReference implements ITraitReference {

  private final String name;
  private final ITraitType type;

  public NamedGenericTraitReference(INamedGenericTrait trait) {
    this.type = trait.getType();
    this.name = trait.getName();
  }
  
  public String getName() {
    return name;
  }

  public ITraitType getTraitType() {
    return type;
  }
}