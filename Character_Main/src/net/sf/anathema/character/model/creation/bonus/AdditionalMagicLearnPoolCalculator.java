package net.sf.anathema.character.model.creation.bonus;

import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.main.model.charms.CharmsModelFetcher;
import net.sf.anathema.character.main.model.traits.GenericTraitCollectionFacade;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;

public class AdditionalMagicLearnPoolCalculator implements IAdditionalMagicLearnPoolCalculator {

  private final IAdditionalMagicLearnPool pool;
  private Hero hero;
  private int pointsSpent = 0;

  public AdditionalMagicLearnPoolCalculator(IAdditionalMagicLearnPool pool, Hero hero) {
    this.pool = pool;
    this.hero = hero;
  }

  @Override
  public boolean canSpendOn(IMagic magic) {
    IGenericTraitCollection traitCollection = new GenericTraitCollectionFacade(TraitModelFetcher.fetch(hero));
    if (!pool.isAllowedFor(traitCollection, magic)) {
      return false;
    }
    return pointsSpent < pool.getAdditionalMagicCount(traitCollection);
  }

  @Override
  public void spendPointsFor(IMagic magic) {
    if (canSpendOn(magic)) {
      if (magic instanceof ICharm && CharmsModelFetcher.fetch(hero).isAlienCharm((ICharm) magic)) {
        pointsSpent++;
      }
      pointsSpent++;
    }
  }

  @Override
  public int getPointsSpent() {
    return pointsSpent;
  }

  @Override
  public int getTotalPoints() {
    return pool.getAdditionalMagicCount(new GenericTraitCollectionFacade(TraitModelFetcher.fetch(hero)));
  }

  @Override
  public void reset() {
    pointsSpent = 0;
  }
}