package net.sf.anathema.character.main.xml.trait;

import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IClonableTraitTemplate extends ITraitTemplate, ICloneable<IClonableTraitTemplate> {

  IClonableTraitTemplate clone();
}