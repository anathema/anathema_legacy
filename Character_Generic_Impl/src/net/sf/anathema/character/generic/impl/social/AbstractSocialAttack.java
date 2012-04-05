package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

import static net.sf.anathema.character.generic.impl.CharacterUtilities.getRoundUpDv;
import static net.sf.anathema.character.generic.impl.CharacterUtilities.getTotalValue;
import static net.sf.anathema.character.generic.traits.types.AttributeType.Charisma;
import static net.sf.anathema.character.generic.traits.types.AttributeType.Manipulation;

public abstract class AbstractSocialAttack implements ISocialCombatStats {

  private final IGenericTraitCollection collection;
  private IEquipmentModifiers equipmentModifiers;

  public AbstractSocialAttack(IGenericTraitCollection collection, IEquipmentModifiers equipmentModifiers) {
    this.collection = collection;
    this.equipmentModifiers = equipmentModifiers;
  }

  @Override
  public final int getDeceptionAttackValue() {
    return getSocialAttackValue(Manipulation);
  }

  @Override
  public final int getDeceptionMDV() {
    return getParryMdv(Manipulation);
  }

  @Override
  public final int getHonestyAttackValue() {
    return getSocialAttackValue(Charisma);
  }

  @Override
  public final int getHonestyMDV() {
    return getParryMdv(Charisma);
  }

  @Override
  public abstract ITraitType getName();

  private int getSocialAttackValue(AttributeType type) {
    return getTotalValue(collection, type, getName());
  }

  private int getParryMdv(AttributeType type) {
    return getRoundUpDv(collection, type, getName()) + equipmentModifiers.getMPDVMod();
  }
}