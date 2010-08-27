package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.FatigueModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.HardnessModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.IArmourStatsModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.MobilityPenaltyModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.SoakModification;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.util.IProxy;
import net.sf.anathema.lib.util.IIdentificate;

public class ProxyArmourStats implements IArmourStats, IProxy<IArmourStats> {

  private final IArmourStats delegate;
  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public ProxyArmourStats(IArmourStats stats, MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.delegate = stats;
    this.material = material;
    this.ruleSet = ruleSet;
  }
  
  public IArmourStats getUnderlying() {
    return this.delegate;
  }

  public Integer getFatigue() {
    Integer fatigue = delegate.getFatigue();
    return getModifiedValue(new FatigueModification(material), fatigue);
  }

  public Integer getHardness(HealthType type) {
    Integer hardness = delegate.getHardness(type);
    return getModifiedValue(new HardnessModification(material, ruleSet), hardness);
  }

  private Integer getModifiedValue(IArmourStatsModification modification, Integer original) {
    if (original == null) {
      return null;
    }
    return modification.getModifiedValue(original);
  }

  public Integer getMobilityPenalty() {
    Integer mobilityPenalty = delegate.getMobilityPenalty();
    return getModifiedValue(new MobilityPenaltyModification(material), mobilityPenalty);
  }

  public Integer getSoak(HealthType type) {
    Integer soak = delegate.getSoak(type);
    return getModifiedValue(new SoakModification(material), soak);
  }

  public IIdentificate getName() {
    return delegate.getName();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ProxyArmourStats)) {
      return false;
    }
    ProxyArmourStats other = (ProxyArmourStats) obj;
    return ObjectUtilities.equals(delegate, other.delegate)
        && ObjectUtilities.equals(material, other.material)
        && ObjectUtilities.equals(ruleSet, other.ruleSet);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}
