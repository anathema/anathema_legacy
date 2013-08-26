package net.sf.anathema.hero.specialties.display.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;
import net.sf.anathema.platform.fx.Stylesheet;

@RegisteredCharacterView(SpecialtiesConfigurationView.class)
public class SpecialtiesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxSpecialtiesView();
  }
}