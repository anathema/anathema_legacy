package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;

public class CheckAbilitiesFixture extends AbstractCheckTraitFixture {

  public boolean isFavored() {
    return ((IFavorableDefaultTrait) getDefaultTrait()).getFavorization().isFavored();
  }
  
  public boolean isCaste() {
    return ((IFavorableDefaultTrait) getDefaultTrait()).getFavorization().isCaste();
  }
  
  @Override
  protected ITraitType getTraitType() {
    return AbilityType.valueOf(traitType);
  }
}