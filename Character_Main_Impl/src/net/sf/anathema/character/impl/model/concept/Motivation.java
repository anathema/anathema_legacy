package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class Motivation implements IMotivation {

  private final ITextualDescription description = new SimpleTextualDescription();

  public ITextualDescription getDescription() {
    return description;
  }

  public void accept(IWillpowerRegainingConceptVisitor visitor) {
    visitor.accept(this);
  }
}