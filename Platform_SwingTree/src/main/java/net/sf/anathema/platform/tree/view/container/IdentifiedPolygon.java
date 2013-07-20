package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.platform.tree.view.draw.AgnosticPolygon;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;

public class IdentifiedPolygon {

  public final AgnosticPolygon element;
  public final String id;

  public IdentifiedPolygon(FilledPolygon element, String id) {
    this.element = element;
    this.id = id;
  }
}
