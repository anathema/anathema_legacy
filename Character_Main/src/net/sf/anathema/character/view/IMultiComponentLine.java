package net.sf.anathema.character.view;

import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IMultiComponentLine {

  void init();

  ITextView addFieldsView(String labelText);
}
