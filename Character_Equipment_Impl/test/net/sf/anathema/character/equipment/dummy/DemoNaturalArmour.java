package net.sf.anathema.character.equipment.dummy;

import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractCombatStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.IIdentificate;

public class DemoNaturalArmour extends AbstractCombatStats implements IArmourStats {

  private final int bashingSoak;
  private final int lethalSoak;
  private final IIdentificate name;

  public DemoNaturalArmour(IIdentificate identificate, int bashingSoak, int lethalSoak) {
    this.name = identificate;
    this.bashingSoak = bashingSoak;
    this.lethalSoak = lethalSoak;
  }

  @Override
  public Integer getFatigue() {
    return null;
  }

  @Override
  public Integer getHardness(HealthType healthType) {
    return null;
  }

  @Override
  public Integer getMobilityPenalty() {
    return null;
  }

  @Override
  public Integer getSoak(HealthType type) {
    if (type == HealthType.Aggravated) {
      return null;
    }
    if (type == HealthType.Lethal) {
      return lethalSoak;
    }
    return bashingSoak;
  }

  @Override
  public IIdentificate getName() {
    return name;
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}