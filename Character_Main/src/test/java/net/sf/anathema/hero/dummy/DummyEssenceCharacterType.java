package net.sf.anathema.hero.dummy;

import net.sf.anathema.character.framework.type.CharacterType;

public class DummyEssenceCharacterType implements CharacterType {

  @Override
  public boolean isExaltType() {
    return false;
  }

  @Override
  public boolean isEssenceUser() {
    return true;
  }

  @Override
  public String getId() {
    return "Dummy";
  }

  public boolean equals(Object other) {
    return other instanceof DummyEssenceCharacterType;
  }
}