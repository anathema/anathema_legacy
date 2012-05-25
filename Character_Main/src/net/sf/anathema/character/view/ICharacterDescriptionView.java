package net.sf.anathema.character.view;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ICharacterDescriptionView extends IView {

  ITextView[] addFieldsView(String[] labelText);

  ITextView addLineView(String labelText);

  ITextView addAreaView(String labelText, int rowCount);

  void addEditAction(SmartAction action, int row);
}