package net.sf.anathema.character.infernal;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterType
@Weight(weight = 6)
public class InfernalCharacterType implements ICharacterType {

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
    return FavoringTraitType.YoziType;
  }

  @Override
  public String getId() {
    return "Infernal";
  }

  public boolean equals(Object other) {
    return other instanceof InfernalCharacterType;
  }

  public int hashCode() {
    return 6;
  }
}