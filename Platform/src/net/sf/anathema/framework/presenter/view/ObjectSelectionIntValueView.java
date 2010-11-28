package net.sf.anathema.framework.presenter.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.lang.ArrayUtilities;

public class ObjectSelectionIntValueView implements IIntValueDisplay {

  private final ObjectSelectionView<Integer> view;
  private final Map<IIntValueChangedListener, IObjectValueChangedListener<Integer>> listenerMap = new HashMap<IIntValueChangedListener, IObjectValueChangedListener<Integer>>();
  private final JPanel content = new JPanel(new GridDialogLayout(2, false));

  public ObjectSelectionIntValueView(String label, ListCellRenderer renderer, int maximum) {
    this.view = new ObjectSelectionView<Integer>(label, renderer, ArrayUtilities.createIntegerArray(maximum));
  }

  public ObjectSelectionIntValueView(String label, ListCellRenderer renderer, int minimum, int maximum) {
    this.view = new ObjectSelectionView<Integer>(label, renderer, ArrayUtilities.createIntegerArray(minimum, maximum));
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

  public void setEnabled(boolean enabled) {
    view.setEnabled(enabled);
  }

  public JComponent getComponent() {
    view.addTo(content, GridDialogLayoutData.FILL_HORIZONTAL);
    return content;
  }
}