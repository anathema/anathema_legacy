package net.sf.anathema.platform.tree.fx;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.ToggleButton;
import javafx.scene.transform.Transform;
import net.sf.anathema.platform.tree.display.SpecialControl;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.view.interaction.SpecialContentMap;
import net.sf.anathema.platform.tree.view.interaction.SpecialControlTrigger;

public class FxSpecialTrigger implements SpecialControlTrigger {

  private final ToggleButton button = new ToggleButton();
  private SpecialContentMap specialContent;
  private Group parent;

  @Override
  public void init(String title, SpecialContentMap specialContent) {
    this.specialContent = specialContent;
    button.setText(title);
  }

  @Override
  public void transformThrough(AgnosticTransform transform) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void transformOriginalCoordinates(AgnosticTransform transform) {
    Transform fxTransform = FxTransformer.convert(transform);
    button.getTransforms().clear();
    button.getTransforms().add(fxTransform);
  }

  @Override
  public void whenTriggeredShow(SpecialControl specialControl) {
    final FxCharmPopupContainer container = new FxCharmPopupContainer(button, specialContent);
    specialControl.showIn(container);
    button.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
      @Override
      public void handle(javafx.event.ActionEvent actionEvent) {
        if (button.isSelected()) {
          container.display();
        } else {
          container.hide();
        }
      }
    });
  }

  @Override
  public void placeBelow(AgnosticShape shape) {
    Bounds bounds = FxTransformer.convert(shape).getBoundsInLocal();
    button.setLayoutX(bounds.getMinX());
    button.setLayoutY(bounds.getMaxY() + 10);
    button.setMinWidth(bounds.getWidth());
    button.setPrefWidth(bounds.getWidth());
    button.setMaxWidth(bounds.getWidth());
  }

  public void addTo(Group group) {
    group.getChildren().add(button);
    this.parent = group;
  }

  public void remove() {
    parent.getChildren().remove(button);
  }
}