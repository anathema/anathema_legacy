package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.platform.tree.display.draw.AgnosticPolygon;

public class IdentifiedPolygon {

  public final AgnosticPolygon element;
  public final String id;

  public IdentifiedPolygon(AgnosticPolygon element, String id) {
    this.element = element;
    this.id = id;
  }
}
