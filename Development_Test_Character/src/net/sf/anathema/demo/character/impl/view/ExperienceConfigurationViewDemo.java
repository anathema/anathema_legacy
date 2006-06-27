package net.sf.anathema.demo.character.impl.view;

import net.sf.anathema.character.impl.model.advance.ExperiencePointConfiguration;
import net.sf.anathema.character.impl.view.ExperienceConfigurationView;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.presenter.advance.ExperienceConfigurationPresenter;
import net.sf.anathema.framework.resources.AnathemaResources;

public class ExperienceConfigurationViewDemo extends BasicCharacterDemoCase {

  public void demo() {
    ExperienceConfigurationView view = new ExperienceConfigurationView("Ich bin ein Header"); //$NON-NLS-1$
    IExperiencePointConfiguration model = new ExperiencePointConfiguration();
    ExperienceConfigurationPresenter presenter = new ExperienceConfigurationPresenter(
        new AnathemaResources(),
        model,
        view);
    presenter.initPresentation();
    show(view.getComponent());
  }
}