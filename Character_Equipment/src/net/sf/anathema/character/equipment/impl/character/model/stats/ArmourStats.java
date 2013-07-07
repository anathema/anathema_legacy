package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.main.equipment.weapon.IArmourStats;
import net.sf.anathema.hero.health.HealthType;

import java.util.HashMap;
import java.util.Map;

public class ArmourStats extends AbstractCombatStats implements IArmourStats {

  private final Map<String, Integer> hardnessByHealthType = new HashMap<>();
  private final Map<String, Integer> soakByHealthType = new HashMap<>();
  private Integer fatigue;
  private Integer mobilityPenalty;

  @Override
  public Integer getFatigue() {
    return fatigue;
  }

  @Override
  public Integer getHardness(HealthType type) {
    return hardnessByHealthType.get(type.name());
  }

  @Override
  public Integer getMobilityPenalty() {
    return mobilityPenalty;
  }

  @Override
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
    } else {
      soakByHealthType.put(healthType.name(), soak);
    }
  }

  public void setHardness(HealthType healthType, Integer hardness) {
    if (hardness == null) {
      hardnessByHealthType.remove(healthType.name());
    } else {
      hardnessByHealthType.put(healthType.name(), hardness);
    }
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}