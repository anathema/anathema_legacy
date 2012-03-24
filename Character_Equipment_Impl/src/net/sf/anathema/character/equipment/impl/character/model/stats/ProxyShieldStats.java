package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.util.IProxy;
import net.sf.anathema.lib.util.IIdentificate;

public class ProxyShieldStats extends AbstractStats implements IShieldStats, IProxy<IShieldStats> {

  private final IShieldStats delegate;
  private final MagicalMaterial material;

  public ProxyShieldStats(IShieldStats stats, MagicalMaterial material) {
    this.delegate = stats;
    this.material = material;
  }
  
  @Override
  public IShieldStats getUnderlying() {
    return this.delegate;
  }

  @Override
  public Integer getFatigue() {
    return delegate.getFatigue();
  }

  @Override
  public Integer getMobilityPenalty() {
    return delegate.getMobilityPenalty();
  }

  @Override
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
        && ObjectUtilities.equals(material, other.material);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  @Override
  public int getCloseCombatBonus() {
    return delegate.getCloseCombatBonus();
  }

  @Override
  public int getRangedCombatBonus() {
    return delegate.getRangedCombatBonus();
  }

  @Override
  public String getId() {
    return getName().getId();
  }
  
  @Override
  public Object[] getApplicableMaterials()
  {
	  return delegate.getApplicableMaterials();
  }

  @Override
  public boolean representsItemForUseInCombat() {
    return delegate.representsItemForUseInCombat();
  }
}
