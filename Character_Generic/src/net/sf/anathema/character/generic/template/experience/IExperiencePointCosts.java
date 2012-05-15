package net.sf.anathema.character.generic.template.experience;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;

public interface IExperiencePointCosts {

  ICurrentRatingCosts getAbilityCosts(boolean favored);

  ICurrentRatingCosts getAttributeCosts(boolean favored);

  int getCharmCosts(ICharm charm, ICostAnalyzer costMapping);

  ICurrentRatingCosts getEssenceCosts();

  int getSpecialtyCosts(boolean favored);

  ICurrentRatingCosts getVirtueCosts();

  ICurrentRatingCosts getWillpowerCosts();

  int getSpellCosts(ISpell spell, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection);

  int getBackgroundCost();
}