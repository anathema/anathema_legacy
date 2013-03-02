package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.value.IntegerViewFactory;

public class AdvantageViewFactory implements IAdvantageViewFactory {

  private final IntegerViewFactory intValueDisplayFactory;

  public AdvantageViewFactory(IntegerViewFactory intValueDisplayFactory) {
    this.intValueDisplayFactory = intValueDisplayFactory;
  }

  @Override
  public IBasicAdvantageView createBasicAdvantageView() {
    return new BasicAdvantageView(intValueDisplayFactory);
  }
}