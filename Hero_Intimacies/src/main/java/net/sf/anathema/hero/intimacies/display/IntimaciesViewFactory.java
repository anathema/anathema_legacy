package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.framework.display.SubViewFactory;
import net.sf.anathema.framework.util.Produces;

@Produces(IntimaciesView.class)
public class IntimaciesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxIntimaciesView();
  }
}