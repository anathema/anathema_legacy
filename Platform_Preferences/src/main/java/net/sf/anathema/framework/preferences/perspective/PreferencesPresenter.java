package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.elements.PreferencePresenter;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferencePresenter;
import net.sf.anathema.framework.preferences.persistence.PreferencePto;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;

import java.util.Collection;

public class PreferencesPresenter {

  private final Environment environment;
  private final PreferencesNavigation preferencesNavigation;
  private final PreferencesModel model;
  private final PreferencesPersister persister;
  private ObjectFactory objectFactory;
  private final DirtyProxy dirtyProxy = new DirtyProxy();

  public PreferencesPresenter(Environment environment, PreferencesNavigation preferencesNavigation,
                              PreferencesModel model, PreferencesPersister persister, ObjectFactory objectFactory) {
    this.environment = environment;
    this.preferencesNavigation = preferencesNavigation;
    this.model = model;
    this.persister = persister;
    this.objectFactory = objectFactory;
  }

  public void initialize() {
    loadPreferences();
    initIndividualPresentations();
    addSaveButtonToNavigation();
  }

  private void initIndividualPresentations() {
    Collection<PreferencePresenter> presenters = objectFactory.instantiateOrdered(RegisteredPreferencePresenter.class);
    for (PreferencePresenter presenter : presenters) {
      presenter.useEnvironment(environment);
      PreferenceModel preferenceModel = model.find(presenter.getModelClass());
      dirtyProxy.register(preferenceModel);
      presenter.useModel(preferenceModel);
      PreferenceView view = preferencesNavigation.addSection(presenter.getTitle(), presenter.getViewClass());
      presenter.useView(view);
      presenter.initialize();
    }
  }

  private void addSaveButtonToNavigation() {
    Tool tool = preferencesNavigation.addTool();
    tool.setIcon(new RelativePath("icons/TaskBarSave24.png"));
    tool.setCommand(() -> {
      savePreferences();
      tool.disable();
    });
    dirtyProxy.whenDirtied(() -> tool.enable());
    tool.disable();
  }

  private void loadPreferences() {
    PreferencePto pto = persister.load();
    model.initializeFrom(pto);
  }

  private void savePreferences() {
    PreferencePto pto = model.serialize();
    persister.save(pto);
  }
}