package net.sf.anathema.character.equipment.creation.view.fx;

import javafx.scene.control.CheckBox;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueView;
import org.jmock.example.announcer.Announcer;

public class FxBooleanView implements BooleanValueView {

  private final CheckBox checkBox = new CheckBox();
  private final Announcer<IBooleanValueChangedListener> announcer = Announcer.to(IBooleanValueChangedListener.class);

  public FxBooleanView() {
    checkBox.setOnAction(event -> announcer.announce().valueChanged(checkBox.isSelected()));
  }

  @Override
  public void setSelected(boolean selected) {
    checkBox.setSelected(selected);
  }

  @Override
  public void addChangeListener(IBooleanValueChangedListener listener) {
    announcer.addListener(listener);
  }
}
