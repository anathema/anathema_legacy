package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;

public class SolarCharacterType implements ICharacterType {
  @Override
  public void accept(ICharacterTypeVisitor visitor) {
    visitor.visitSolar(this);
  }

  @Override
  public boolean isExaltType() {
    return true;
  }

  @Override
  public boolean isEssenceUser() {
    return true;
  }

  @Override
  public FavoringTraitType getFavoringTraitType() {
    return FavoringTraitType.AbilityType;
  }

  @Override
  public boolean canAttuneToMalfeanMaterials() {
    return false;
  }

  @Override
  public String getId() {
    return "Solar";
  }

  public boolean equals(Object other) {
    return other instanceof SolarCharacterType;
  }

  public int hashCode() {
    return 1;
  }
}