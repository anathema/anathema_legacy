package net.sf.anathema.character.generic.impl.template.experience;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;

public class DefaultExperienceCosts implements IExperiencePointCosts {

  @Override
  public ICurrentRatingCosts getAbilityCosts(boolean favored) {
    if (favored) {
      return new MultiplyRatingCosts(2, 3, -1);
    }
    return new MultiplyRatingCosts(2, 3);
  }

  @Override
  public ICurrentRatingCosts getAttributeCosts(boolean favored)
  {
	  return favored ? new MultiplyRatingCosts(3) : new MultiplyRatingCosts(4);
  }

  @Override
  public int getSpellCosts(ISpell spell, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    return getCharmCosts(spell.isFavored(basicCharacter, traitCollection), null);
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
  public ICurrentRatingCosts getEssenceCosts() {
    return new MultiplyRatingCosts(8);
  }

  @Override
  public int getSpecialtyCosts(boolean favored) {
    return 3;
  }

  @Override
  public ICurrentRatingCosts getVirtueCosts() {
    return new MultiplyRatingCosts(3);
  }

  @Override
  public ICurrentRatingCosts getWillpowerCosts() {
    return new MultiplyRatingCosts(2);
  }
}