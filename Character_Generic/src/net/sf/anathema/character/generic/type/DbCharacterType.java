package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterType
@Weight(weight = 2)
public class DbCharacterType implements ICharacterType {
  @Override
  public void accept(ICharacterTypeVisitor visitor) {
    visitor.visitDB();
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
    return FavoringTraitType.AbilityType;
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