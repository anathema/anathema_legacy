package net.sf.anathema.character.library.trait.view.fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import jfxtras.labs.scene.control.ListSpinner;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

public class FxTraitView implements IIntValueView {
  private final Announcer<IIntValueChangedListener> valueChangeAnnouncer = new Announcer<>(IIntValueChangedListener.class);
  private final ListSpinner<Integer> spinner;
  private final Label label;
  private final FxConfigurableLayout layout = FxConfigurableLayout.Right();

  public FxTraitView(String labelText, int value, int maxValue) {
    this.label = new Label(labelText);
    this.spinner = new ListSpinner<>(0, maxValue);
    spinner.getStyleClass().add("dots");
    setValue(value);
    initListening();
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

  public void addTo(MigPane parent) {
    layout.addLabel(label);
    layout.addDisplay(spinner);
    layout.addTo(parent);
  }
}