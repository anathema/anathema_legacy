package net.sf.anathema.hero.charms.display;

import net.sf.anathema.platform.tree.display.ContentFactory;

public class FxBooleanDisplayFactory implements ContentFactory {
  @Override
  public <T> T create(Object... parameters) {
    String label = (String) parameters[0];
    return (T) new FxCheckMenuItemView(label);
  }
}
