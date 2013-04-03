package net.sf.anathema.character.equipment.item.view.swing;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.item.view.SelectableIntValueView;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.value.NullUpperBounds;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import org.jmock.example.announcer.Announcer;

import javax.swing.JPanel;

public class SwingSelectableIntValueView<V> implements SelectableIntValueView<V> {
  private final IChangeableJComboBox<V> objectSelectionBox = new ChangeableJComboBox<>(false);
  private final IIntValueDisplay valueDisplay;
  private final Announcer<ISelectionIntValueChangedListener> control = Announcer.to(
          ISelectionIntValueChangedListener.class);
  private int currentValue;

  public SwingSelectableIntValueView(IntegerViewFactory configuration, int initial, int max) {
    this.valueDisplay = configuration.createIntValueDisplay(max, initial, new NullUpperBounds());
    objectSelectionBox.addObjectSelectionChangedListener(new ObjectValueListener<V>() {
      @Override
      public void valueChanged(V newValue) {
        fireSelectionChangedEvent();
      }
    });
    valueDisplay.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        int oldValue = currentValue;
        currentValue = newValue;
        if (oldValue != newValue) {
          valueDisplay.setValue(newValue);
        }
        fireSelectionChangedEvent();
      }
    });
  }

  private void fireSelectionChangedEvent() {
    control.announce().valueChanged(objectSelectionBox.getSelectedObject(), currentValue);
  }

  @Override
  public void addTo(JPanel panel, CC data) {
    panel.add(objectSelectionBox.getComponent(), data);
    panel.add(valueDisplay.getComponent());
  }

  @Override
  public void setSelectableValues(V[] objects) {
    objectSelectionBox.setObjects(objects);
  }

  @Override
  public void addSelectionChangedListener(ISelectionIntValueChangedListener<V> listener) {
    control.addListener(listener);
  }

  @Override
  public void setSelectedObject(V object) {
    objectSelectionBox.setSelectedObject(object);
  }

  @Override
  public void setValue(int value) {
    valueDisplay.setValue(value);
  }
}