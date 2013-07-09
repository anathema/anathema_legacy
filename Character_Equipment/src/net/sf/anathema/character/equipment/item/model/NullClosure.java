package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.lib.util.Closure;

public class NullClosure<S> implements Closure<S> {
  @Override
  public void execute(S value) {
    //nothing to do;
  }
}
