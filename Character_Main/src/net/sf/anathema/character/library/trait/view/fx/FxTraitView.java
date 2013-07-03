package net.sf.anathema.character.library.trait.view.fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import jfxtras.labs.scene.control.ListSpinner;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class FxTraitView implements IIntValueView {
  private final ListSpinner<Integer> spinner;
  private final Announcer<IIntValueChangedListener> valueChangeAnnouncer = new Announcer<>(IIntValueChangedListener.class);

  public FxTraitView(int value, int maxValue) {
    this.spinner = new ListSpinner<>(0, maxValue);
    setValue(value);
    initListening();
  }

  public Node getNode() {
    return spinner;
  }

  @Override
  public void setValue(int newValue) {
    spinner.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    valueChangeAnnouncer.addListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    valueChangeAnnouncer.removeListener(listener);
  }

  private void initListening() {
    spinner.valueProperty().addListener(new ChangeListener<Integer>() {
      @Override
      public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer newValue) {
        valueChangeAnnouncer.announce().valueChanged(newValue);
      }
    });
  }
}