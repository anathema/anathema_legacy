package net.sf.anathema.fx.hero.perspective;

import net.sf.anathema.framework.view.util.FxStack;
import net.sf.anathema.lib.util.Identifier;

public class SwitchToView {

  private final Identifier name;
  private final FxStack stack;

  public SwitchToView(Identifier name, FxStack stack) {
    this.name = name;
    this.stack = stack;
  }

  public void execute() {
    stack.show(name);
  }
}