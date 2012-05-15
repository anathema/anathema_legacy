package net.sf.anathema.character.generic.traits;

import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.util.IIdentificate;

public interface ITraitType extends IIdentificate {

  void accept(ITraitTypeVisitor visitor);
}