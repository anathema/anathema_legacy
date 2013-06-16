package net.sf.anathema.character.generic.traits;

import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.util.Identifier;

public interface TraitType extends Identifier {

  void accept(ITraitTypeVisitor visitor);
}