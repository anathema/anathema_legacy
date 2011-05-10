package net.sf.anathema.character.abyssal.equipment;

import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractStats;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class SecondEditionFangStats extends AbstractStats implements IWeaponStats {

  public int getAccuracy() {
    return 0;
  }

  public int getDamage() {
    return 0;
  }

  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  public HealthType getDamageType() {
    return HealthType.Lethal;
  }

  public Integer getDefence() {
    return 0;
  }

  public Integer getRange() {
    return null;
  }

  public Integer getRate() {
    return null;
  }

  public int getSpeed() {
    return 6;
  }

  public IIdentificate[] getTags() {
    return new IIdentificate[]{WeaponTag.ClinchEnhancer, WeaponTag.Natural, WeaponTag.Piercing};
  }

  public ITraitType getTraitType() {
    return AbilityType.MartialArts;
  }

  public boolean inflictsNoDamage() {
    return false;
  }

  public boolean isRangedCombat() {
    return false;
  }

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