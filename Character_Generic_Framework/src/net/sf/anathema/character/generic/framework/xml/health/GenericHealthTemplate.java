package net.sf.anathema.character.generic.framework.xml.health;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericHealthTemplate implements IHealthTemplate, ICloneable<GenericHealthTemplate> {

  private ITraitType[] toughnessTraitTypes = new ITraitType[] { AbilityType.Endurance };

  @Override
  public GenericHealthTemplate clone() {
    GenericHealthTemplate healthTemplate = new GenericHealthTemplate();
    healthTemplate.setToughnessControllingTraitTypes(toughnessTraitTypes);
    return healthTemplate;
  }

  public ITraitType[] getToughnessControllingTraits() {
    return toughnessTraitTypes;
  }

  public void setToughnessControllingTraitTypes(ITraitType[] traitTypes) {
    this.toughnessTraitTypes = traitTypes;
  }
}