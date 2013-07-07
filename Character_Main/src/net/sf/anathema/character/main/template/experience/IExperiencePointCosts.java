package net.sf.anathema.character.main.template.experience;

import net.sf.anathema.character.main.IGenericTraitCollection;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.ISpell;
import net.sf.anathema.hero.model.Hero;

public interface IExperiencePointCosts {

  CurrentRatingCosts getAbilityCosts(boolean favored);

  CurrentRatingCosts getAttributeCosts(boolean favored);

  int getCharmCosts(ICharm charm, ICostAnalyzer costMapping);

  CurrentRatingCosts getEssenceCosts();

  int getSpecialtyCosts(boolean favored);

  CurrentRatingCosts getVirtueCosts();

  CurrentRatingCosts getWillpowerCosts();

  int getSpellCosts(ISpell spell, Hero hero, IGenericTraitCollection traitCollection);
}