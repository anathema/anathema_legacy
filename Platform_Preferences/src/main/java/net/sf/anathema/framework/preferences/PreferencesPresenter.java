package net.sf.anathema.framework.preferences;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.lib.resources.Resources;

public class PreferencesPresenter {

  private final Environment environment;
  private final PreferencesNavigation preferencesNavigation;
  private final PreferencesView preferencesView;

  public PreferencesPresenter(Environment environment, PreferencesNavigation preferencesNavigation, PreferencesView preferencesView) {
    this.environment = environment;
    this.preferencesNavigation = preferencesNavigation;
    this.preferencesView = preferencesView;
  }

  public void initialize() {
    //To change body of created methods use File | Settings | File Templates.
  }
}
