package net.sf.anathema.character.model.concept;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IMotivation extends IWillpowerRegainingConcept {

  public ITextualDescription getDescription();

  public void beginEdit();

  public void endEditXPSpending(String xpMessage);

  public void endEdit();

  public void addEditingListener(IEditMotivationListener listener);

  public ITextualDescription getEditableDescription();
}