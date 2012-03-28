package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class PresenceSocialAttack extends AbstractSocialAttack {

  public PresenceSocialAttack(IGenericTraitCollection collection, IEquipmentModifiers equipmentModifiers) {
    super(collection,equipmentModifiers);
  }

  @Override
  public int getRate() {
    return 2;
  }

  @Override
  public int getSpeed() {
    return 4;
  }

  @Override
  public AbilityType getName() {
    return AbilityType.Presence;
  }
}