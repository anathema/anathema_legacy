package net.sf.anathema.character.main.social;

import net.sf.anathema.character.main.IGenericTraitCollection;
import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.main.util.CharacterUtilities;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AttributeType;

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
  public abstract TraitType getName();

}