package net.sf.anathema.character.main.model.health;

import net.sf.anathema.character.main.health.HealthLevelType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface HealthModel {

  Identifier ID = new SimpleIdentifier("Health");

  int getHealthLevelTypeCount(HealthLevelType type);

  void addHealthLevelProvider(IHealthLevelProvider provider);

  void addPainToleranceProvider(IPainToleranceProvider provider);

  OxBodyTechniqueArbitrator getOxBodyLearnArbitrator();

  int getPainToleranceLevel();
}