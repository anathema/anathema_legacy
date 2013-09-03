package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.preferences.elements.PreferencePresenter;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferencePresenter;
import net.sf.anathema.framework.preferences.persistence.PreferencePto;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;

import java.util.Collection;

public class PreferencesPresenter {

  private final Environment environment;
  private final PreferencesNavigation preferencesNavigation;
  private final PreferencesModel model;
  private final PreferencesPersister persister;
  private ObjectFactory objectFactory;

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
    addSaveButtonToNavigation();
    initIndividualPresentations();
  }

  private void initIndividualPresentations() {
    Collection<PreferencePresenter> presenters = objectFactory.instantiateOrdered(RegisteredPreferencePresenter.class);
    for (PreferencePresenter presenter : presenters) {
      presenter.useResources(environment);
      presenter.useModel(model.find(presenter.getModelClass()));
      PreferenceView view = preferencesNavigation.addSection(presenter.getTitle(), presenter.getViewClass());
      presenter.useView(view);
      presenter.initialize();
    }
  }

  private void addSaveButtonToNavigation() {
    Tool tool = preferencesNavigation.addTool();
    tool.setIcon(new RelativePath("icons/TaskBarSave24.png"));
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        savePreferences();
      }
    });
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