package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.platform.tree.presenter.view.ContentFactory;

public class SpecialIntDisplayFactory implements ContentFactory {
  private IntegerViewFactory factory;

  public SpecialIntDisplayFactory(IntegerViewFactory factory) {
    this.factory = factory;
  }

  @SuppressWarnings("unchecked")
  @Override
  public IntValueView create(Object... parameters) {
    String label = (String) parameters[0];
    int value = (Integer) parameters[1];
    int maxValue = (Integer) parameters[2];
    SimpleTraitView view = SimpleTraitView.RightAlignedWithoutUpperBounds(factory, label, value, maxValue);
    return new SpecialIntValueView(view);
  }
}