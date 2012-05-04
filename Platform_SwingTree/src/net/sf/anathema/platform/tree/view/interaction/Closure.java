package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.draw.FilledPolygon;

public interface Closure {
  void execute(FilledPolygon polygon);
}