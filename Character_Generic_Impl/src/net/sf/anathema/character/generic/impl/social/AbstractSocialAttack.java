package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

import net.sf.anathema.character.generic.impl.CharacterUtilities;

public abstract class AbstractSocialAttack implements ISocialCombatStats {

  private final IGenericTraitCollection collection;
  private ICharacterStatsModifiers equipmentModifiers;

  public AbstractSocialAttack(IGenericTraitCollection collection, ICharacterStatsModifiers equipmentModifiers) {
    this.collection = collection;
    this.equipmentModifiers = equipmentModifiers;
  }

  @Override
  public final int getDeceptionAttackValue() {
    return CharacterUtilities.getSocialAttackValue(collection, AttributeType.Manipulation, getName());
  }

  @Override
  public final int getDeceptionMDV() {
    return CharacterUtilities.getParryMdv(collection, equipmentModifiers, AttributeType.Manipulation, getName());
  }

  @Override
  public final int getHonestyAttackValue() {
    return CharacterUtilities.getSocialAttackValue(collection, AttributeType.Charisma, getName());
  }

  @Override
  public final int getHonestyMDV() {
    return CharacterUtilities.getParryMdv(collection, equipmentModifiers, AttributeType.Charisma, getName());
  }

  @Override
  public abstract ITraitType getName();

}