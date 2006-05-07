package net.sf.anathema.character.equipment.creation.view;

import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public interface IWeaponDamageView extends IDialogComponent, IObjectSelectionView {

  public IntegerSpinner getIntegerSpinner();
  
  public void setHealthTypeRenderer(ListCellRenderer renderer);

  public void setLabelText(String label);
}