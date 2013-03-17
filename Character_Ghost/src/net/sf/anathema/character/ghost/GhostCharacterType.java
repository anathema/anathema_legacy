package net.sf.anathema.character.ghost;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitTypeEnum;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterType
@Weight(weight = 8)
public class GhostCharacterType implements ICharacterType {

  @Override
  public boolean isExaltType() {
    return false;
  }

  @Override
  public boolean isEssenceUser() {
    return true;
  }

  @Override
  public FavoringTraitType getFavoringTraitType() {
    return FavoringTraitTypeEnum.AbilityType;
  }

  @Override
  public String getId() {
    return "Ghost";
  }

  public boolean equals(Object other) {
    return other instanceof GhostCharacterType;
  }

  public int hashCode() {
    return 8;
  }
}