package net.sf.anathema.character.equipment.item.view.fx;

import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;

public class NullCostSelectionView implements CostSelectionView {
  @Override
  public void setValue(ItemCost cost) {
    //Nothing to do
  }

  @Override
  public void addSelectionChangedListener(ISelectionIntValueChangedListener<String> listener) {
    //Nothing to do
  }

  @Override
  public void setSelectableBackgrounds(String[] backgrounds) {
    //Nothing to do
  }
}