package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.platform.svgtree.presenter.view.ContentFactory;

public class SpecialIntValueFactory implements ContentFactory {
  private IntegerViewFactory factory;

  public SpecialIntValueFactory(IntegerViewFactory factory) {
    this.factory = factory;
  }

  @Override
  public IIntValueView create(Object... parameters) {
    String label = (String) parameters[0];
    int value = (Integer) parameters[1];
    int maxValue = (Integer) parameters[2];
    SimpleTraitView view = new SimpleTraitView(factory, label, value, maxValue);
    return new SpecialIntValueView(view);
  }
}