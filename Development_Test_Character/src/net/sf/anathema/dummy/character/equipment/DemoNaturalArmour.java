package net.sf.anathema.dummy.character.equipment;

import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DemoNaturalArmour implements IArmourStats {

  private final int bashingSoak;
  private final int lethalSoak;
  private final IIdentificate name;

  public DemoNaturalArmour() {
    this(new Identificate("Natural"), 2, 1); //$NON-NLS-1$
  }

  public DemoNaturalArmour(IIdentificate identificate, int bashingSoak, int lethalSoak) {
    this.name = identificate;
    this.bashingSoak = bashingSoak;
    this.lethalSoak = lethalSoak;
  }

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
      return lethalSoak;
    }
    return bashingSoak;
  }

  public IIdentificate getName() {
    return name;
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}