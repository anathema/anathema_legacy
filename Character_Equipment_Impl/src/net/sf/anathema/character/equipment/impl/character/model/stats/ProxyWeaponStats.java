package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.IIdentificate;

public class ProxyWeaponStats implements IWeaponStats {

  private final IWeaponStats delegate;
  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public ProxyWeaponStats(IWeaponStats stats, MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.delegate = stats;
    this.material = material;
    this.ruleSet = ruleSet;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ProxyWeaponStats)) {
      return false;
    }
    ProxyWeaponStats other = (ProxyWeaponStats) obj;
    return ObjectUtilities.equals(delegate, other.delegate)
        && ObjectUtilities.equals(material, other.material)
        && ObjectUtilities.equals(ruleSet, other.ruleSet);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public int getAccuracy() {
    return delegate.getAccuracy();
  }

  public int getDamage() {
    return delegate.getDamage();
  }

  public ITraitType getDamageTraitType() {
    return delegate.getDamageTraitType();
  }

  public HealthType getDamageType() {
    return delegate.getDamageType();
  }

  public Integer getDefence() {
    return delegate.getDefence();
  }

  public Integer getRange() {
    return delegate.getRange();
  }

  public Integer getRate() {
    return delegate.getRate();
  }

  public int getSpeed() {
    return delegate.getSpeed();
  }

  public IIdentificate[] getTags() {
    return delegate.getTags();
  }

  public ITraitType getTraitType() {
    return delegate.getTraitType();
  }

  public boolean inflictsNoDamage() {
    return delegate.inflictsNoDamage();
  }

  public IIdentificate getName() {
    return delegate.getName();
  }
}