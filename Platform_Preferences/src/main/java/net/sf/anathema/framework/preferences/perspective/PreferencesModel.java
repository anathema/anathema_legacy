package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.persistence.PreferencePto;

public interface PreferencesModel {
  PreferencePto serialize();

  void initializeFrom(PreferencePto pto);

  PreferenceModel find(Class modelClass);
}