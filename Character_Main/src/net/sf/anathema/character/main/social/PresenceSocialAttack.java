package net.sf.anathema.character.main.social;

import net.sf.anathema.character.main.IGenericTraitCollection;
import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.main.traits.types.AbilityType;

public class PresenceSocialAttack extends AbstractSocialAttack {

  public PresenceSocialAttack(IGenericTraitCollection collection, ICharacterStatsModifiers equipmentModifiers) {
    super(collection, equipmentModifiers);
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