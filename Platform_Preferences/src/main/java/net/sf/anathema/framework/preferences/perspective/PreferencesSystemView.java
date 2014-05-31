package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.platform.fx.PerspectivePane;

import java.util.ArrayList;
import java.util.Collection;

public class PreferencesSystemView {
  public final PerspectivePane perspectivePane = new PerspectivePane();
  public final FxPreferencesNavigation preferencesNavigation;
  public final FxPreferencesView preferencesView;

  public PreferencesSystemView(ObjectFactory objectFactory) {
    Collection<PreferenceView> views = objectFactory.instantiateAllImplementers(PreferenceView.class);
    this.preferencesView = new FxPreferencesView();
    this.preferencesNavigation = new FxPreferencesNavigation(new ArrayList<>(views), preferencesView, null);
    perspectivePane.setNavigationComponent(preferencesNavigation.getNode());
    perspectivePane.setContentComponent(preferencesView.getNode());
  }
}