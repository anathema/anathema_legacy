package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public abstract class AbstractSocialAttack implements ISocialCombatStats {

  private final IGenericCharacter character;

  public AbstractSocialAttack(IGenericCharacter character) {
    this.character = character;
  }

  public final int getDeceptionAttackValue() {
    return CharacterUtilties.getTotalValue(character, AttributeType.Manipulation, getName());
  }

  public final int getDeceptionMDV() {
    return CharacterUtilties.getRoundUpDv(character, AttributeType.Manipulation, getName());
  }

  public final int getHonestyAttackValue() {
    return CharacterUtilties.getTotalValue(character, AttributeType.Charisma, getName());
  }

  public final int getHonestyMDV() {
    return CharacterUtilties.getRoundUpDv(character, AttributeType.Charisma, getName());
  }

  public abstract ITraitType getName();
}