package net.sf.anathema.demo.character.impl.view;

import de.jdemo.junit.DemoAsTestRunner;
import net.sf.anathema.character.impl.model.advance.ExperiencePointConfiguration;
import net.sf.anathema.character.impl.view.ExperienceConfigurationView;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.presenter.advance.ExperienceConfigurationPresenter;
import net.sf.anathema.framework.resources.AnathemaResources;
import org.junit.runner.RunWith;

@RunWith(DemoAsTestRunner.class)
public class ExperienceConfigurationViewDemo extends BasicCharacterDemoCase {

  public void demo() {
    ExperienceConfigurationView view = new ExperienceConfigurationView();
    IExperiencePointConfiguration model = new ExperiencePointConfiguration();
    ExperienceConfigurationPresenter presenter = new ExperienceConfigurationPresenter(
        new AnathemaResources(),
        model,
        view);
    presenter.initPresentation();
    show(view.getComponent());
  }
}