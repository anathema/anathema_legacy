package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identificate;

public class IntimacyType extends Identificate implements ITraitType {

  public IntimacyType(String name) {
    super(name);
  }

  public void accept(ITraitTypeVisitor visitor) {
    throw new NotYetImplementedException();
  }
}