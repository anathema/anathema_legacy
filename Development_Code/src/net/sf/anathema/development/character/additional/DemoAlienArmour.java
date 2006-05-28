package net.sf.anathema.development.character.additional;

import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DemoAlienArmour implements IArmour {
  public Integer getFatigue() {
    return 2;
  }

  public Integer getHardness(HealthType healthType) {
    if (healthType == HealthType.Bashing) {
      return 12;
    }
    return 3;
  }

  public Integer getMobilityPenalty() {
    return -4;
  }

  public Integer getSoak(HealthType type) {
    if (type == HealthType.Aggravated) {
      return 10;
    }
    if (type == HealthType.Lethal) {
      return 1;
    }
    return 2;
  }

  public IIdentificate getName() {
    return new Identificate("Alien");
  }
}