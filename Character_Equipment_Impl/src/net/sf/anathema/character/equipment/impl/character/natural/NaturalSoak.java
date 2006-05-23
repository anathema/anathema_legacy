package net.sf.anathema.character.equipment.impl.character.natural;

import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class NaturalSoak implements IArmour {

  private final IGenericTrait stamina;
  private final CharacterType characterType;

  public NaturalSoak(IGenericTrait stamina, CharacterType characterType) {
    this.stamina = stamina;
    this.characterType = characterType;
  }

  public Integer getFatigue() {
    return null;
  }

  public Integer getHardness(HealthType type) {
    return null;
  }

  private Integer getMortalSoak() {
    return 0;
  }

  private Integer getExaltedSoak(HealthType type) {
    if (type == HealthType.Bashing) {
      return stamina.getCurrentValue();
    }
    return stamina.getCurrentValue() / 2;
  }

  public Integer getMobilityPenalty() {
    return null;
  }

  public Integer getSoak(HealthType type) {
    if (type == HealthType.Aggravated) {
      return null;
    }
    if (characterType == CharacterType.MORTAL) {
      return getMortalSoak();
    }
    return getExaltedSoak(type);
  }

  public IIdentificate getName() {
    return new Identificate("Natural"); //$NON-NLS-1$
  }
}