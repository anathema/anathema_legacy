package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class NaturalSoak extends AbstractStats implements IArmourStats {

  private final IGenericTrait stamina;
  private final ICharacterType characterType;
  private final ICharacterModelContext context;
  
  private final String INVINCIBLE_ESSENCE_REINFORCEMENT = "Solar.InvincibleEssenceReinforcement";

  public NaturalSoak(ICharacterModelContext context) {
    this(context.getTraitCollection().getTrait(AttributeType.Stamina), context.getBasicCharacterContext().getCharacterType(), context);
  }
  public NaturalSoak(IGenericTrait stamina, ICharacterType characterType) {
    this(stamina, characterType, null);
  }
  protected NaturalSoak(IGenericTrait stamina, ICharacterType characterType, ICharacterModelContext context) {
    this.stamina = stamina;
    this.characterType = characterType;
    this.context = context;
  }

  public Integer getFatigue() {
    return null;
  }

  public Integer getHardness(HealthType type) {
    if (context == null) {
      return null;
    }
    
    int ierCount = context.getMagicCollection().getLearnCount(INVINCIBLE_ESSENCE_REINFORCEMENT);
    if (ierCount == 0 || context.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue() < 4) {
      return null;
    }
    else if (type == HealthType.Bashing || type == HealthType.Lethal) {
      return ierCount;
    }
    else {
      return null;
    }
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
	int ierCount;
	if (context == null) {
      ierCount = 0;
	}
	else {
      ierCount = context.getMagicCollection().getLearnCount(INVINCIBLE_ESSENCE_REINFORCEMENT);
	}
	
    if (type == HealthType.Bashing || ierCount > 0) {
      return stamina.getCurrentValue() + 3*ierCount;
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