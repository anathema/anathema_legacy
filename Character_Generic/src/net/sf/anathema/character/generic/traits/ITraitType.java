package net.sf.anathema.character.generic.traits;

import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.util.Identified;

public interface ITraitType extends Identified {

  void accept(ITraitTypeVisitor visitor);
}