package net.sf.anathema.hero.health.model;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.health.HealthLevelType;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface HealthModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Health");

  int getHealthLevelTypeCount(HealthLevelType type);

  void addHealthLevelProvider(IHealthLevelProvider provider);

  void addPainToleranceProvider(IPainToleranceProvider provider);

  int getPainToleranceLevel();

  TraitType[] getToughnessControllingTraitTypes();
}