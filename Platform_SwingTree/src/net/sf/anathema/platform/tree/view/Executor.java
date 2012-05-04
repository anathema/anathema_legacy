package net.sf.anathema.platform.tree.view;

public interface Executor {
  Executor perform(Closure closure);

  void orFallBackTo(Runnable defaultClosure);
}
