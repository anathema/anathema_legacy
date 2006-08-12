package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;

public class CheckAbilitiesFixture extends AbstractCheckTraitFixture {

  public boolean isFavored() {
    return ((IFavorableTrait) getTrait()).getFavorization().isFavored();
  }
  
  public boolean isCaste() {
    return ((IFavorableTrait) getTrait()).getFavorization().isCaste();
  }
  
  @Override
  protected ITraitType getTraitType() {
    return AbilityType.valueOf(traitType);
  }
}