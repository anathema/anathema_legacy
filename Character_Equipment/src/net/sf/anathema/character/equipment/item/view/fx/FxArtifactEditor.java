package net.sf.anathema.character.equipment.item.view.fx;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import net.sf.anathema.character.equipment.item.ArtifactEditor;
import net.sf.anathema.character.equipment.item.events.UserInput;
import net.sf.anathema.character.equipment.item.events.ViewShouldShow;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.equipment.item.events.ArtifactEvents.Stats_Name;


public class FxArtifactEditor implements ArtifactEditor {

  private Resources resources;
  private final TextField nameField = new TextField();

  public FxArtifactEditor(Resources resources) {
    this.resources = resources;
  }

  public Node getNode() {
    return nameField;
  }

  @Override
  public void registerOn(final EventBus eventBus) {
    nameField.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
        eventBus.post(new UserInput(Stats_Name, newValue));
      }
    });

    eventBus.register(new Object() {
      @Subscribe
      public void statsChanged(ViewShouldShow valueFromModel) {
        if (valueFromModel.is(Stats_Name)) {
          nameField.setText(valueFromModel.as(String.class));
        }
      }
    });
  }
}