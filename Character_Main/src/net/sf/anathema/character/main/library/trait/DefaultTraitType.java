package net.sf.anathema.character.main.library.trait;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class DefaultTraitType extends SimpleIdentifier implements TraitType {

  public DefaultTraitType(String id) {
    super(id);
  }

  @Override
  public void accept(ITraitTypeVisitor visitor) {
    throw new UnsupportedOperationException();
    // visitor.visitCustomTraitType(this);
  }
}