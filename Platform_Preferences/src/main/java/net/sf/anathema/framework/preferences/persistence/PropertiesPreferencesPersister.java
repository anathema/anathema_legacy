package net.sf.anathema.framework.preferences.persistence;

import net.sf.anathema.framework.preferences.perspective.PreferencesPersister;

public class PropertiesPreferencesPersister implements PreferencesPersister {
  @Override
  public void save(PreferencePto pto) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public PreferencePto load() {
    return new PreferencePto();
  }
}