package net.sf.anathema.character.generic.framework.xml.health;

import net.sf.anathema.character.generic.traits.ITraitType;

public interface IHealthTemplate {

  public ITraitType[] getToughnessControllingTraits();
  
  public String[] getBaseHealthProviders();

}
