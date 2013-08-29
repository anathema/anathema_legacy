package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.preferences.persistence.PreferencePto;
import net.sf.anathema.framework.presenter.action.preferences.PreferencesPage;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;

public class PreferencesPresenter {

  private final Environment environment;
  private final PreferencesNavigation preferencesNavigation;
  private final PreferencesView preferencesView;
  private final PreferencesModel model;
  private final PreferencesPersister persister;

  public PreferencesPresenter(Environment environment, PreferencesNavigation preferencesNavigation,
                              PreferencesView preferencesView, PreferencesModel model, PreferencesPersister persister) {
    this.environment = environment;
    this.preferencesNavigation = preferencesNavigation;
    this.preferencesView = preferencesView;
    this.model = model;
    this.persister = persister;
  }

  public void initialize() {
    loadPreferences();
    addSaveButtonToNavigation();
    addNavigationEntryForEachCategory();
  }

  private void addNavigationEntryForEachCategory() {
  }

  private void addSaveButtonToNavigation() {
    Tool tool = preferencesNavigation.addTool();
    tool.setText("Save");
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