package net.sf.anathema.character.generic.framework.xml.health;

import net.sf.anathema.character.generic.traits.TraitType;

public interface IHealthTemplate {

  TraitType[] getToughnessControllingTraits();

  String[] getBaseHealthProviders();

}
