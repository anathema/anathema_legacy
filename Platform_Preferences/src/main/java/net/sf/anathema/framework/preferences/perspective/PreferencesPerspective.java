package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.preferences.persistence.PropertiesPreferencesPersister;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.lib.file.RelativePath;

@PerspectiveAutoCollector
@Weight(weight = 8000)
public class PreferencesPerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/preferences.png"));
    toggle.setTooltip("Preferences.Perspective");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Environment environment) {
    PreferencesSystemView view = new PreferencesSystemView(environment);
    container.setContent(view.perspectivePane.getNode());
    PreferencesModel model = new CollectingPreferencesModel(environment);
    PreferencesPersister persister = new PropertiesPreferencesPersister();
    PreferencesPresenter presenter = new PreferencesPresenter(environment, view.preferencesNavigation, model, persister, environment);
    presenter.initialize();
  }
}