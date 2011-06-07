package net.sf.anathema.character.generic.framework.xml.health;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericHealthTemplate implements IHealthTemplate, ICloneable<GenericHealthTemplate> {

  private ITraitType toughnessTraitType = AbilityType.Endurance;
  private String[] baseProviders;

  @Override
  public GenericHealthTemplate clone() {
    GenericHealthTemplate healthTemplate = new GenericHealthTemplate();
    healthTemplate.setToughnessControllingTraitType(toughnessTraitType);
    healthTemplate.setBaseProviders(baseProviders);
    return healthTemplate;
  }

  public ITraitType getToughnessControllingTrait() {
    return toughnessTraitType;
  }
  
  public String[] getBaseHealthProviders()
  {
	  return baseProviders;
  }

  public void setToughnessControllingTraitType(ITraitType traitType) {
    this.toughnessTraitType = traitType;
  }
  
  public void setBaseProviders(String[] baseProviders)
  {
	  this.baseProviders = baseProviders;
  }
}