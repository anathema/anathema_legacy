package net.sf.anathema.character.generic.dummy.magic;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummySpell implements ISpell {

  private CircleType circleType = null;
  private String id;

  public DummySpell() {
    // nothing to do
  }

  public DummySpell(String id) {
    this.id = id;
  }

  public DummySpell(CircleType type) {
    this.circleType = type;
  }

  public void setCircleType(CircleType type) {
    this.circleType = type;
  }

  @Override
  public CircleType getCircleType() {
    return circleType;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public IExaltedSourceBook[] getSources() {
    throw new NotYetImplementedException();
  }
  
  @Override
  public IExaltedSourceBook getPrimarySource() {
	throw new NotYetImplementedException();
  }

  @Override
  public ICostList getTemporaryCost() {
    throw new NotYetImplementedException();
  }

  @Override
  public void accept(IMagicVisitor visitor) {
    visitor.visitSpell(this);
  }

  @Override
  public boolean isFavored(IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    return ((IFavorableGenericTrait) traitCollection.getTrait(AbilityType.Occult)).isCasteOrFavored();
  }

  @Override
  public String getTarget() {
    throw new NotYetImplementedException();
  }
}