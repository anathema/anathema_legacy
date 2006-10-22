package net.sf.anathema.character.equipment.impl.character.model.stats;

import java.util.Map;

import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;

public class ArmourStats extends AbstractStats implements IArmourStats {

  private final Map<String, Integer> hardnessByHealthType;
  private final Map<String, Integer> soakByHealthType;
  private Integer fatigue;
  private Integer mobilityPenalty;

  public ArmourStats(ICollectionFactory collectionFactory) {
    hardnessByHealthType = collectionFactory.createHashMap();
    soakByHealthType = collectionFactory.createHashMap();
  }

  public Integer getFatigue() {
    return fatigue;
  }

  public Integer getHardness(HealthType type) {
    return hardnessByHealthType.get(type.name());
  }

  public Integer getMobilityPenalty() {
    return mobilityPenalty;
  }

  public Integer getSoak(HealthType type) {
    return soakByHealthType.get(type.name());
  }

  public void setFatigue(Integer fatigue) {
    this.fatigue = fatigue;
  }

  public void setMobilityPenalty(Integer mobilityPenalty) {
    this.mobilityPenalty = mobilityPenalty;
  }

  public void setSoak(HealthType healthType, Integer soak) {
    if (soak == null) {
      soakByHealthType.remove(healthType.name());
    }
    else {
      soakByHealthType.put(healthType.name(), soak);
    }
  }

  public void setHardness(HealthType healthType, Integer hardness) {
    if (hardness == null) {
      hardnessByHealthType.remove(healthType.name());
    }
    else {
      hardnessByHealthType.put(healthType.name(), hardness);
    }
  }
}