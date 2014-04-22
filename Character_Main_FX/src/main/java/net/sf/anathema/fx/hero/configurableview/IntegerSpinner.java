package net.sf.anathema.fx.hero.configurableview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import jfxtras.scene.control.ListSpinner;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.IIntegerView;

public class IntegerSpinner implements IIntegerView {

  private final ListSpinner<Integer> spinner = new ListSpinner<>(0, 10000, 5);

  @Override
  public void addChangeListener(final IntValueChangedListener listener) {
    spinner.valueProperty().addListener(new ChangeListener<Integer>() {
      @Override
      public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer newValue) {
        listener.valueChanged(newValue);
      }
    });
  }

  public void setValue(int value) {
    spinner.setValue(value);
  }

  public Node getNode() {
    return spinner;
  }
}
