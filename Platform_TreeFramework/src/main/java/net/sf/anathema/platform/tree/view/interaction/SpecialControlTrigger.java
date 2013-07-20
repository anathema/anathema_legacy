package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.display.SpecialControl;
import net.sf.anathema.platform.tree.display.draw.ShapeWithPosition;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;

public interface SpecialControlTrigger extends ShapeWithPosition {
  void transformThrough(AgnosticTransform transform);

  void transformOriginalCoordinates(AgnosticTransform transform);

  void init(String title, SpecialContentMap specialContent);

  void whenTriggeredShow(SpecialControl specialControl);
}
