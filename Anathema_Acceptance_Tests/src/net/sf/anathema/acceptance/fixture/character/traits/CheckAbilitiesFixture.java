package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;

public class CheckAbilitiesFixture extends AbstractCheckTraitFixture {

  public boolean isFavored() {
    return ((IFavorableModifiableTrait) getTrait()).getFavorization().isFavored();
  }
  
  public boolean isCaste() {
    return ((IFavorableModifiableTrait) getTrait()).getFavorization().isCaste();
  }
  
  @Override
  protected ITraitType getTraitType() {
    return AbilityType.valueOf(traitType);
  }
}