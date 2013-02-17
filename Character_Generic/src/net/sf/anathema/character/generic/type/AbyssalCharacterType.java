package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;

public class AbyssalCharacterType implements ICharacterType {
  @Override
  public void accept(ICharacterTypeVisitor visitor) {
    visitor.visitAbyssal(this);
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
    return "Abyssal";
  }

  public boolean equals(Object other) {
    return other instanceof AbyssalCharacterType;
  }

  public int hashCode() {
    return 4;
  }
}