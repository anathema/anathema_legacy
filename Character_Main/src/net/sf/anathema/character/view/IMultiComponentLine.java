package net.sf.anathema.character.view;

import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.lib.gui.widgets.IIntegerView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IMultiComponentLine {

  void init();

  ITextView addFieldsView(String labelText);

  IIntegerView addIntegerView(String labelText, IIntegerDescription integerDescription);
}
