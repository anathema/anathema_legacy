package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractCombatStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.util.Identified;

public class NaturalSoak extends AbstractCombatStats implements IArmourStats {

  private final IGenericTrait stamina;
  private final ICharacterType characterType;
  private final ICharacterModelContext context;
  
  private static final String INVINCIBLE_ESSENCE_REINFORCEMENT = "Solar.InvincibleEssenceReinforcement";

  private static final String SCAR_WRIT_SAGA_SHIELD = "Infernal.ScarWritSagaShield";
  
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

  @Override
  public Integer getFatigue() {
    return null;
  }

  @Override
  public Integer getHardness(HealthType type) {
    int ierCount;
    int swssCount;
    int essence;
    
    if (context == null) {
      return null;
    }
    else {
      ierCount = context.getMagicCollection().getLearnCount(INVINCIBLE_ESSENCE_REINFORCEMENT);
      swssCount = context.getMagicCollection().getLearnCount(SCAR_WRIT_SAGA_SHIELD);
    }
    
    if ((ierCount == 0 && swssCount == 0) || (ierCount > 0 && context.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue() < 4)) {
      return null;
    }
    else if ((ierCount > 0) && (type == HealthType.Bashing || type == HealthType.Lethal)) {
      return ierCount;
    }
    else if ((swssCount == 1 || swssCount == 2) && (type == HealthType.Bashing)) {
      return stamina.getCurrentValue();
    }
    else if ((swssCount == 3) && (type == HealthType.Bashing || type == HealthType.Lethal)) {
      essence = context.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
      return (stamina.getCurrentValue() + essence);
    }
    else {
      return null;
    }

  }

  @Override
  public Integer getMobilityPenalty() {
    return null;
  }

  @Override
  public Integer getSoak(HealthType type) {
    if (type == HealthType.Aggravated) {
      return null;
    }
    if (!characterType.isEssenceUser() && type == HealthType.Lethal) {
      return 0;
    }
    return getExaltedSoak(type);
  }

  private Integer getExaltedSoak(HealthType type) {
  	int ierCount;
  	int swssCount;
  	int essence;
  	
	if (context == null) {
		ierCount = 0;
		swssCount = 0;
		essence = 0;
	}
	else {	
  	  ierCount = context.getMagicCollection().getLearnCount(INVINCIBLE_ESSENCE_REINFORCEMENT);
      swssCount = context.getMagicCollection().getLearnCount(SCAR_WRIT_SAGA_SHIELD);
      essence = context.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
	}
	
    if (ierCount > 0) { 
      return (stamina.getCurrentValue() + 3*ierCount);
    }
    else if ((swssCount == 1) && (type == HealthType.Lethal)) {
    	return stamina.getCurrentValue();
    }
    else if (swssCount >= 2) {
    	return (stamina.getCurrentValue() + essence);
    }
    else if (type == HealthType.Lethal) {
        return (stamina.getCurrentValue() / 2);
    }
    else
        return stamina.getCurrentValue();
    
  }

  @Override
  public Identified getName() {
    return new Identificate("NaturalSoak"); //$NON-NLS-1$
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}