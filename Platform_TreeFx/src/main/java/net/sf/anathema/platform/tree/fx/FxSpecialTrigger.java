package net.sf.anathema.platform.tree.fx;

import javafx.scene.Group;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Rectangle;
import net.sf.anathema.platform.tree.display.SpecialControl;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.view.interaction.SpecialContentMap;
import net.sf.anathema.platform.tree.view.interaction.SpecialControlTrigger;

public class FxSpecialTrigger implements SpecialControlTrigger {

  private Rectangle originalBounds = new Rectangle(0, 0, 0, 15);
  private final ToggleButton button = new ToggleButton();
  private SpecialContentMap specialContent;

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
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void whenTriggeredShow(SpecialControl specialControl) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void placeBelow(AgnosticShape shape) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void addTo(Group group) {
    //To change body of created methods use File | Settings | File Templates.
  }

  public void remove() {
    //To change body of created methods use File | Settings | File Templates.
  }
}
