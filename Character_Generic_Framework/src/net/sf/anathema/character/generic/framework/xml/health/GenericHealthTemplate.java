package net.sf.anathema.character.generic.framework.xml.health;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class GenericHealthTemplate implements IHealthTemplate {

  private ITraitType toughnessTraitType = AbilityType.Endurance;

  @Override
  public GenericHealthTemplate clone() {
    GenericHealthTemplate healthTemplate = new GenericHealthTemplate();
    healthTemplate.setToughnessControllingTraitType(toughnessTraitType);
    return healthTemplate;
  }

  public ITraitType getToughnessControllingTrait() {
    return toughnessTraitType;
  }

  public void setToughnessControllingTraitType(ITraitType traitType) {
    this.toughnessTraitType = traitType;
  }
}