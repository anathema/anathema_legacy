package net.sf.anathema.fx.hero.perspective;

public class SwitchToView {

  private final String name;
  private final FxStack stack;

  public SwitchToView(String name, FxStack stack) {
    this.name = name;
    this.stack = stack;
  }

  public void execute() {
    stack.show(name);
  }
}