package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.interaction.Tool;

public interface PreferencesNavigation {
  Tool addTool();

  PreferenceView addSection(String title, Class viewClass);
}