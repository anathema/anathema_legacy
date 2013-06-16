package net.sf.anathema.character.generic.framework.xml.health;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericHealthTemplate implements IHealthTemplate, ICloneable<GenericHealthTemplate> {

  private TraitType[] toughnessTraitTypes = new TraitType[]{AbilityType.Resistance};
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
  public TraitType[] getToughnessControllingTraits() {
    return toughnessTraitTypes;
  }

  @Override
  public String[] getBaseHealthProviders() {
    return baseProviders;
  }

  public void setToughnessControllingTraitTypes(TraitType[] traitTypes) {
    this.toughnessTraitTypes = traitTypes;
  }

  public void setBaseProviders(String[] baseProviders) {
    this.baseProviders = baseProviders;
  }
}