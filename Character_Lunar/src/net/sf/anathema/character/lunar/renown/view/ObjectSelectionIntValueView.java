package net.sf.anathema.character.lunar.renown.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ListCellRenderer;

import net.sf.anathema.framework.presenter.view.ObjectSelectionView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class ObjectSelectionIntValueView implements IIntValueView {

  private final ObjectSelectionView view;
  private final Map<IIntValueChangedListener, IObjectValueChangedListener<Integer>> listenerMap = new HashMap<IIntValueChangedListener, IObjectValueChangedListener<Integer>>();
  private final String label;
  private final ListCellRenderer renderer;

  public ObjectSelectionIntValueView(String label, ListCellRenderer renderer, int maximum) {
    this.label = label;
    this.renderer = renderer;
    this.view = new ObjectSelectionView(createIntegerArray(maximum));
  }

  public void setValue(int newValue) {
    view.setSelectedObject(new Integer(newValue));
  }

  public void addIntValueChangedListener(final IIntValueChangedListener listener) {
    IObjectValueChangedListener<Integer> changeListener = new IObjectValueChangedListener<Integer>() {
      public void valueChanged(Integer oldValue, Integer newValue) {
        listener.valueChanged(newValue);
      }
    };
    listenerMap.put(listener, changeListener);
    view.addObjectSelectionChangedListener(changeListener);
  }

  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    throw new NotYetImplementedException();
  }

  public void setMaximum(int maximalValue) {
    throw new NotYetImplementedException();
  }

  private Integer[] createIntegerArray(int maximalValue) {
    Integer[] ranks = new Integer[maximalValue + 1];
    for (int index = 0; index < ranks.length; index++) {
      ranks[index] = index;
    }
    return ranks;
  }

  public void addTo(IGridDialogPanel facePanel) {
    view.addTo(label, renderer, facePanel);
  }
}