package net.sf.anathema.framework.presenter.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ListCellRenderer;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class ObjectSelectionIntValueView implements IIntValueView, IGridDialogPanelContent {

  private final ObjectSelectionView view;
  private final Map<IIntValueChangedListener, IObjectValueChangedListener<Integer>> listenerMap = new HashMap<IIntValueChangedListener, IObjectValueChangedListener<Integer>>();

  public ObjectSelectionIntValueView(String label, ListCellRenderer renderer, int maximum) {
    this.view = new ObjectSelectionView(label, renderer, createIntegerArray(maximum));
  }

  public void setValue(int newValue) {
    view.setSelectedObject(new Integer(newValue));
  }

  public void addIntValueChangedListener(final IIntValueChangedListener listener) {
    IObjectValueChangedListener<Integer> changeListener = new IObjectValueChangedListener<Integer>() {
      public void valueChanged(Integer newValue) {
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
    Integer[] ranks = new Integer[Math.abs(maximalValue) + 1];
    for (int index = 0; index < ranks.length; index++) {
      ranks[index] = Integer.signum(maximalValue) * index;
    }
    return ranks;
  }

  public void addComponents(IGridDialogPanel gridPanel) {
    view.addComponents(gridPanel);
  }
}