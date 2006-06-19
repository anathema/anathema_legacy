package net.sf.anathema.character.generic.framework.xml.trait;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IClonableTraitTemplate extends ITraitTemplate, ICloneable<IClonableTraitTemplate> {

  public IClonableTraitTemplate clone();
}