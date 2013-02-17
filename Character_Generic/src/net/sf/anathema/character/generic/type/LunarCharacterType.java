package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;

public class LunarCharacterType implements ICharacterType {
  @Override
  public void accept(ICharacterTypeVisitor visitor) {
    visitor.visitLunar(this);
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
    return FavoringTraitType.AttributeType;
  }

  @Override
  public boolean canAttuneToMalfeanMaterials() {
    return false;
  }

  @Override
  public String getId() {
    return "Lunar";
  }

  public boolean equals(Object other) {
    return other instanceof LunarCharacterType;
  }

  public int hashCode() {
    return 3;
  }
}