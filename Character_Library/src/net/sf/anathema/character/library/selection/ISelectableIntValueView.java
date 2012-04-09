package net.sf.anathema.character.library.selection;

import javax.swing.JPanel;

import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;

public interface ISelectableIntValueView<V> {

  void setSelectableValues(V[] traits);

  void addSelectionChangedListener(ISelectionIntValueChangedListener<V> listener);

  void setValue(int value);

  void setSelectedObject(V object);
  
  void addTo(JPanel panel);
}