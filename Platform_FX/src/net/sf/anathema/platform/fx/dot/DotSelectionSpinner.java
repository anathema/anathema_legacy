package net.sf.anathema.platform.fx.dot;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import jfxtras.labs.scene.control.ListSpinner;
import net.sf.anathema.platform.fx.FxThreading;
import org.jmock.example.announcer.Announcer;

public class DotSelectionSpinner {

  private final Announcer<ChangeListener> announcer = new Announcer<>(ChangeListener.class);
  private ListSpinner<Integer> spinner;
  @SuppressWarnings("unchecked")
  private ChangeListener<Integer> announcingListener = new ChangeListener<Integer>() {
    @Override
    public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
      announcer.announce().changed(observableValue, oldValue, newValue);
    }
  };

  public DotSelectionSpinner(final int lowerBound, final int upperBound) {
    FxThreading.runInFxAsSoonAsPossible(new Runnable() {
      @Override
      public void run() {
        spinner = new ListSpinner<>(lowerBound, upperBound);
        spinner.getStyleClass().add("dots");
        spinner.valueProperty().addListener(announcingListener);
      }
    });
  }

  public Node getNode() {
    waitForComponent();
    return spinner;
  }

  public void setValue(int value) {
    waitForComponent();
    spinner.setValue(value);
  }

  public void setValueSilently(int value) {
    spinner.valueProperty().removeListener(announcingListener);
    setValue(value);
    spinner.valueProperty().addListener(announcingListener);
  }

  public void addListener(ChangeListener<Integer> listener) {
    announcer.addListener(listener);
  }

  public void removeListener(ChangeListener<Integer> listener) {
    announcer.removeListener(listener);
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