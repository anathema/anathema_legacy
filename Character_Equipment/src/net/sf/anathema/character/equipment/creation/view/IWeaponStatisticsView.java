package net.sf.anathema.character.equipment.creation.view;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.ICheckableTextView;

public interface IWeaponStatisticsView extends IPageContent {

  public ICheckableTextView addCheckableLineTextView(String label);
  
  public IntegerSpinner addIntegerSpinner(String label, int startValue);
  
  public void addDialogComponent(IDialogComponent component);
}