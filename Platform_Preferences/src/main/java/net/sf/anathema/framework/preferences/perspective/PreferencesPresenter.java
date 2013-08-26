package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.environment.Environment;
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
    addSaveButtonToNavigation();
    addNavigationEntryForEachCategory();
  }

  private void addNavigationEntryForEachCategory() {
    //To change body of created methods use File | Settings | File Templates.
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

  private void savePreferences() {
    persister.save(model);
  }
}
