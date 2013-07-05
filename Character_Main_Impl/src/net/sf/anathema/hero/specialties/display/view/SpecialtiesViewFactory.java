package net.sf.anathema.hero.specialties.display.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.character.view.SubViewFactory;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;

@RegisteredCharacterView(SpecialtiesConfigurationView.class)
public class SpecialtiesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    FxSpecialtiesView fxSpecialtiesView = new FxSpecialtiesView();
    return (T) new BridgingSpecialtiesView(fxSpecialtiesView);
  }
}