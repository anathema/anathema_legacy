package net.sf.anathema.character.generic.framework.xml.health;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IHealthTemplate extends ICloneable {

  public ITraitType getToughnessControllingTrait();

}
