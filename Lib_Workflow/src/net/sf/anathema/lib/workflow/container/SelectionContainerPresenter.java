package net.sf.anathema.lib.workflow.container;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.lang.ArrayFactory;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.lang.IArrayFactory;

public class SelectionContainerPresenter<V> {

  private final ISelectionContainerModel<V> model;
  private final ISelectionContainerView<V> view;
  private final IArrayFactory<V> arrayFactory;

  public SelectionContainerPresenter(
      ISelectionContainerModel<V> model,
      ISelectionContainerView<V> view,
      Class<V> componentType) {
    this.model = model;
    this.view = view;
    this.arrayFactory = new ArrayFactory<V>(componentType);
  }

  public void initPresentation() {
    view.addSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        Object[] selectedValues = view.getSelectedValues();
        V[] valueArray = arrayFactory.createArray(selectedValues.length);
        ArrayUtilities.copyAll(selectedValues, valueArray);
        model.setSelectedValues(valueArray);
      }
    });
    model.addChangeListener(new IChangeListener() {
      public void changeOccured() {
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