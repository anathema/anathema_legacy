package net.sf.anathema.hero.type;

import net.sf.anathema.character.framework.type.CharacterType;

public class SimpleCharacterType implements CharacterType {

  public String id;
  public boolean exalt;
  public boolean essenceUser;

  @Override
  public boolean isExaltType() {
    return exalt;
  }

  @Override
  public boolean isEssenceUser() {
    return essenceUser;
  }

  @Override
  public String getId() {
    return id;
  }
}