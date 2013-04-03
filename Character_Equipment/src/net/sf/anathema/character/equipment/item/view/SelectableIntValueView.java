package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;

public interface SelectableIntValueView<V> extends AdditiveView{

  void setSelectableValues(V[] traits);

  void addSelectionChangedListener(ISelectionIntValueChangedListener<V> listener);

  void setValue(int value);

  void setSelectedObject(V object);
}