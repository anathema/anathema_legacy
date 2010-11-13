package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class SubTraitReference implements ITraitReference {

  private final ITraitType basicTraitType;
  private final String name;

  public SubTraitReference(ISubTrait subTrait) {
    basicTraitType = subTrait.getBasicTraitType();
    name = subTrait.getName();
  }

  public ITraitType getTraitType() {
    return basicTraitType;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SubTraitReference)) {
      return false;
    }
    SubTraitReference subTraitReference = (SubTraitReference) obj;
    return basicTraitType == subTraitReference.basicTraitType && name.equals(subTraitReference.name);
  }

  @Override
  public int hashCode() {
    return basicTraitType.hashCode() * 5 + name.hashCode() * 3;
  }
}