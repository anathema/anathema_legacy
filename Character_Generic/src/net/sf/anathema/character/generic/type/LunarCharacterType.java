package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterType
@Weight(weight = 3)
public class LunarCharacterType implements ICharacterType {
  @Override
  public void accept(ICharacterTypeVisitor visitor) {
    visitor.visitLunar();
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