package net.sf.anathema.framework.preferences;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;

@PerspectiveAutoCollector
@Weight(weight = 8000)
public class PreferencesPerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setText("Preferences");
    toggle.setTooltip("Configure Anathema");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Environment environment) {
    PreferencesSystemView view = new PreferencesSystemView();
    container.setContent(view.perspectivePane.getNode());
    PreferencesPresenter presenter = new PreferencesPresenter(environment, view.preferencesNavigation, view.preferencesView);
    presenter.initialize();
  }
}

//Find all Preferences. For each: Persister, View, Presenter, Model
//Register all preferences by view
//Persisters: Write to a single file in the main directory
//Main Presenter: For each model, find a view and a persister and initialize
//Main Persister: Find all persisters, have all models.
//Main Persister: For each persister, write data for corresponding model or default - to a single file!?
//Main Persister: For each persister, load data for corresponding model or default.
