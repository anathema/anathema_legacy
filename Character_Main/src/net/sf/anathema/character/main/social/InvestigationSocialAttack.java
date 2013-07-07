package net.sf.anathema.character.main.social;

import net.sf.anathema.character.main.IGenericTraitCollection;
import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.main.traits.types.AbilityType;

public class InvestigationSocialAttack extends AbstractSocialAttack {

  public InvestigationSocialAttack(IGenericTraitCollection collection, ICharacterStatsModifiers equipmentModifiers) {
    super(collection, equipmentModifiers);
  }

  @Override
  public int getRate() {
    return 2;
  }

  @Override
  public int getSpeed() {
    return 5;
  }

  @Override
  public AbilityType getName() {
    return AbilityType.Investigation;
  }
}