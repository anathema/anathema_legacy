package net.sf.anathema.fx.hero.configurableview;

import net.sf.anathema.character.framework.display.SubViewFactory;
import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;

@Produces(ConfigurableCharacterView.class)
public class ConfigurableViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxConfigurableView view = new FxConfigurableView();
    return (T) view;
  }
}