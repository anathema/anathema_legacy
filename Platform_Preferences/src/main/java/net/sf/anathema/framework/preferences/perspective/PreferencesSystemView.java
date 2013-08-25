package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.platform.fx.PerspectivePane;

public class PreferencesSystemView {
  public final PerspectivePane perspectivePane = new PerspectivePane();
  public final FxPreferencesView preferencesView = new FxPreferencesView();
  public final FxPreferencesNavigation preferencesNavigation = new FxPreferencesNavigation();

  public PreferencesSystemView() {
    perspectivePane.setNavigationComponent(preferencesNavigation.getNode());
    perspectivePane.setContentComponent(preferencesView.getNode());
  }
}