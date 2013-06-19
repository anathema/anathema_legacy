package net.sf.anathema.character.generic.template.experience;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.main.hero.Hero;

public interface IExperiencePointCosts {

  CurrentRatingCosts getAbilityCosts(boolean favored);

  CurrentRatingCosts getAttributeCosts(boolean favored);

  int getCharmCosts(ICharm charm, ICostAnalyzer costMapping);

  CurrentRatingCosts getEssenceCosts();

  int getSpecialtyCosts(boolean favored);

  CurrentRatingCosts getVirtueCosts();

  CurrentRatingCosts getWillpowerCosts();

  int getSpellCosts(ISpell spell, Hero hero, IGenericTraitCollection traitCollection);

  int getBackgroundCost();
}