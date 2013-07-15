package net.sf.anathema.platform.tree.view.interaction;

public class DefaultExecutor implements Executor {
  @Override
  public Executor perform(Closure closure) {
    return this;
  }

  @Override
  public void orFallBackTo(Runnable defaultClosure) {
    defaultClosure.run();
  }
}