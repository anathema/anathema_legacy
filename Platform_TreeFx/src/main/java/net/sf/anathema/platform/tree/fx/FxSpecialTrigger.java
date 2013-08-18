package net.sf.anathema.platform.tree.fx;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.ToggleButton;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.SpecialControl;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.view.interaction.SpecialContentMap;
import net.sf.anathema.platform.tree.view.interaction.SpecialControlTrigger;

public class FxSpecialTrigger implements SpecialControlTrigger {

  private final ToggleButton button = new ToggleButton();
  private SpecialContentMap specialContent;
  private FxGroupCanvas parent;

  @Override
  public void init(String title, SpecialContentMap specialContent) {
    this.specialContent = specialContent;
    button.setText(title);
  }

  @Override
  public void transformThrough(AgnosticTransform transform) {
    //nothing to do
  }

  @Override
  public void transformOriginalCoordinates(AgnosticTransform transform) {
    //nothing to do
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

  public void addTo(FxGroupCanvas group) {
    this.parent = group;
    parent.addControl(button);
  }

  public void remove() {
    parent.remove(button);
  }

  public void toggle() {
    button.fire();
  }

  public boolean contains(Coordinate coordinate) {
    Point2D point2D = button.parentToLocal(coordinate.x, coordinate.y);
    return button.contains(point2D);
  }
}