package net.sf.anathema.character.view;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.presenter.view.ITabView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ICharacterDescriptionView extends ITabView<Object> {

  public ITextView addLineView(String labelText);

  public ITextView addAreaView(String labelText, int rows);

  public void addEditAction(SmartAction action, int row);
}