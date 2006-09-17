package net.sf.anathema.character.view;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ICharacterDescriptionView extends IView {

  public ITextView addLineView(String labelText);

  public ITextView addAreaView(String labelText, int rowCount);

  public void addEditAction(SmartAction action, int row);
}