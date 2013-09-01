package net.sf.anathema.character.db;

import net.sf.anathema.character.main.template.magic.AbilityFavoringType;
import net.sf.anathema.character.main.template.magic.FavoringTraitType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.RegisteredCharacterType;
import net.sf.anathema.framework.environment.dependencies.Weight;

@RegisteredCharacterType
@Weight(weight = 2)
public class DbCharacterType implements CharacterType {

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
    return new AbilityFavoringType();
  }

  @Override
  public String getId() {
    return "Dragon-Blooded";
  }

  public boolean equals(Object other) {
    return other instanceof DbCharacterType;
  }

  public int hashCode() {
    return 2;
  }
}