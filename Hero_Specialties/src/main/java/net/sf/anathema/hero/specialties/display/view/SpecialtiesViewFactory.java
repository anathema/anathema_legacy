package net.sf.anathema.hero.specialties.display.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;
import net.sf.anathema.platform.fx.Stylesheet;

@RegisteredCharacterView(SpecialtiesConfigurationView.class)
public class SpecialtiesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    FxSpecialtiesView fxSpecialtiesView = new FxSpecialtiesView();
    String[] skins = new CssSkinner().getSkins(type);
    for (String skin : skins) {
      new Stylesheet(skin).applyToParent(fxSpecialtiesView.getNode());
    }
    return (T) fxSpecialtiesView;
  }
}