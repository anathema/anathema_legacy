package net.sf.anathema.character.generic.framework.xml.health;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericHealthTemplate implements IHealthTemplate, ICloneable<GenericHealthTemplate> {

  private ITraitType[] toughnessTraitTypes = new ITraitType[]{AbilityType.Resistance};
  private String[] baseProviders;

  @Override
  public GenericHealthTemplate clone() {
    GenericHealthTemplate healthTemplate;
    try {
      healthTemplate = (GenericHealthTemplate) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    return healthTemplate;
  }

  @Override
  public ITraitType[] getToughnessControllingTraits() {
    return toughnessTraitTypes;
  }

  @Override
  public String[] getBaseHealthProviders() {
    return baseProviders;
  }

  public void setToughnessControllingTraitTypes(ITraitType[] traitTypes) {
    this.toughnessTraitTypes = traitTypes;
  }

  public void setBaseProviders(String[] baseProviders) {
    this.baseProviders = baseProviders;
  }
}