package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.util.Identifier;

public class TraitType extends Identifier implements ITraitType {

  public TraitType(String id) {
    super(id);
  }

  @Override
  public void accept(ITraitTypeVisitor visitor) {
    throw new UnsupportedOperationException();
    // visitor.visitCustomTraitType(this);
  }
}