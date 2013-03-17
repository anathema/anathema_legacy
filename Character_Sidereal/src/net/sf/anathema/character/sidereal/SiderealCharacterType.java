package net.sf.anathema.character.sidereal;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitTypeEnum;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterType
@Weight(weight = 5)
public class SiderealCharacterType implements ICharacterType {

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
    return FavoringTraitTypeEnum.AttributeType;
  }

  @Override
  public String getId() {
    return "Sidereal";
  }

  public boolean equals(Object other) {
    return other instanceof SiderealCharacterType;
  }

  public int hashCode() {
    return 5;
  }
}