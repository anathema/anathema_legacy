package net.sf.anathema.character.main.template.experience;

import net.sf.anathema.character.main.IGenericTraitCollection;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.hero.model.Hero;

public interface IExperiencePointCosts {

  CurrentRatingCosts getAbilityCosts(boolean favored);

  CurrentRatingCosts getAttributeCosts(boolean favored);

  int getCharmCosts(Charm charm, CostAnalyzer costMapping);

  CurrentRatingCosts getEssenceCosts();

  int getSpecialtyCosts(boolean favored);

  CurrentRatingCosts getVirtueCosts();

  CurrentRatingCosts getWillpowerCosts();

  int getSpellCosts(Spell spell, Hero hero, IGenericTraitCollection traitCollection);
}