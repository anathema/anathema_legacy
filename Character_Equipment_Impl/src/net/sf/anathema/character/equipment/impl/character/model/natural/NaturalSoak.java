package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class NaturalSoak implements IArmourStats {

  private final IGenericTrait stamina;
  private final ICharacterType characterType;

  public NaturalSoak(IGenericTrait stamina, ICharacterType characterType) {
    this.stamina = stamina;
    this.characterType = characterType;
  }

  public Integer getFatigue() {
    return null;
  }

  public Integer getHardness(HealthType type) {
    return null;
  }

  public Integer getMobilityPenalty() {
    return null;
  }

  public Integer getSoak(HealthType type) {
    if (type == HealthType.Aggravated) {
      return null;
    }
    if (!characterType.isExaltType() && type == HealthType.Lethal) {
      return 0;
    }
    return getExaltedSoak(type);
  }

  private Integer getExaltedSoak(HealthType type) {
    if (type == HealthType.Bashing) {
      return stamina.getCurrentValue();
    }
    return stamina.getCurrentValue() / 2;
  }

  public IIdentificate getName() {
    return new Identificate("NaturalSoak"); //$NON-NLS-1$
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}