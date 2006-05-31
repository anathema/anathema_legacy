package net.sf.anathema.character.impl.testing;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummySpell implements ISpell {

  public CircleType getCircleType() {
    throw new NotYetImplementedException();
  }

  public String getId() {
    throw new NotYetImplementedException();
  }

  public String getPage() {
    throw new NotYetImplementedException();
  }

  public IPermanentCostList getPermanentCost() {
    throw new NotYetImplementedException();
  }

  public IMagicSource getSource() {
    throw new NotYetImplementedException();
  }

  public IMagicSource getSource(IExaltedEdition edition) {
    throw new NotYetImplementedException();
  }

  public ICostList getTemporaryCost() {
    throw new NotYetImplementedException();
  }

  public void accept(IMagicVisitor visitor) {
    visitor.visitSpell(this);
  }

  public boolean isFavored(
      IBasicCharacterData basicCharacter,
      IGenericTraitCollection traitCollection,
      FavoringTraitType type) {
    return ((IFavorableGenericTrait) traitCollection.getTrait(AbilityType.Occult)).isCasteOrFavored();
  }

  public String getTarget() {
    throw new NotYetImplementedException();
  }
}