package net.sf.anathema.character.model.concept;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IMotivation {

  ITextualDescription getDescription();

  void beginEdit();

  void cancelEdit();

  void endEditXPSpending(String xpMessage);

  void endEdit();

  void addEditingListener(IEditMotivationListener listener);

  ITextualDescription getEditableDescription();
}