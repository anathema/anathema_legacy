package net.sf.anathema.character.main.testing.dummy.magic;

import net.sf.anathema.character.main.magic.model.magic.IMagicVisitor;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.magic.model.magic.ICostList;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.rules.IExaltedSourceBook;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummySpell implements ISpell {

  public DummySpell() {
    // nothing to do
  }
  @Override
  public CircleType getCircleType() {
    return null;
  }

  @Override
  public String getId() {
    return null;
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
  public boolean isFavored(Hero hero) {
    return TraitModelFetcher.fetch(hero).getTrait(AbilityType.Occult).isCasteOrFavored();
  }

  @Override
  public String getTarget() {
    throw new NotYetImplementedException();
  }
}