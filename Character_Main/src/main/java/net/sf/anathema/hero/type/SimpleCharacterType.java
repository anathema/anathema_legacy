package net.sf.anathema.hero.type;

import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.hero.template.magic.FavoringTraitType;

public class SimpleCharacterType implements CharacterType {

  public String id;
  public boolean exalt;
  public boolean essenceUser;
  public FavoringTraitType favors;

  @Override
  public boolean isExaltType() {
    return exalt;
  }

  @Override
  public boolean isEssenceUser() {
    return essenceUser;
  }

  @Override
  public FavoringTraitType getFavoringTraitType() {
    return favors;
  }

  @Override
  public String getId() {
    return id;
  }
}