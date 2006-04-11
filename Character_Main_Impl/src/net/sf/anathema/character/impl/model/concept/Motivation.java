package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class Motivation implements IMotivation {

  private final ISimpleTextualDescription description = new SimpleTextualDescription();

  public ISimpleTextualDescription getDescription() {
    return description;
  }

  public void accept(IWillpowerRegainingConceptVisitor visitor) {
    visitor.accept(this);
  }
}