package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.view.draw.FilledPolygon;

public interface Closure {
  void execute(FilledPolygon polygon);
}
