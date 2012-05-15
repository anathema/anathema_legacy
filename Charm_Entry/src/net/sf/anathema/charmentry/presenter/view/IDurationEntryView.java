package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JRadioButton;

public interface IDurationEntryView extends IPageContent {

  ITextView addRadioButtonTextField(String string);

  JRadioButton addRadioButton(String string);

  void addTypeChangeListener(IChangeListener changeListener);
}