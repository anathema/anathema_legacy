package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.preferences.persistence.PropertiesPreferencesPersister;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.file.RelativePath;

@PerspectiveAutoCollector
@Weight(weight = 8000)
public class PreferencesPerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/preferences.png"));
    toggle.setTooltip("Configure Anathema");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Environment environment) {
    ObjectFactory objectFactory = applicationModel.getObjectFactory();
    PreferencesSystemView view = new PreferencesSystemView(objectFactory);
    container.setContent(view.perspectivePane.getNode());
    PreferencesModel model = new CollectingPreferencesModel(objectFactory);
    PreferencesPersister persister = new PropertiesPreferencesPersister();
    PreferencesPresenter presenter = new PreferencesPresenter(environment, view.preferencesNavigation, model, persister, objectFactory);
    presenter.initialize();
  }
}

//Find all Preferences. For each: View, Presenter, Model
//Register all preferences by view
//Persisters: Write to a single file in the main directory
//Main Presenter: For each model, find a view and a persister and initialize
//Early during initialization: Load all preferences
//Main Persister: Write all serialized to single properties file
//In Environment: Offer to get preference loading implementation by class. Hand in to wherever required.