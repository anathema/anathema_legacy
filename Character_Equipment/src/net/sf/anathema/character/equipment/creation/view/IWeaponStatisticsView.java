package net.sf.anathema.character.equipment.creation.view;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IWeaponStatisticsView extends IPageContent {

  ITextView addLineTextView(String label);

  IntegerSpinner addIntegerSpinner(String label, int startValue);

  void addDialogComponent(IDialogComponent component);
}