package net.sf.anathema.dummy.character.magic;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
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

  public CircleType getCircleType() {
    return circleType;
  }

  public String getId() {
    return id;
  }

  public String getPage() {
    throw new NotYetImplementedException();
  }

  public IExaltedSourceBook getSource() {
    throw new NotYetImplementedException();
  }

  public IExaltedSourceBook getSource(IExaltedEdition edition) {
    throw new NotYetImplementedException();
  }

  public ICostList getTemporaryCost() {
    throw new NotYetImplementedException();
  }

  public void accept(IMagicVisitor visitor) {
    visitor.visitSpell(this);
  }

  public boolean isFavored(IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    return ((IFavorableGenericTrait) traitCollection.getTrait(AbilityType.Occult)).isCasteOrFavored();
  }

  public String getTarget() {
    throw new NotYetImplementedException();
  }
}