package net.sf.anathema.character.impl.view;

import net.sf.anathema.framework.value.IIntValueDisplayFactory;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.IBasicAdvantageView;

public class AdvantageViewFactory implements IAdvantageViewFactory {

  private final IIntValueDisplayFactory intValueDisplayFactory;

  public AdvantageViewFactory(IIntValueDisplayFactory intValueDisplayFactory) {
    this.intValueDisplayFactory = intValueDisplayFactory;
  }

  @Override
  public IBasicAdvantageView createBasicAdvantageView() {
    return new BasicAdvantageView(intValueDisplayFactory);
  }
}