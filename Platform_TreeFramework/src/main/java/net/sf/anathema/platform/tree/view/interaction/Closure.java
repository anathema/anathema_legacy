package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

public interface Closure {
  void execute(InteractiveGraphicsElement polygon);
}