package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;

import javax.swing.JPanel;

public interface ISelectableIntValueView<V> {

  void setSelectableValues(V[] traits);

  void addSelectionChangedListener(ISelectionIntValueChangedListener<V> listener);

  void setValue(int value);

  void setSelectedObject(V object);
  
  void addTo(JPanel panel);
}