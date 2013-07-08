package net.sf.anathema.character.main.library.trait.view.fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.platform.fx.dot.DotSelectionSpinner;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

public class FxTraitView implements IIntValueView {
  public static FxTraitView WithDefaultLayout(String labelText, int maxValue) {
    return new FxTraitView(labelText, maxValue, FxConfigurableLayout.Right());
  }

  public static FxTraitView AsSingleNode(String labelText, int maxValue) {
    return new FxTraitView(labelText, maxValue, FxConfigurableLayout.Single());
  }

  private final Announcer<IIntValueChangedListener> valueChangeAnnouncer = new Announcer<>(IIntValueChangedListener.class);
  private final DotSelectionSpinner spinner;
  private final Label label;

  private final FxConfigurableLayout layout;

  private FxTraitView(String labelText, int maxValue, FxConfigurableLayout layout) {
    this.layout = layout;
    this.label = new Label(labelText);
    this.spinner = new DotSelectionSpinner(0, maxValue);
    initListening();
  }

  @Override
  public void setValue(int newValue) {
    spinner.setValueSilently(newValue);
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    valueChangeAnnouncer.addListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    valueChangeAnnouncer.removeListener(listener);
  }

  public void addTo(MigPane parent) {
    layout.addLabel(label);
    layout.addDisplay(spinner.getNode());
    layout.addTo(parent);
  }

  private void initListening() {
    spinner.addListener(new ChangeListener<Integer>() {
      @Override
      public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
        spinner.setValueSilently(oldValue);
        valueChangeAnnouncer.announce().valueChanged(newValue);
      }
    });
  }
}