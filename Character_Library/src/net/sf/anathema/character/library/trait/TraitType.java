package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.util.Identificate;

public class TraitType extends Identificate implements ITraitType {

  public TraitType(String id) {
    super(id);
  }

  public void accept(ITraitTypeVisitor visitor) {
    throw new UnsupportedOperationException();
    // visitor.visitCustomTraitType(this);
  }
}