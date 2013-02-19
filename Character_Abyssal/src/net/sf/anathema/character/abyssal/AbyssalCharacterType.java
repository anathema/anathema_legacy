package net.sf.anathema.character.abyssal;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterType
@Weight(weight = 4)
public class AbyssalCharacterType implements ICharacterType {
  @Override
  public void accept(ICharacterTypeVisitor visitor) {
    visitor.visitAbyssal();
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
  public boolean canAttuneToMalfeanMaterials() {
    return false;
  }

  @Override
  public String getId() {
    return "Abyssal";
  }

  public boolean equals(Object other) {
    return other instanceof AbyssalCharacterType;
  }

  public int hashCode() {
    return 4;
  }
}