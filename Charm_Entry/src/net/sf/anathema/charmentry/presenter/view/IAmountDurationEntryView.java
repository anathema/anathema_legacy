package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.ListCellRenderer;

public interface IAmountDurationEntryView extends IPageContent {

  IntegerSpinner addRadioButtonSpinner();

  ObjectSelectionView<ITraitType> addRadioButtonComboBox(String label, ListCellRenderer renderer, ITraitType[] types);

  void addTypeChangeListener(IChangeListener changeListener);

  ITextView addTextView();
}