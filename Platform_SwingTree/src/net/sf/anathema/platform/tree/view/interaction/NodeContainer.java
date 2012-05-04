package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.draw.FilledPolygon;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class NodeContainer {

  private List<FilledPolygon> polygons = new ArrayList<FilledPolygon>();

  public void add(FilledPolygon polygon) {
    polygons.add(polygon);
  }

  public Executor onPolygonAtPoint(Point2D point) {
    for (FilledPolygon polygon : polygons)
      if (polygon.contains(point)) {
        return new PolygonExecutor(polygon);
      }
    return new DefaultExecutor();
  }
}
