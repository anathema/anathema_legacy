package net.sf.anathema.framework.preferences.elements;

import net.sf.anathema.framework.preferences.persistence.PreferencePto;

public interface PreferenceModel extends DirtyModel {
  void serializeTo(PreferencePto pto);

  void initializeFrom(PreferencePto pto);
}