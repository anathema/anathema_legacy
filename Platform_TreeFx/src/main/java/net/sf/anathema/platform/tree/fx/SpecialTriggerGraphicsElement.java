package net.sf.anathema.platform.tree.fx;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.view.draw.Canvas;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

public class SpecialTriggerGraphicsElement implements InteractiveGraphicsElement {
  private FxSpecialTrigger specialControl;

  public SpecialTriggerGraphicsElement(FxSpecialTrigger specialControl) {
    this.specialControl = specialControl;
  }

  @Override
  public boolean contains(Coordinate coordinate) {
    return specialControl.contains(coordinate);
  }

  @Override
  public void toggle() {
    specialControl.toggle();
  }

  @Override
  public void paint(Canvas graphics) {
    specialControl.addTo(graphics);
  }
}
