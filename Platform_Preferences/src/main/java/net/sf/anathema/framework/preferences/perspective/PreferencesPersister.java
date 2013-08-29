package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.preferences.persistence.PreferencePto;

public interface PreferencesPersister {
  void save(PreferencePto pto);

  PreferencePto load();
}