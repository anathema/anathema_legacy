package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.util.IIdentificate;

public class ProxyShieldStats implements IShieldStats {

  private final IShieldStats delegate;
  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public ProxyShieldStats(IShieldStats stats, MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.delegate = stats;
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public Integer getFatigue() {
    return delegate.getFatigue();
  }

  public Integer getMobilityPenalty() {
    return delegate.getMobilityPenalty();
  }

  public IIdentificate getName() {
    return delegate.getName();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ProxyShieldStats)) {
      return false;
    }
    ProxyShieldStats other = (ProxyShieldStats) obj;
    return ObjectUtilities.equals(delegate, other.delegate)
    && ObjectUtilities.equals(material, other.material)
    && ObjectUtilities.equals(ruleSet, other.ruleSet);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public int getCloseCombatBonus() {
    return delegate.getCloseCombatBonus();
  }

  public int getRangedCombatBonus() {
    return delegate.getRangedCombatBonus();
  }
}