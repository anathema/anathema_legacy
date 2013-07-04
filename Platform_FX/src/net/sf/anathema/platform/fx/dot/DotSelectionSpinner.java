package net.sf.anathema.platform.fx.dot;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import jfxtras.labs.scene.control.ListSpinner;
import net.sf.anathema.platform.fx.FxThreading;

public class DotSelectionSpinner {

  private ListSpinner<Integer> spinner;

  public DotSelectionSpinner(final int lowerBound, final int upperBound) {
    FxThreading.runInFxAsSoonAsPossible(new Runnable() {
      @Override
      public void run() {
        spinner = new ListSpinner<>(lowerBound, upperBound);
        spinner.getStyleClass().add("dots");
      }
    });
  }

  public Node getNode() {
    return spinner;
  }

  public void setValue(int value) {
    waitForComponent();
    spinner.setValue(value);
  }

  public void addListener(ChangeListener<Integer> listener) {
    waitForComponent();
    spinner.valueProperty().addListener(listener);
  }

  public void removeListener(ChangeListener<Integer> listener) {
    spinner.valueProperty().removeListener(listener);
  }

  public int getValue() {
    return spinner.getValue();
  }

  private void waitForComponent() {
    try {
      while (spinner == null) {
        Thread.sleep(50);
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}