package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;

public interface CostSelectionView {
  void setValue(ItemCost cost);

  void addSelectionChangedListener(ISelectionIntValueChangedListener<String> listener);

  void setSelectableBackgrounds(String[] backgrounds);
}
