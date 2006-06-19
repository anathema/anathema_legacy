package net.sf.anathema.character.generic.framework.xml.health;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericHealthTemplate implements IHealthTemplate, ICloneable<GenericHealthTemplate> {

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