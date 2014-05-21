package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.model.types.ITraitTypeVisitor;
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