package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;

public class InfernalCharacterType implements ICharacterType {
  @Override
  public void accept(ICharacterTypeVisitor visitor) {
    visitor.visitInfernal(this);
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
    return FavoringTraitType.YoziType;
  }

  @Override
  public boolean canAttuneToMalfeanMaterials() {
    return true;
  }

  @Override
  public String getId() {
    return "Infernal";
  }

  public boolean equals(Object other) {
    return other instanceof InfernalCharacterType;
  }

  public int hashCode() {
    return 6;
  }
}