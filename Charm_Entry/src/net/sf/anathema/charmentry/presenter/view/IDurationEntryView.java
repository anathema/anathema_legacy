package net.sf.anathema.charmentry.presenter.view;

import javax.swing.JRadioButton;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IDurationEntryView extends IPageContent {

  public ITextView addRadioButtonTextField(String string);

  public JRadioButton addRadioButton(String string);

  public void addTypeChangeListener(IChangeListener changeListener);
}