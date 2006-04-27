package net.sf.anathema.character.equipment.creation.view;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ICloseCombatStatisticsView extends IPageContent {

  public ITextView addLineTextView(String label);
  
  public IntegerSpinner addIntegerSpinner(String label, int startValue);
}