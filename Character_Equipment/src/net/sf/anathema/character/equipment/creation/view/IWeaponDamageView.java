package net.sf.anathema.character.equipment.creation.view;

import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public interface IWeaponDamageView extends IDialogComponent, IObjectSelectionView<HealthType> {

  public IntegerSpinner getDamageIntegerSpinner();
  
  public IntegerSpinner getMinDamageIntegerSpinner();

  public void setHealthTypeRenderer(ListCellRenderer renderer);

  public void setDamageLabelText(String label);
  
  public void setMinDamageLabelText(String label);
}