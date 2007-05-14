package net.sf.anathema.character.equipment.impl.character.model.natural;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class TotalArmour implements IArmourStats {

  private int fatigue;
  private int mobilityPenalty;
  private final Map<HealthType, Integer> soakByHealthType = new HashMap<HealthType, Integer>() {
    {
      for (HealthType healthType : HealthType.values()) {
        put(healthType, new Integer(0));
      }
    }
  };
  private final Map<HealthType, Integer> hardnessByHealthType = new HashMap<HealthType, Integer>() {
    {
      for (HealthType healthType : HealthType.values()) {
        put(healthType, new Integer(0));
      }
    }
  };

  public Integer getFatigue() {
    return fatigue;
  }

  public Integer getHardness(HealthType type) {
    return hardnessByHealthType.get(type);
  }

  public Integer getMobilityPenalty() {
    return mobilityPenalty;
  }

  public Integer getSoak(HealthType type) {
    return soakByHealthType.get(type);
  }

  public IIdentificate getName() {
    return new Identificate("TotalArmour"); //$NON-NLS-1$
  }

  public void addArmour(IArmourStats armour) {
    fatigue = getIncrementedValue(fatigue, armour.getFatigue());
    mobilityPenalty = getIncrementedValue(mobilityPenalty, armour.getMobilityPenalty());
    for (HealthType healthType : HealthType.values()) {
      soakByHealthType.put(
          healthType,
          getIncrementedValue(soakByHealthType.get(healthType), armour.getSoak(healthType)));
      hardnessByHealthType.put(healthType, getIncrementedValue(
          hardnessByHealthType.get(healthType),
          armour.getHardness(healthType)));
    }
  }

  private int getIncrementedValue(int value, Integer increment) {
    return increment == null ? value : value + increment;
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}