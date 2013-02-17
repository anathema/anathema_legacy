package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;

public class MortalCharacterType implements ICharacterType {
  @Override
  public void accept(ICharacterTypeVisitor visitor) {
    visitor.visitMortal(this);
  }

  @Override
  public boolean isExaltType() {
    return false;
  }

  @Override
  public boolean isEssenceUser() {
    return false;
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
    return "Mortal";
  }

  public boolean equals(Object other) {
    return other instanceof MortalCharacterType;
  }

  public int hashCode() {
    return 8;
  }
}