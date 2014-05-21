package net.sf.anathema.hero.concept.display.caste.view;

import net.sf.anathema.character.framework.display.SubViewFactory;
import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.concept.display.caste.presenter.CasteView;
import net.sf.anathema.platform.fx.Stylesheet;

@Produces(CasteView.class)
public class CasteViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxCasteView fxView = new FxCasteView();
    new Stylesheet("skin/concept/concept.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}