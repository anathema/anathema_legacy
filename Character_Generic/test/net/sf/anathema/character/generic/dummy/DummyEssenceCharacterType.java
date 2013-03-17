package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitTypeEnum;
import net.sf.anathema.character.generic.type.ICharacterType;

public class DummyEssenceCharacterType implements ICharacterType {

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
    return "Dummy";
  }
}