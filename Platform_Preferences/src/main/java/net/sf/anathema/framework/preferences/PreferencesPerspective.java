package net.sf.anathema.framework.preferences;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;

public class PreferencesPerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setText("Preferences");
    toggle.setTooltip("Configure Anathema");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Environment environment) {
    PreferencesView view = new PreferencesView();
    container.setContent(view.getNode());
    PreferencesPresenter presenter = new PreferencesPresenter(environment);
    presenter.initialize();
  }
}
