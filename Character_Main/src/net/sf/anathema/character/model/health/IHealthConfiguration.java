package net.sf.anathema.character.model.health;

import net.sf.anathema.character.generic.health.HealthLevelType;

public interface IHealthConfiguration {

  public int getHealthLevelTypeCount(HealthLevelType type);

  public void addHealthLevelProvider(IHealthLevelProvider provider);

  public void addPainToleranceProvider(IPainToleranceProvider provider);

  public IOxBodyTechniqueArbitrator getOxBodyLearnArbitrator();

  public int getPainToleranceLevel();
}