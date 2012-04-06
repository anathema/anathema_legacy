package net.sf.anathema.character.abyssal.equipment;

import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractCombatStats;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class FangStats extends AbstractCombatStats implements IWeaponStats {

  @Override
  public int getAccuracy() {
    return 0;
  }

  @Override
  public int getDamage() {
    return 0;
  }
  
  @Override
  public int getMinimumDamage() {
	return 1;
  }
  
  @Override
  public int getMobilityPenalty() {
	return 0;
  }

  @Override
  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  @Override
  public HealthType getDamageType() {
    return HealthType.Lethal;
  }

  @Override
  public Integer getDefence() {
    return 0;
  }

  @Override
  public Integer getRange() {
    return null;
  }

  @Override
  public Integer getRate() {
    return null;
  }

  @Override
  public int getSpeed() {
    return 6;
  }

  @Override
  public IIdentificate[] getTags() {
    return new IIdentificate[]{WeaponTag.ClinchEnhancer, WeaponTag.Natural, WeaponTag.Piercing};
  }

  @Override
  public ITraitType getTraitType() {
    return AbilityType.MartialArts;
  }

  @Override
  public boolean inflictsNoDamage() {
    return false;
  }

  @Override
  public boolean isRangedCombat() {
    return false;
  }

  @Override
  public IIdentificate getName() {
    return new Identificate("Abyssal.Fangs"); //$NON-NLS-1$
  }

  @Override
  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[] { this };
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}