package net.sf.anathema.hero.equipment.display;

import net.sf.anathema.character.equipment.impl.item.model.NullClosure;
import net.sf.anathema.lib.util.Closure;

public class ProxyClosure<S> implements Closure<S> {
  private Closure<S> delegate = new NullClosure<>();

  public void setDelegate(Closure<S> closure) {
    this.delegate = closure;
  }

  @Override
  public void execute(S value) {
    delegate.execute(value);
  }
}
