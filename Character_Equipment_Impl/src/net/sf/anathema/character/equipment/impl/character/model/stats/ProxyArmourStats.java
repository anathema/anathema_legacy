package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.util.IIdentificate;

public class ProxyArmourStats implements IArmourStats {

  private final IArmourStats delegate;
  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public ProxyArmourStats(IArmourStats stats, MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.delegate = stats;
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public Integer getFatigue() {
    Integer fatigue = delegate.getFatigue();
    return fatigue;
  }

  public Integer getHardness(HealthType type) {
    return delegate.getHardness(type);
  }

  public Integer getMobilityPenalty() {
    return delegate.getMobilityPenalty();
  }

  public Integer getSoak(HealthType type) {
    return delegate.getSoak(type);
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
}