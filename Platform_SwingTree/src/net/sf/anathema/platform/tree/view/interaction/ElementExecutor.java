package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

public class ElementExecutor implements Executor {
  private final InteractiveGraphicsElement element;

  public ElementExecutor(InteractiveGraphicsElement element) {
    this.element = element;
  }

  @Override
  public Executor perform(Closure closure) {
    closure.execute(element);
    return this;
  }

  @Override
  public void orFallBackTo(Runnable defaultAction) {
    //nothing to do
  }
}
