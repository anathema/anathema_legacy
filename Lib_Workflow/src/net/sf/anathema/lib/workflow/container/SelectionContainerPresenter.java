package net.sf.anathema.lib.workflow.container;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.anathema.lib.lang.ArrayFactory;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.lang.IArrayFactory;

public class SelectionContainerPresenter<V> {

  private final ISelectionContainerModel<V> model;
  private final ISelectionContainerView view;
  private final IArrayFactory<V> arrayFactory;

  public SelectionContainerPresenter(
      ISelectionContainerModel<V> model,
      ISelectionContainerView view,
      Class<V> componentType) {
    this.model = model;
    this.view = view;
    this.arrayFactory = new ArrayFactory<V>(componentType);
  }

  public void initPresentation() {
    view.addSelectionChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        Object[] selectedValues = view.getSelectedValues();
        V[] valueArray = arrayFactory.createArray(selectedValues.length);
        ArrayUtilities.copyAll(selectedValues, valueArray);
        model.setSelectedValues(valueArray);
      }
    });
    model.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        V[] values = model.getSelectedValues();
        if (values == null) {
          values = arrayFactory.createArray(0);
        }
        view.setSelectedValues(values);
      }
    });
    view.populate(model.getAllAvailableValues());
    
    
  }
}