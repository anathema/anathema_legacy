package net.sf.anathema.hero.specialties.display.view;

import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;

@Produces(SpecialtiesConfigurationView.class)
public class SpecialtiesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxSpecialtiesView();
  }
}