package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.view.draw.FilledPolygon;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NodeContainer implements Iterable<FilledPolygon> {

  private List<FilledPolygon> polygons = new ArrayList<FilledPolygon>();

  public void add(FilledPolygon polygon) {
    polygons.add(polygon);
  }

  @Override
  public Iterator<FilledPolygon> iterator() {
    return polygons.iterator();
  }

  public Executor onPolygonAtPoint(Point2D point) {
    for (FilledPolygon polygon : polygons)
      if (polygon.contains(point)) {
        return new PolygonExecutor(polygon);
      }
    return new DefaultExecutor();
  }
}
