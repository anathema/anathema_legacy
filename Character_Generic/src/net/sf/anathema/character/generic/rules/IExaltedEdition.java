package net.sf.anathema.character.generic.rules;

import net.sf.anathema.character.generic.caste.ITypedDescriptionType;

public interface IExaltedEdition extends ITypedDescriptionType {

  void accept(IEditionVisitor visitor);
}