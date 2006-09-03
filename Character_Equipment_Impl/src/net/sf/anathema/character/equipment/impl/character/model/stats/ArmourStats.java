package net.sf.anathema.character.equipment.impl.character.model.stats;

import java.util.Map;

import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;

public class ArmourStats extends AbstractStats implements IArmourStats {

  private final Map<HealthType, Integer> hardnessByHealthType;
  private final Map<HealthType, Integer> soakByHealthType;
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
    return hardnessByHealthType.get(type);
  }

  public Integer getMobilityPenalty() {
    return mobilityPenalty;
  }

  public Integer getSoak(HealthType type) {
    return soakByHealthType.get(type);
  }

  public void setFatigue(Integer fatigue) {
    this.fatigue = fatigue;
  }

  public void setMobilityPenalty(Integer mobilityPenalty) {
    this.mobilityPenalty = mobilityPenalty;
  }

  public void setSoak(HealthType healthType, Integer soak) {
    if (soak == null) {
      soakByHealthType.remove(healthType);
    }
    else {
      soakByHealthType.put(healthType, soak);
    }
  }

  public void setHardness(HealthType healthType, Integer hardness) {
    if (hardness == null) {
      hardnessByHealthType.remove(healthType);
    }
    else {
      hardnessByHealthType.put(healthType, hardness);
    }
  }
}