package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public abstract class AbstractSocialAttack implements ISocialCombatStats {

  private final IGenericTraitCollection collection;

  public AbstractSocialAttack(IGenericTraitCollection collection) {
    this.collection = collection;
  }

  public final int getDeceptionAttackValue() {
    return CharacterUtilties.getTotalValue(collection, AttributeType.Manipulation, getName());
  }

  public final int getDeceptionMDV() {
    return CharacterUtilties.getRoundUpDv(collection, AttributeType.Manipulation, getName());
  }

  public final int getHonestyAttackValue() {
    return CharacterUtilties.getTotalValue(collection, AttributeType.Charisma, getName());
  }

  public final int getHonestyMDV() {
    return CharacterUtilties.getRoundUpDv(collection, AttributeType.Charisma, getName());
  }

  public abstract ITraitType getName();
}