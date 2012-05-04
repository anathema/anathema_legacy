package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.view.draw.FilledPolygon;

public class PolygonExecutor implements Executor {
  private final FilledPolygon polygon;

  public PolygonExecutor(FilledPolygon polygon) {
    this.polygon = polygon;
  }

  @Override
  public Executor perform(Closure closure) {
    closure.execute(polygon);
    return this;
  }

  @Override
  public void orFallBackTo(Runnable defaultAction) {
    //nothing to do
  }
}
