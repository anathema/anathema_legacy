package net.sf.anathema.character.generic.impl.template.experience;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.hero.model.Hero;

public class DefaultExperienceCosts implements IExperiencePointCosts {

  @Override
  public CurrentRatingCosts getAbilityCosts(boolean favored) {
    if (favored) {
      return new MultiplyRatingCosts(2, 3, -1);
    }
    return new MultiplyRatingCosts(2, 3);
  }

  @Override
  public CurrentRatingCosts getAttributeCosts(boolean favored) {
    return favored ? new MultiplyRatingCosts(3) : new MultiplyRatingCosts(4);
  }

  @Override
  public int getSpellCosts(ISpell spell, Hero hero, IGenericTraitCollection traitCollection) {
    return getCharmCosts(spell.isFavored(hero, traitCollection), null);
  }

  @Override
  public int getCharmCosts(ICharm charm, ICostAnalyzer costMapping) {
    return getCharmCosts(costMapping.isMagicFavored(charm), costMapping.getMartialArtsLevel(charm));
  }

  protected int getCharmCosts(boolean favored, MartialArtsLevel level) {
    if (level == MartialArtsLevel.Sidereal) {
      return favored ? 13 : 15;
    }
    return favored ? 8 : 10;
  }

  @Override
  public int getBackgroundCost() {
    return 3;
  }

  @Override
  public CurrentRatingCosts getEssenceCosts() {
    return new MultiplyRatingCosts(8);
  }

  @Override
  public int getSpecialtyCosts(boolean favored) {
    return 3;
  }

  @Override
  public CurrentRatingCosts getVirtueCosts() {
    return new MultiplyRatingCosts(3);
  }

  @Override
  public CurrentRatingCosts getWillpowerCosts() {
    return new MultiplyRatingCosts(2);
  }
}