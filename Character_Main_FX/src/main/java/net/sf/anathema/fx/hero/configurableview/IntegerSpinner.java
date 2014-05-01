package net.sf.anathema.fx.hero.configurableview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import jfxtras.scene.control.ListSpinner;
import jfxtras.scene.control.ListSpinnerIntegerList;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.IIntegerView;

public class IntegerSpinner implements IIntegerView, IIntegerSpinner {

  private static final String STYLE_READONLY = "readonly";
  private int minimum = 0;
  private int maximum = 10000;
  private int stepSize = 5;
  private final ListSpinner<Integer> spinner;

  public IntegerSpinner() {
    this(5);
  }

  public IntegerSpinner(int stepsize) {
    this.stepSize = stepsize;
    this.spinner = new ListSpinner<>(minimum, maximum, stepSize);
  }

  @Override
  public void addChangeListener(final IntValueChangedListener listener) {
    spinner.valueProperty().addListener(new ChangeListener<Integer>() {
      @Override
      public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer newValue) {
        listener.valueChanged(newValue);
      }
    });
  }

  @Override
  public void setEnabled(boolean enabled) {
    if (enabled) {
      spinner.getStyleClass().remove(STYLE_READONLY);
    } else {
      spinner.getStyleClass().add(STYLE_READONLY);
    }
  }

  public void setValue(int value) {
    spinner.setValue(value);
  }

  @Override
  public void setMinimum(Integer minimum) {
    if (minimum == Integer.MIN_VALUE) {
      minimum = -100;
    }
    int currentValue = spinner.getValue();
    this.minimum = minimum;
    refreshValues(currentValue);
  }

  @Override
  public void setMaximum(Integer maximum) {
    if (maximum == Integer.MAX_VALUE) {
      maximum = 100;
    }
    int currentValue = spinner.getValue();
    this.maximum = maximum;
    refreshValues(currentValue);
  }

  private void refreshValues(int currentValue) {
    ListSpinnerIntegerList newList = new ListSpinnerIntegerList(minimum, maximum, stepSize);
    spinner.setItems(FXCollections.observableList(newList));
    spinner.setValue(minimum);
    spinner.setValue(currentValue);
  }

  public Node getNode() {
    return spinner;
  }
}
