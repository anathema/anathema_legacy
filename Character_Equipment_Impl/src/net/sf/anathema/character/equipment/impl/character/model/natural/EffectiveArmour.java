package net.sf.anathema.character.equipment.impl.character.model.natural;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractCombatStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class EffectiveArmour extends AbstractCombatStats implements IArmourStats {

  private int fatigue;
  private int mobilityPenalty;
  private final Map<HealthType, Integer> naturalSoakByHealthType = new HashMap<HealthType, Integer>() {
    {
      for (HealthType healthType : HealthType.values()) {
        put(healthType, 0);
      }
    }
  };
  private final Map<HealthType, Integer> equipmentSoakByHealthType = new HashMap<HealthType, Integer>() {
    {
      for (HealthType healthType : HealthType.values()) {
        put(healthType, 0);
      }
    }
  };
  private final Map<HealthType, Integer> hardnessByHealthType = new HashMap<HealthType, Integer>() {
    {
      for (HealthType healthType : HealthType.values()) {
        put(healthType, 0);
      }
    }
  };

  @Override
  public Integer getFatigue() {
    return fatigue;
  }

  @Override
  public Integer getHardness(HealthType type) {
    return hardnessByHealthType.get(type);
  }

  @Override
  public Integer getMobilityPenalty() {
    return mobilityPenalty;
  }

  @Override
  public Integer getSoak(HealthType type) {
    return naturalSoakByHealthType.get(type) + equipmentSoakByHealthType.get(type);
  }

  @Override
  public IIdentificate getName() {
    return new Identificate("EffectiveArmour"); //$NON-NLS-1$
  }

  public void addArmour(IArmourStats armour) {
    if (armour instanceof NaturalSoak) handleNaturalArmour(armour);
    else handleEquipmentArmour(armour);
  }

  private void handleNaturalArmour(IArmourStats armour) {
    for (HealthType healthType : HealthType.values()) {
      naturalSoakByHealthType.put(healthType,
              getIncrementValue(naturalSoakByHealthType.get(healthType), armour.getSoak(healthType)));
    }
  }

  private void handleEquipmentArmour(IArmourStats armour) {
    fatigue = getHighestValue(fatigue, armour.getFatigue());
    modifyMobilityPenalty(armour.getMobilityPenalty());
    for (HealthType healthType : HealthType.values()) {
      equipmentSoakByHealthType.put(healthType,
              getHighestValue(equipmentSoakByHealthType.get(healthType), armour.getSoak(healthType)));
      hardnessByHealthType.put(healthType,
              getHighestValue(hardnessByHealthType.get(healthType), armour.getHardness(healthType)));
    }
  }

  public void modifyMobilityPenalty(Integer amount) {
    if (amount != null) mobilityPenalty += amount;
  }

  private int getIncrementValue(int value, Integer increment) {
    return increment == null ? value : value + increment;
  }

  private int getHighestValue(int currentValue, Integer newValue) {
    if (newValue == null) return currentValue;
    return newValue > currentValue ? newValue : currentValue;
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}