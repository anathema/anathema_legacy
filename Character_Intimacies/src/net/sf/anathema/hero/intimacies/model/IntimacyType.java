package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class IntimacyType extends SimpleIdentifier implements TraitType {

  public IntimacyType(String name) {
    super(name);
  }

  @Override
  public void accept(ITraitTypeVisitor visitor) {
    throw new NotYetImplementedException();
  }
}