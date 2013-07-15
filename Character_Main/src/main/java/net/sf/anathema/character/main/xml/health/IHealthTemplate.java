package net.sf.anathema.character.main.xml.health;

import net.sf.anathema.character.main.traits.TraitType;

public interface IHealthTemplate {

  TraitType[] getToughnessControllingTraits();

  String[] getBaseHealthProviders();

}
