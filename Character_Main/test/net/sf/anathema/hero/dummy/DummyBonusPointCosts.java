package net.sf.anathema.hero.dummy;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostsTto;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;

public class DummyBonusPointCosts implements BonusPointCosts {

  public MagicCreationCostsTto magicCostsTto = new MagicCreationCostsTto();

  private int getCharmCosts(Charm charm, CostAnalyzer costMapping) {
    return getCharmCosts(costMapping.isMagicFavored(charm), costMapping.getMartialArtsLevel(charm));
  }

  protected int getCharmCosts(boolean favored, MartialArtsLevel martialArtsLevel) {
    if (martialArtsLevel == null) {
      return favored ? 4 : 5;
    }
    if (martialArtsLevel.compareTo(MartialArtsLevel.Sidereal) < 0) {
      return favored ? 4 : 5;
    }
    throw new IllegalArgumentException("Sidereal Martial Arts shan't be learned at Hero Creation!");
  }

  @Override
  public CurrentRatingCosts getAbilityCosts(boolean favored) {
    if (favored) {
      return new FixedValueRatingCosts(1);
    }
    return new FixedValueRatingCosts(2);
  }

  @Override
  public MagicCreationCostsTto getMagicCosts() {
    return magicCostsTto;
  }

  @Override
  public int getAttributeCosts(ValuedTraitType trait) {
    return new FixedValueRatingCosts(4).getRatingCosts(trait.getCurrentValue());
  }

  @Override
  public CurrentRatingCosts getVirtueCosts() {
    return new FixedValueRatingCosts(3);
  }

  @Override
  public int getMaximumFreeVirtueRank() {
    return 3;
  }

  @Override
  public int getMaximumFreeAbilityRank() {
    return 3;
  }

  @Override
  public int getWillpowerCosts() {
    return 2;
  }

  @Override
  public int getFavoredSpecialtyDotsPerPoint() {
    return 2;
  }

  @Override
  public int getDefaultSpecialtyDotsPerPoint() {
    return 1;
  }

  @Override
  public CurrentRatingCosts getEssenceCost() {
    return new FixedValueRatingCosts(7);
  }
}