package net.sf.anathema.development.character;

import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DemoNaturalArmour implements IArmour {

  public Integer getFatigue() {
    return null;
  }

  public Integer getHardness(HealthType healthType) {
    return null;
  }

  public Integer getMobilityPenalty() {
    return null;
  }

  public Integer getSoak(HealthType type) {
    if (type == HealthType.Aggravated) {
      return null;
    }
    if (type == HealthType.Lethal) {
      return 1;
    }
    return 2;
  }

  public IIdentificate getName() {
    return new Identificate("Natural");
  }
}