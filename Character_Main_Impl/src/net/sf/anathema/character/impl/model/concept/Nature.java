package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.INature;
import net.sf.anathema.character.model.concept.INatureType;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;

public class Nature implements INature {

  private final ITypedDescription<INatureType> nature = new TypedDescription<INatureType>(null);

  public ITypedDescription<INatureType> getDescription() {
    return nature;
  }

  public void accept(IWillpowerRegainingConceptVisitor visitor) {
    visitor.accept(this);
  }
}