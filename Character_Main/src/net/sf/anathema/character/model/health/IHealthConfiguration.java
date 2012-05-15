package net.sf.anathema.character.model.health;

import net.sf.anathema.character.generic.health.HealthLevelType;

public interface IHealthConfiguration {

  int getHealthLevelTypeCount(HealthLevelType type);

  void addHealthLevelProvider(IHealthLevelProvider provider);

  void addPainToleranceProvider(IPainToleranceProvider provider);

  IOxBodyTechniqueArbitrator getOxBodyLearnArbitrator();

  int getPainToleranceLevel();
}