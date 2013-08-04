package net.sf.anathema.hero.charms.display;

import net.sf.anathema.fx.hero.traitview.FxTraitView;
import net.sf.anathema.platform.tree.display.ContentFactory;

public class FxIntDisplayFactory implements ContentFactory {
  @Override
  public <T> T create(Object... parameters) {
    String label = (String) parameters[0];
    int value = (Integer) parameters[1];
    int maxValue = (Integer) parameters[2];
    FxTraitView view = FxTraitView.WithDefaultLayout(label, maxValue);
    return (T) new FxIntValueView(view);
  }
}
