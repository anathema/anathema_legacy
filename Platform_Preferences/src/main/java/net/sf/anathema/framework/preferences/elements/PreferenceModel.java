package net.sf.anathema.framework.preferences.elements;

import net.sf.anathema.framework.preferences.persistence.PreferencePto;

public interface PreferenceModel {
  void serializeTo(PreferencePto pto);

  void initializeFrom(PreferencePto pto);
}