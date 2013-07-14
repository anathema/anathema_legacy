package net.sf.anathema.character.main.template.experience;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.advance.CostAnalyzer;

public interface IExperiencePointCosts {

  CurrentRatingCosts getAbilityCosts(boolean favored);

  CurrentRatingCosts getAttributeCosts(boolean favored);

  CurrentRatingCosts getEssenceCosts();

  int getSpecialtyCosts(boolean favored);

  CurrentRatingCosts getVirtueCosts();

  CurrentRatingCosts getWillpowerCosts();

  int getCharmCosts(Charm charm, CostAnalyzer costAnalyzer);
}